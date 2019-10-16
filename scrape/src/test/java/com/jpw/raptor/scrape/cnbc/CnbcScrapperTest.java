package com.jpw.raptor.scrape.cnbc;

import com.jpw.raptor.scrape.yahoostock.YahooStockPageContents;
import com.jpw.raptor.scrape.yahoostock.YahooStockPageScrapper;
import org.junit.Test;

/**
 * Created by john on 12/4/18.
 */
public class CnbcScrapperTest {

    @Test
    //
    // Risk Stats
    public void testit() {

        System.out.println("test it started");
        String y2 = "US2Y";
        String y10 = "US10Y";
        CnbcScrapper pg = new CnbcScrapper();
        String result = pg.readPage(y10);

        System.out.println("Result " + result);

        //  2 year 2.799
        // 10 year 2.915
    }

}
