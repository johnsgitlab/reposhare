package com.jpw.raptor.scrape.yahoostock;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by john on 10/30/18.
 */
public class DefaultKeyStatisticsTest {

    @Test
    public void testit() {

        System.out.println("DefaultKeyStatisticsTest " );

        TestData                td  = new TestData();
        DefaultKeyStatistics    rec = new DefaultKeyStatistics();

        /*
         */
        rec.parse(" ", td.defaultKeyStatisticsRaw);
        assertEquals(Math.round(11.038*100.0)/100.0, rec.getTrailingEps().getDouble(), 0.000000001);
        assertEquals(1116404842496l, rec.getEnterpriseValue().getLong());
        assertEquals(Math.round(1.71*100.0)/100.0, rec.getPegRatio().getDouble(), 0.000000001);
        assertEquals(Math.round(9.101327*100.0)/100.0, rec.getPriceToBook().getDouble(), 0.000000001);
        assertEquals(Math.round(4.373*100.0)/100.0, rec.getEnterpriseToRevenue().getDouble(), 0.000000001);
        assertEquals(Math.round(14.216*100.0)/100.0, rec.getEnterpriseToEbitda().getDouble(), 0.000000001);

        /*
        rec.parse(td.defaultKeyStatisticsRaw);
        System.out.println("trailingEps         " + rec.getTrailingEps().getRaw() + " "  + rec.getTrailingEps().getDouble());
        System.out.println("enterpriseValue     " + rec.getEnterpriseValue().getRaw() + " " + rec.getEnterpriseValue().getLong());
        System.out.println("pegRatio            " + rec.getPegRatio().getRaw() + " " + rec.getPegRatio().getDouble());
        System.out.println("priceToBook         " + rec.getPriceToBook().getRaw() + " "+ rec.getPriceToBook().getDouble());
        System.out.println("enterpriseToRevenue " + rec.getEnterpriseToRevenue().getRaw() + " " + rec.getEnterpriseToRevenue().getDouble());
        System.out.println("enterpriseToEbitda  " + rec.getEnterpriseToEbitda().getRaw() + " " + rec.getEnterpriseToEbitda().getDouble());

        */
    }

}
