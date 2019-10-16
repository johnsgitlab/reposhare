package com.jpw.raptor.scrape.yahoo.fields;

import com.jpw.raptor.scrape.yahoo.YahooPageContents;
import com.jpw.raptor.scrape.yahoo.YahooPageScrapper;
import org.junit.Test;

/**
 * Created by john on 3/23/18.
 */
public class YahooPageScrapperTest {


    @Test
    //
    // Risk Stats
    public void testit() {

        System.out.println("test it started");

        YahooPageScrapper pg = new YahooPageScrapper();
        YahooPageContents ypc = pg.readPage("XLC");
        //YahooPageContents ypc = pg.readPage("a234");
        //YahooPageContents ypc = pg.readPage("agg");
        //YahooPageContents ypc = pg.readPage("vtwnx");
        //YahooPageContents ypc = pg.readPage("ivv");
        //YahooPageContents ypc = pg.readPage("dgrw");
        //YahooPageContents ypc = pg.readPage("ITR");
        //YahooPageContents ypc = pg.readPage("FNDX");
        //YahooPageContents ypc = pg.readPage("PEMDX");

        if ( ypc.isFound() ) {
            System.out.println("symbol " + ypc.getSymbol());
            System.out.println("getLastUpdate " + ypc.getLastUpdate()); // equity profile
            System.out.println("Family " + ypc.getFamily()); // equity profile
            System.out.println("getAvgDailyVol " + ypc.getAvgDailyVol()); // equity profile

            System.out.println("getInception " + ypc.getInception()); // equity profile
            System.out.println("MarketCap " + ypc.getMarketCap()); // equity profile

            System.out.println("ExpenseRatio " + ypc.getExpenseRatio()); // equity profile
            System.out.println("getDividendYield " + ypc.getDividendYield()); // equity profile

            // Sector weighting
            System.out.println(" ");
            System.out.println("getRealestate " + ypc.getRealestate());
            System.out.println("getConsumerCyclical " + ypc.getConsumerCyclical());
            System.out.println("getBasicMaterials " + ypc.getBasicMaterials());
            System.out.println("getConsumerDefensive " + ypc.getConsumerDefensive());
            System.out.println("getTechnology " + ypc.getTechnology());
            System.out.println("getCommunicationServices " + ypc.getCommunicationServices());
            System.out.println("getFinancialServices " + ypc.getFinancialServices());
            System.out.println("getUtilities " + ypc.getUtilities());
            System.out.println("getIndustrials " + ypc.getIndustrials());
            System.out.println("getEnergy " + ypc.getEnergy());
            System.out.println("getHealthcare " + ypc.getHealthcare());

            //Risk
            System.out.println(" ");
            System.out.println("alpha " + ypc.getAlpha());
            System.out.println("beta " + ypc.getBeta());
            System.out.println("MeanAnnualReturn " + ypc.getMeanAnnualReturn());
            System.out.println("RSquared " + ypc.getRSquared());
            System.out.println("Deviation " + ypc.getDeviation());
            System.out.println("SharpeRatio " + ypc.getSharpeRatio());
            System.out.println("TreynorRatio " + ypc.getTreynorRatio());

            // Equity holdings
            System.out.println(" ");
            System.out.println("getPc " + ypc.getPc());
            System.out.println("getPs " + ypc.getPs());
            System.out.println("getPe " + ypc.getPe());
            System.out.println("getPb " + ypc.getPb());
            System.out.println("getEarningsGrowth " + ypc.getEarningsGrowth());
            System.out.println("getMedianMarketCap " + ypc.getMedianMarketCap());

            // Bond holding stats
            System.out.println(" ");
            System.out.println("getBondMaturity " + ypc.getBondMaturity());
            System.out.println("getBondDuration " + ypc.getBondDuration());
            System.out.println("getBondCreditQuality " + ypc.getBondCreditQuality());
            System.out.println("getBondBbPercent " + ypc.getBondBbPercent());
            System.out.println("getBondAaPercent " + ypc.getBondAaPercent());
            System.out.println("getBondAaaPercent " + ypc.getBondAaaPercent());
            System.out.println("getBondAPercent " + ypc.getBondAPercent());
            System.out.println("getBondOtherPercent " + ypc.getBondOtherPercent());
            System.out.println("getBondBPercent " + ypc.getBondBPercent());
            System.out.println("getBondBbbPercent " + ypc.getBondBbbPercent());
            System.out.println("getBondBelowbPercent " + ypc.getBondBelowbPercent());
            System.out.println("getBondUsPercent " + ypc.getBondUsPercent());

            // Position summary
            System.out.println(" ");
            System.out.println("BondPosition " + ypc.getBondPosition());
            System.out.println("CashPosition " + ypc.getCashPosition());
            System.out.println("ConvertiblePosition " + ypc.getConvertiblePosition());
            System.out.println("OtherPosition " + ypc.getOtherPosition());
            System.out.println("PreferredPosition " + ypc.getPreferredPosition());
            System.out.println("StockPosition " + ypc.getStockPosition());

            // Holdings
            System.out.println(" ");
            System.out.println("getTopHoldings " + ypc.getTopHoldings());
            //com.jpw.lib.model.Holding[] sArray = ypc.getHoldings();
            //for (com.jpw.lib.model.Holding h : sArray) {
            //    System.out.println(h.getSymbol() + " " + h.getHoldingName() + " " + h.getHoldingPercent());
            //}

            System.out.println("getOverview " + ypc.getOverview());

        } else {
            System.out.println("Equity not found "  );
        }

        System.out.println("test it finished");
    }


}
