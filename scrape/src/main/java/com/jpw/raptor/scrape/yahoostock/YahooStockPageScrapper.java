package com.jpw.raptor.scrape.yahoostock;

import com.jpw.raptor.scrape.yahoo.YahooPageContents;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by john on 10/20/18.
 */
public class YahooStockPageScrapper {


    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public YahooStockPageContents readPage(String symbol) {

        // Yahoo replaces . with -
        String                  ticker = symbol.toUpperCase().replace('.','-');
        YahooStockPageContents  ypc    = new YahooStockPageContents();


        try {
            Document doc  = Jsoup.connect("https://finance.yahoo.com/quote/" + ticker).get();
            String   page = doc.toString();

            if ( page.contains("\"statusText\":\"Not Found\"") ) {
                ypc.setFound(false);
                System.out.println("*************** STOCK NOT FOUND *************************");
            } else {
                ypc.getDefaultKeyStatistics().parse(symbol, page);
                ypc.getFinancialData().parse(symbol, page);
                ypc.getRecommendation().parse(symbol, page);
                ypc.getSummaryDetail().parse(symbol, page);
                ypc.getSummaryProfile().parse(symbol, page);

                if ( !ypc.getDefaultKeyStatistics().isFound() ||
                     !ypc.getFinancialData().isFound() ||
                     !ypc.getSummaryDetail().isFound() ||
                     !ypc.getSummaryProfile().isFound() ) {
                    ypc.setFound(false);
                } else {
                    ypc.setFound(true);
                }
            }
        } catch (IOException e) {
            //System.out.println("ERROR IOException");
            logger.info( "### Page read failed for " + symbol + " " + e.getMessage() );
            System.out.println("### Page read failed for " + symbol + " " + e.getMessage());
            ypc.setFound(false);
        }

        return ypc;
    }

}
