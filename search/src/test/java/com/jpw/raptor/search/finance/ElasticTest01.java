package com.jpw.raptor.search.finance;

import com.google.gson.Gson;
import com.jpw.raptor.model.FinanceKnowledge;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 5/4/18.
 */
public class ElasticTest01 {

    FinanceKnowledge    k1;
    FinanceKnowledge    k2;
    FinanceKnowledge    k3;


    String              eol;

    public void setupK1() {

        StringBuilder sb = new StringBuilder();
        sb.append("the quick brown fox ran").append(eol);
        sb.append("humpy dumpty fell down").append(eol);
        sb.append("now is the time for all good men");

        k1 = new FinanceKnowledge();
        k1.setTitle("knowledge 1");
        k1.setLoc("childrens nursery rhymes");
        k1.setBody(sb.toString());
    }


    public void setupK2() {

        StringBuilder sb = new StringBuilder();
        sb.append("Earnings per share (EPS) is generally considered to be the single most important variable in determining a share's price.").append(eol);
        sb.append("The diluted EPS is the worst-case scenario for the earnings per share").append(eol);
        sb.append("EPS = (Net Income - Dividends on Preferred Stock) / Average Outstanding Shares. ");

        k2 = new FinanceKnowledge();
        k2.setTitle("knowledge 2");
        k2.setLoc("Earnings Per Share");
        k2.setBody(sb.toString());
    }


    public void setupK3() {

        StringBuilder sb = new StringBuilder();
        sb.append("it shows how much investors are willing to pay per dollar of earnings.").append(eol);
        sb.append("The price-earnings ratio (P/E ratio) is the ratio for valuing a company that measures its current share price relative to its per share earnings. ");

        k3 = new FinanceKnowledge();
        k3.setTitle("knowledge 3");
        k3.setLoc("Price-Earnings Ratio");
        k3.setBody(sb.toString());
    }

    @Before
    public void setUp() throws java.text.ParseException {
        eol = System.getProperty("line.separator");

        setupK1();
        setupK2();
        setupK3();
    }

    @After
    public void tearDown() {
    }

    public void printHit(SearchHit hit) {

        System.out.println();

        // hit information
        System.out.println("index " + hit.getIndex());
        System.out.println("type  " + hit.getType());
        System.out.println("id    " + hit.getId());
        System.out.println("score " + hit.getScore());

        // get the document
        //"_source" : {
        //    "title" : "qbox",
        //    "tag" : ["value1","value2","valuen"]
        //}
        Map<String, Object> sourceAsMap = hit.getSourceAsMap();

        // single value field
        System.out.println("*** title ***");
        System.out.println((String) sourceAsMap.get("title"));

        List<Object> lines;

        // location
        System.out.println("*** loc ***");
        System.out.println((String) sourceAsMap.get("loc"));

        //
        String body = (String) sourceAsMap.get("body");
        if (body != null) {
            System.out.println("*** body ***");
            System.out.println(body);
        }

        System.out.println();

    }


    public void printSearchResponse(SearchResponse  sr) {

        if ( sr == null ) return;

        //
        // Search response
        System.out.println( "Rest status      " + sr.status() );
        System.out.println( "time             " + sr.getTook() );
        System.out.println( "Terminated early " + sr.isTerminatedEarly() );
        System.out.println( "Timed out        " + sr.isTimedOut() );

        //
        // Search hits
        SearchHits searchHits = sr.getHits();
        System.out.println( "Total hits       " + searchHits.getTotalHits() );
        System.out.println( "Max score        " + searchHits.getMaxScore() );

        for (SearchHit hit : searchHits) {
            printHit(hit);
        }

    }


    @Test
    public void test00() throws IOException {

        /*

        System.out.println("delete documents");

        ElasticSearch fd = new ElasticSearch();

        boolean openResult = fd.openIndex();

        System.out.println( "9jy2X2MBhxp6ZC5mynXa " + fd.deleteDocument("9jy2X2MBhxp6ZC5mynXa"));

        System.out.println( "9zy2X2MBhxp6ZC5my3VW " + fd.deleteDocument("9zy2X2MBhxp6ZC5my3VW") );

        System.out.println( "-Dy2X2MBhxp6ZC5my3Vy " + fd.deleteDocument("-Dy2X2MBhxp6ZC5my3Vy") );

        boolean closeResult = fd.closeIndex();

        System.out.println( "close index result " + closeResult );
*/

    }

    @Test
    public void test01() throws IOException {

        /*

        Gson gson = new Gson();
        System.out.println(gson.toJson(k1));

        System.out.println("test index open close");

        ElasticSearch fd = new ElasticSearch();

        boolean openResult = fd.openIndex();

        System.out.println( "open index result " + openResult );

        System.out.println( "index " + fd.indexDocument(k1) );
        System.out.println( "index " + fd.indexDocument(k2) );
        System.out.println( "index " + fd.indexDocument(k3) );

        boolean closeResult = fd.closeIndex();

        System.out.println( "close index result " + closeResult );
*/

    }


    @Test
    public void test02() throws IOException {

        /*

        System.out.println("test search all");

        ElasticSearch fd = new ElasticSearch();

        boolean openResult = fd.openIndex();

        SearchResponse sr = fd.getAll();

        printSearchResponse(sr);

        boolean closeResult = fd.closeIndex();
*/

    }


    @Test
    public void test03() throws IOException {

        /*


        System.out.println("test search ");

        ElasticSearch fd = new ElasticSearch();

        boolean openResult = fd.openIndex();

        SearchResponse sr = null;

        // match all documents
        //sr = fd.getAll();

        // match documents where all phrase terms appear in the same order contained in the search phrase
        // no more than 2 other terms may be between the search terms
        //sr = fd.phraseQuery("");
        sr = fd.phraseQuery("Earnings Per Share");

        // match documents where all phrase terms appear
        //sr = fd.allWordsQuery("");
        //sr = fd.allWordsQuery("considered important determine shares");

        // match documents where one or more phrase terms appear
        //sr = fd.wordsQuery("");
        //sr = fd.wordsQuery("rhymes fox dumpty");

        printSearchResponse(sr);

        boolean closeResult = fd.closeIndex();

*/
    }


    @Test
    public void test04() throws IOException {

        /*

        System.out.println("test search mapping");

        ElasticSearch fd = new ElasticSearch();

        boolean openResult = fd.openIndex();

        SearchResponse sr = fd.phraseQuery("little boe peep");
        SearchHits searchHits = sr.getHits();

        printSearchResponse(sr);

        FinanceKnowledge fk = new FinanceKnowledge(searchHits.getAt(0).getSourceAsMap());

        // single value field
        System.out.println( "*** title ***" );
        System.out.println( fk.getTitle() );

        List<String> lines;

        // tags
        System.out.println("*** tag ***");
        lines = fk.getTag();
        for ( String line : lines ) {
            System.out.println( line );
        }

        // body
        System.out.println("*** body ***");
        System.out.println( fk.getBody() );

        boolean closeResult = fd.closeIndex();
*/

    }



    @Test
    public void test05() throws IOException {

        /*

        System.out.println("test search titled");

        ElasticSearch fd = new ElasticSearch();

        boolean openResult = fd.openIndex();

        SearchResponse sr = fd.getTitles();

        printSearchResponse(sr);

        boolean closeResult = fd.closeIndex();

*/
    }

}
