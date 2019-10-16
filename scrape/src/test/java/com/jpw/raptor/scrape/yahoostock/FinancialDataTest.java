package com.jpw.raptor.scrape.yahoostock;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by john on 10/30/18.
 */
public class FinancialDataTest {

    @Test
    public void testit() {

        System.out.println("FinancialDataTest " );

        TestData         td  = new TestData();
        FinancialData    rec = new FinancialData();

        /*
        */

        rec.parse(" ", td.financialDataRaw);
        assertEquals(Math.round(0.30764*100.0)/100.0, rec.getEbitdaMargins().getDouble(), 0.0000000001);
        assertEquals(Math.round(0.21983999*100.0)/100.0, rec.getProfitMargins().getDouble(), 0.0000000001);
        assertEquals(Math.round(0.38267*100.0)/100.0, rec.getGrossMargins().getDouble(), 0.0000000001);
        assertEquals(73032998912l, rec.getOperatingCashflow().getLong());
        assertEquals(Math.round(0.173*100.0)/100.0, rec.getRevenueGrowth().getDouble(), 0.0000000001);
        assertEquals(Math.round(0.26599002*100.0)/100.0, rec.getOperatingMargins().getDouble(), 0.0000000001);
        assertEquals(78533001216l, rec.getEbitda().getLong());
        assertEquals(41444749312l, rec.getFreeCashflow().getLong());
        assertEquals(Math.round(0.401*100.0)/100.0, rec.getEarningsGrowth().getDouble(), 0.0000000001);
        assertEquals(Math.round(1.307*100.0)/100.0, rec.getCurrentRatio().getDouble(), 0.0000000001);
        assertEquals(Math.round(0.12222999*100.0)/100.0, rec.getReturnOnAssets().getDouble(), 0.0000000001);
        assertEquals(Math.round(99.696*100.0)/100.0, rec.getDebtToEquity().getDouble(), 0.0000000001);
        assertEquals(Math.round(0.45373002*100.0)/100.0,  rec.getReturnOnEquity().getDouble(), 0.0000000001);
        assertEquals(70969999360l, rec.getTotalCash().getLong());
        assertEquals(114600001536l, rec.getTotalDebt().getLong());
        assertEquals(255273992192l, rec.getTotalRevenue().getLong());
        assertEquals(Math.round(14.694*100.0)/100.0, rec.getTotalCashPerShare().getDouble(), 0.0000000001);

        /*
        rec.parse(td.financialDataRaw);
        System.out.println("ebitdaMargins " + rec.getEbitdaMargins().getRaw() + " ");
        System.out.println("profitMargins " + rec.getProfitMargins().getRaw() + " ");
        System.out.println("grossMargins " + rec.getGrossMargins().getRaw() + " ");
        System.out.println("operatingCashflow " + rec.getOperatingCashflow().getRaw() + " ");
        System.out.println("revenueGrowth " + rec.getRevenueGrowth().getRaw() + " ");
        System.out.println("operatingMargins " + rec.getOperatingMargins().getRaw() + " ");
        System.out.println("ebitda " + rec.getEbitda().getRaw() + " ");
        System.out.println("freeCashflow " + rec.getFreeCashflow().getRaw() + " ");
        System.out.println("earningsGrowth " + rec.getEarningsGrowth().getRaw() + " ");
        System.out.println("currentRatio " + rec.getCurrentRatio().getRaw() + " ");
        System.out.println("returnOnAssets " + rec.getReturnOnAssets().getRaw() + " ");;
        System.out.println("debtToEquity " + rec.getDebtToEquity().getRaw() + " ");
        System.out.println("returnOnEquity " + rec.getReturnOnEquity().getRaw() + " ");
        System.out.println("totalCash " + rec.getTotalCash().getRaw() + " ");
        System.out.println("totalDebt " + rec.getTotalDebt().getRaw() + " ");
        System.out.println("totalRevenue " + rec.getTotalRevenue().getRaw() + " ");
        System.out.println("totalCashPerShare " + rec.getTotalCashPerShare().getRaw() + " ");
        */
    }

}
