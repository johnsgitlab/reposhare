package com.jpw.raptor.scrape.yahoostock;

import com.jpw.raptor.model.Stock;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by john on 10/31/18.
 */
public class SummaryDetailTest {

    @Test
    public void testit() {

        System.out.println("SummaryDetailTest " );

        TestData         td  = new TestData();
        SummaryDetail    rec = new SummaryDetail();

        /*
        */
        rec.parse(" ", td.summaryDetailRaw);
        assertEquals(34335300l, rec.getAverageDailyVolume10Day().getLong());
        assertEquals(Math.round(1.266481*100.0)/100.0, rec.getBeta().getDouble(), 0.000000001);
        assertEquals(Math.round(19.570574*100.0)/100.0, rec.getTrailingPE().getDouble(), 0.000000001);
        assertEquals(Math.round(15.733431*100.0)/100.0, rec.getForwardPE().getDouble(), 0.000000001);
        assertEquals(1043361497088l, rec.getMarketCap().getLong());
        assertEquals(Math.round(4.087222*100.0)/100.0, rec.getPriceToSalesTrailing12Months().getDouble(), 0.000000001);
        assertEquals(Math.round(2.92*100.0)/100.0, rec.getDividendRate().getDouble(), 0.000000001);
        assertEquals(Math.round(0.013099999*100.0)/100.0, rec.getDividendYield().getDouble(), 0.000000001);

        String symbol = "AA";

        YahooStockPageScrapper yps = new YahooStockPageScrapper();

        YahooStockPageContents ypc = yps.readPage(symbol);
        Stock stock = new Stock();

        //ypc.updateStockFromYahoo(stock);
        /*
        rec.parse(td.summaryDetailRaw);
        System.out.println("averageDailyVolume10Day " + rec.getAverageDailyVolume10Day().getRaw());
        System.out.println("beta " + rec.getBeta().getRaw());
        System.out.println("trailing " + rec.getTrailingPE().getRaw());
        System.out.println("forwardPE " + rec.getForwardPE().getRaw());
        System.out.println("marketCap " + rec.getMarketCap().getRaw());
        System.out.println("priceToSalesTrailing12Months " + rec.getPriceToSalesTrailing12Months().getRaw());
        System.out.println("dividendRate " + rec.getDividendRate().getRaw());
        System.out.println("dividendYield " + rec.getDividendYield().getRaw());
        */

    }
}
