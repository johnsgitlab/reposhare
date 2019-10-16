package com.jpw.raptor.search.finance;

import com.jpw.raptor.model.RaptorConstants;
import com.jpw.raptor.model.FinanceKnowledge;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.close.CloseIndexResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 5/16/18.
 */
@Service
public class ElasticSearch {

    private static final Logger logger      = LoggerFactory.getLogger(ElasticSearch.class);

    private RestHighLevelClient client;

    // Constructor
    public ElasticSearch() {}

    @PostConstruct
    public void postConstruct() {
        openIndex();
    }

    @PreDestroy
    public void preDestroy() {
        closeIndex();
    }

    public boolean openIndex() {

        boolean result = false;

        try {

            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http"),
                            new HttpHost("localhost", 9201, "http")
                    )
            );

            //
            // open index
            OpenIndexRequest request = new OpenIndexRequest("raptor");

            //
            // Synchronous execution
            OpenIndexResponse openIndexResponse = client.indices().open(request);
            result = openIndexResponse.isAcknowledged();

        } catch ( IOException ex) {
        }
        return result;

    }

    public boolean closeIndex() {

        boolean result = false;

        try {
            //
            // close index
            CloseIndexRequest request = new CloseIndexRequest("raptor");

            //
            // Synchronous execution
            CloseIndexResponse closeIndexResponse = client.indices().close(request);
            result = closeIndexResponse.isAcknowledged();

            client.close();
        } catch ( IOException ex) {
        }

        return result;

    }

    public boolean deleteDocument(String id) throws IOException {

        // delete document request
        DeleteRequest request = new DeleteRequest(RaptorConstants.INDEX, RaptorConstants.DOC_TYPE, id);

        // Synchronous execution
        DeleteResponse deleteResponse = client.delete(request);

        boolean result = true;
        if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            result = false;
        }

        return result;
    }

    public String indexDocument(FinanceKnowledge doc) throws IOException {

        //
        // Build document with XContentBuilder
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();

        if ( doc.getUrl() != null )
            builder.field(RaptorConstants.FIELD_URL, doc.getUrl());
        if ( doc.getLoc() != null )
            builder.field(RaptorConstants.FIELD_LOC, doc.getLoc());

        if ( doc.getTitle() != null )
            builder.field(RaptorConstants.FIELD_TITLE, doc.getTitle());
        if ( doc.getTag() != null )
            builder.field(RaptorConstants.FIELD_TAG, doc.getTag());
        if ( doc.getBody() != null ) {
            builder.field(RaptorConstants.FIELD_BODY, doc.getBody());
        }

        builder.endObject();

        // put it all together
        IndexRequest indexRequest = new IndexRequest(RaptorConstants.INDEX, RaptorConstants.DOC_TYPE).source(builder);

        //indexRequest.opType("create");

        IndexResponse indexResponse = client.index(indexRequest);

        String id = null;

        if ( indexResponse.getResult()== DocWriteResponse.Result.CREATED ||
                indexResponse.getResult()== DocWriteResponse.Result.UPDATED   ) {
            id = indexResponse.getId();
        }

        return id;
    }

    public FinanceKnowledge getDocument(String id) throws IOException {

        FinanceKnowledge doc = null;

        // get request
        GetRequest request = new GetRequest(RaptorConstants.INDEX, RaptorConstants.DOC_TYPE, id);

        // all fields returned by default

        // Synchronous execution
        GetResponse getResponse = client.get(request);

        // Get response
        Map<String, Object> sourceAsMap = null;

        if (getResponse.isExists()) {
            // document as field map
            doc = new FinanceKnowledge(getResponse.getSourceAsMap());
            doc.setId(getResponse.getId());
        }

        return doc;
    }


    public List<FinanceKnowledge> getAll() throws IOException {

        // Search request
        SearchRequest searchRequest= new SearchRequest();

        // Specify index and document type to search
        searchRequest = new SearchRequest(RaptorConstants.INDEX);
        searchRequest.types(RaptorConstants.DOC_TYPE);

        // Search parameters are added to the searchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // specify search timeout
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //
        // Limit returned fields
        String[] includeFields = new String[]
                {RaptorConstants.FIELD_TITLE, RaptorConstants.FIELD_URL, RaptorConstants.FIELD_LOC, RaptorConstants.FIELD_TAG};
        String[] excludeFields = new String[] {RaptorConstants.FIELD_BODY};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);

        // match all documents
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        // Add parameters to the search request
        searchRequest.source(searchSourceBuilder);

        // Synchronous execution and return SearchResponse
        return generateList(client.search(searchRequest));
    }



    public List<FinanceKnowledge> phraseQuery(String phrase) throws IOException {

        // Search request
        SearchRequest searchRequest= new SearchRequest();

        // Specify index and document type to search
        searchRequest = new SearchRequest(RaptorConstants.INDEX);
        searchRequest.types(RaptorConstants.DOC_TYPE);

        // Search parameters are added to the searchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // specify search timeout
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // specify sort
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));

        //
        // Limit returned fields
        String[] includeFields = new String[]
                {RaptorConstants.FIELD_TITLE, RaptorConstants.FIELD_URL, RaptorConstants.FIELD_LOC, RaptorConstants.FIELD_TAG};
        String[] excludeFields = new String[] {RaptorConstants.FIELD_BODY};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);

        // match documents where all phrase terms appear in the same order contained in the search phrase
        // no more than 2 other terms may be between the search terms
        searchSourceBuilder.query(
                QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchPhraseQuery(RaptorConstants.FIELD_TITLE, phrase).slop(3))
                        .should(QueryBuilders.matchPhraseQuery(RaptorConstants.FIELD_BODY, phrase).slop(3))
        );

        // Add parameters to the search request
        searchRequest.source(searchSourceBuilder);

        // Synchronous execution and return SearchResponse
        return generateList(client.search(searchRequest));
    }


    public List<FinanceKnowledge> allWordsQuery(String phrase) throws IOException {

        // Search request
        SearchRequest searchRequest= new SearchRequest();

        // Specify index and document type to search
        searchRequest = new SearchRequest(RaptorConstants.INDEX);
        searchRequest.types(RaptorConstants.DOC_TYPE);

        // Search parameters are added to the searchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // specify search timeout
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // specify sort
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));

        //
        // Limit returned fields
        String[] includeFields = new String[]
                {RaptorConstants.FIELD_TITLE, RaptorConstants.FIELD_URL, RaptorConstants.FIELD_LOC, RaptorConstants.FIELD_TAG};
        String[] excludeFields = new String[] {RaptorConstants.FIELD_BODY};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);

        // match documents where all phrase terms appear
        searchSourceBuilder.query(
                QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery(RaptorConstants.FIELD_TITLE, phrase).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery(RaptorConstants.FIELD_BODY, phrase).operator(Operator.AND))
        );

        // Add parameters to the search request
        searchRequest.source(searchSourceBuilder);

        // Synchronous execution and return SearchResponse
        return generateList(client.search(searchRequest));

    }


    public List<FinanceKnowledge> wordsQuery(String phrase) throws IOException {

        // Search request
        SearchRequest searchRequest= new SearchRequest();

        // Specify index and document type to search
        searchRequest = new SearchRequest(RaptorConstants.INDEX);
        searchRequest.types(RaptorConstants.DOC_TYPE);

        // Search parameters are added to the searchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // specify search timeout
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // specify sort
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));

        //
        // Limit returned fields
        String[] includeFields = new String[]
                {RaptorConstants.FIELD_TITLE, RaptorConstants.FIELD_URL, RaptorConstants.FIELD_LOC, RaptorConstants.FIELD_TAG};
        String[] excludeFields = new String[] {RaptorConstants.FIELD_BODY};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);

        // match documents where one or more phrase terms appear
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(phrase, RaptorConstants.FIELD_TITLE, RaptorConstants.FIELD_BODY));

        // Add parameters to the search request
        searchRequest.source(searchSourceBuilder);

        // Synchronous execution and return SearchResponse
        return generateList(client.search(searchRequest));
    }

    public List<FinanceKnowledge> generateList(SearchResponse rsp) {
        System.out.println( "***********");
        System.out.println( "generateList");

        if ( rsp == null )  {
            System.out.println( "No SearchResponse");
            return null;
        }

        // Get hits
        SearchHits searchHits = rsp.getHits();;
        System.out.println( "Hits " + searchHits.totalHits);

        // allocate list space
        List<FinanceKnowledge> list = new ArrayList<FinanceKnowledge>();

        // loop through hits create a knowledge document and add it to the list
        for (SearchHit hit : searchHits) {
            // get the document fields
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            // Create knowledge document
            FinanceKnowledge doc = new FinanceKnowledge(sourceAsMap);

            // Add id and score to document
            System.out.println( "************");
            System.out.println( "id     " + hit.getId());
            System.out.println( "score  " + hit.getScore());
            System.out.println( "************");

            doc.setId(hit.getId());
            doc.setScore(hit.getScore());

            // document to list
            list.add(doc);
            System.out.println( "add document");
        }

        return list;
    }
}
