package com.jpw.raptor.search.finance;


import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.close.CloseIndexResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;

import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by john on 4/22/18.
 */
public class learnitTest {

    public RestHighLevelClient client;

    @Before
    public void setup() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));
    }


    @Test
    public void test01() throws IOException{


        //
        // Build document with XContentBuilder
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();

        builder.field("user", "kimchy");
        builder.field("postDate", new Date());
        builder.field("message", "trying out Elasticsearch");
        builder.array("field name", "value1", "value2", "valuen");

        String[] array = { "value1", "value2", "valuen"};
        builder.field("another one", array);

        builder.endObject();
        System.out.println(builder.string());


    }

    @Ignore
    @Test
    public void createIndex() throws IOException {

        //
        // create index request
        CreateIndexRequest request = new CreateIndexRequest("twitter");

        //
        // Add mapping to index
        String mappingString =
                "  {\n" +
                        "    \"tweet\": {\n" +
                        "      \"properties\": {\n" +
                        "        \"message\": {\n" +
                        "          \"type\": \"text\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }";

        // put it all together
        request.mapping("tweet", mappingString, XContentType.JSON);

        //
        // Synchronous execution
        CreateIndexResponse createIndexResponse = client.indices().create(request);

        boolean acknowledged = createIndexResponse.isAcknowledged();

    }

    @Ignore
    @Test
    public void deleteIndex() throws IOException {

        //
        // Delete index
        DeleteIndexRequest request = new DeleteIndexRequest("posts");

        //
        // Synchronous execution
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(request);

        boolean deleteIndexResponseAck = deleteIndexResponse.isAcknowledged();

    }

    @Ignore
    @Test
    public void openIndex() throws IOException {

        //
        // open index
        OpenIndexRequest request = new OpenIndexRequest("index");

        //
        // Synchronous execution
        OpenIndexResponse openIndexResponse = client.indices().open(request);

        boolean acknowledged = openIndexResponse.isAcknowledged();

    }

    @Ignore
    @Test
    public void closeIndex() throws IOException {

        //
        // close index
        CloseIndexRequest request = new CloseIndexRequest("index");

        //
        // Synchronous execution
        CloseIndexResponse closeIndexResponse = client.indices().close(request);

        boolean acknowledged = closeIndexResponse.isAcknowledged();

    }

    @Ignore
    @Test
    public void indexDocument() throws IOException {

        //
        // index document request
        IndexRequest request = new IndexRequest(
                "posts",      // index
                "doc"  );     //document type

        //
        // Document as JSON
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

        // put it all together
        request.source(jsonString, XContentType.JSON);

        //
        // Build document with XContentBuilder
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();

        builder.field("user", "kimchy");
        builder.field("postDate", new Date());
        builder.field("message", "trying out Elasticsearch");
        builder.array("field name", "value1", "value2", "valuen");

        builder.endObject();

        // put it all together
        IndexRequest indexRequest = new IndexRequest("posts", "doc").source(builder);

        // can specify the operation update is the default this should be optional
        indexRequest.opType("update");
        indexRequest.opType("create");

        IndexResponse indexResponse = client.index(indexRequest);
        String index = indexResponse.getIndex();
        String type = indexResponse.getType();
        String id = indexResponse.getId();
        long version = indexResponse.getVersion();


        if (indexResponse.getResult()== DocWriteResponse.Result.CREATED) {

        } else if (indexResponse.getResult()== DocWriteResponse.Result.UPDATED) {

        } else {

        }
    }

    @Ignore
    @Test
    public void getDocument() throws IOException {

        //
        // get request
        GetRequest request = new GetRequest(
                "posts",    // index
                "doc",      // document type
                "1");       // document id

        // all fields returned by default
        // select fields
        String[] includes = new String[]{"message", "*Date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);

        //
        // Synchronous execution
        GetResponse getResponse = client.get(request);

        //
        // Get response
        String index = getResponse.getIndex();
        String type = getResponse.getType();
        String id = getResponse.getId();
        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();            // document as string
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();     // document as field map
        } else {

        }

    }

    @Ignore
    @Test
    public void deleteDocument() throws IOException {

        //
        // delete document
        DeleteRequest request = new DeleteRequest(
                "posts",    // index
                "doc",      // document type
                "1");       // document id

        //
        // Synchronous execution
        DeleteResponse deleteResponse = client.delete(request);

        //
        // Delete response
        String index = deleteResponse.getIndex();
        String type = deleteResponse.getType();
        String id = deleteResponse.getId();
        if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            // it failed
        }

    }

    @Ignore
    @Test
    public void updateDocument() throws IOException {

        //
        // Update document
        UpdateRequest request = new UpdateRequest(
                "posts",    // index
                "doc",      // document type
                "1");       // document id

        //
        // Using a map
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");
        request.doc(jsonMap);

        //
        // Using a builder
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("updated", new Date());
            builder.field("reason", "daily update");
        }
        builder.endObject();
        request.doc(builder);

        //
        // You can also upsert
        //request.upsert(jsonMap);
        //request.upsert(builder);

        //
        // Synchronous execution
        UpdateResponse updateResponse = client.update(request);

        //
        // Response
        String index = updateResponse.getIndex();
        String type = updateResponse.getType();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();

        //
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            // Handle the case where the document was created for the first time (upsert)
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            // Handle the case where the document was updated
        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
            // Handle the case where the document was deleted
        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
            // Handle the case where the document was not impacted by the update,
            // ie no operation (noop) was executed on the document
        } else {
            // failure
        }

    }

    @Ignore
    @Test
    public void search() throws IOException {

        //
        // Search document
        SearchRequest searchRequest;
        searchRequest = new SearchRequest();

        //
        // Specify index and document type to search
        searchRequest = new SearchRequest("posts");
        searchRequest.types("doc");

        //
        // Search parameters are added to the searchSourceBuilder
        SearchSourceBuilder searchSourceBuilder;
        searchSourceBuilder = new SearchSourceBuilder();

        //
        // Specify the result index to start searching from defaults to 0
        searchSourceBuilder.from(0);

        //
        // Specify number of search hits to return defaults to 10
        searchSourceBuilder.size(5);

        //
        // specify search timeout
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //
        // enable fuzzy search
        // which is the number of one character changes that need to be made to one string to make it the same as another string.
        // AUTO generates an edit distance based on the length of the term.
        //      term length 0..2 must match exactly
        //      term length 3..5 one edit allowed
        //      term length >5 two edits allowed
        // prefix_length The number of initial characters which will not be “fuzzified”
        // max_expansions The maximum number of terms that the fuzzy query will expand to.
        QueryBuilders.matchQuery("user", "john doe").fuzziness(Fuzziness.AUTO).prefixLength(3).maxExpansions(10);

        // match all documents
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        //
        // term query exact match for kimchy in the user field (includes spelling and capitalization)
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));

        //
        // matches all documents where john or doe appear in the user field
        searchSourceBuilder.query(QueryBuilders.matchQuery("user", "john doe"));

        //
        // matches all documents where john and doe appear in the user field order does not matter
        searchSourceBuilder.query(QueryBuilders.matchQuery("user", "john doe").operator(Operator.AND));

        //
        // matches all documents where john and doe appear in the user field john must occur befor doe
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("user", "john doe"));

        //
        // matches all documents where john and doe appear in the user field
        // john must occur before doe also no more than 2 terms may be between john and doe
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("user", "john doe").slop(3));

        //
        // matches all documents where john or doe appear in any of the specified fields
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("john doe", "field_1", "field_2", "*_field_wildcard"));


        // boolean query
        searchSourceBuilder.query(
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("content", "test1"))
                        .must(QueryBuilders.termQuery("content", "test4"))
                        .mustNot(QueryBuilders.termQuery("content", "test2"))
                        .should(QueryBuilders.termQuery("content", "test3"))
                        .filter(QueryBuilders.termQuery("content", "test5"))
        );

        //
        // Alternate way to build query
        QueryBuilder termQuery1 = QueryBuilders.termQuery("content", "test1");
        searchSourceBuilder.query(termQuery1);

        //
        // Limit returned fields
        String[] includeFields = new String[] {"title", "user", "innerObject.*"};
        String[] excludeFields = new String[] {"_type"};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);

        //
        // Add parameters to the search request
        searchRequest.source(searchSourceBuilder);

        //
        // Synchronous execution
        SearchResponse searchResponse = client.search(searchRequest);

        //
        // Search response
        RestStatus  status = searchResponse.status();
        TimeValue   took = searchResponse.getTook();
        Boolean     terminatedEarly = searchResponse.isTerminatedEarly();
        boolean     timedOut = searchResponse.isTimedOut();

        //
        // Search hits
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        float maxScore = hits.getMaxScore();

        //
        // iterate over returned documents
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {

            // hit information
            String index = hit.getIndex();
            String type = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();

            // get the document
            //"_source" : {
            //    "title" : "qbox",
            //    "text" : ["value1","value2","valuen"]
            //}
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            // single value field
            String documentTitle = (String) sourceAsMap.get("title");

            // multi value field
            List<Object> textLines = (List<Object>) sourceAsMap.get("text");
            for ( Object line : textLines ) {
                String asString = (String) line;
            }

        }

    }


    @Ignore
    @Test
    public void searchWithScroll() throws IOException {

        // Search request for index posts
        SearchRequest searchRequest = new SearchRequest("posts");

        // Create scroll
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));

        // Add scroll to search request
        searchRequest.scroll(scroll);

        // Create parameters for a match all query
        SearchSourceBuilder searchSourceBuilder= new SearchSourceBuilder();

        // match all documents
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        // Add the parameters to the search reques
        searchRequest.source(searchSourceBuilder);

        // Synchronous execution
        SearchResponse searchResponse = client.search(searchRequest);

        // Get the scroll id for additional requests to elastic search
        String scrollId = searchResponse.getScrollId();

        // get the search hits
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            searchResponse = client.searchScroll(scrollRequest);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();

            // process returned search results
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest);
        boolean succeeded = clearScrollResponse.isSucceeded();



    }

}
