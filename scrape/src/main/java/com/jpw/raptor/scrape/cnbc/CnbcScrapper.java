package com.jpw.raptor.scrape.cnbc;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by john on 12/4/18.
 */
public class CnbcScrapper {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public String readPage(String symbol) {

        // Yahoo replaces . with -
        String ticker = symbol.toUpperCase().replace('.','-');
        String result = null;


        try {
            Document doc  = Jsoup.connect("https://www.cnbc.com/quotes/?symbol=" + ticker).get();
            String   page = doc.toString();

            int start = page.indexOf("<meta itemprop=\"price\" content=\"");
            if ( start > 0 ) {
                start += 32;
                int end = page.indexOf("\"", start);
                result = page.substring(start,end);

            }

        } catch (IOException e) {
            //System.out.println("ERROR IOException");
            logger.info( "### Page read failed for " + symbol + " " + e.getMessage() );
            System.out.println("### Page read failed for " + symbol + " " + e.getMessage());

        }

        return result;
    }
}
