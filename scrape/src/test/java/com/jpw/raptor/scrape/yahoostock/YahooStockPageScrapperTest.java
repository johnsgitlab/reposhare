package com.jpw.raptor.scrape.yahoostock;

import org.junit.Test;

/**
 * Created by john on 11/4/18.
 */
public class YahooStockPageScrapperTest {

    @Test
    //
    // Risk Stats
    public void testit() {

        System.out.println("test it started");
        String symbol = "BF-B";
        YahooStockPageScrapper pg =  new YahooStockPageScrapper();
        YahooStockPageContents ypc = pg.readPage(symbol);
        //YahooStockPageContents ypc = pg.readPage("A234");
        //YahooStockPageContents ypc = pg.readPage("XLF");

        if ( ypc.isFound() == false ) {
            System.out.println("Not Found " + symbol);
            System.out.println("DefaultKeyStatistics " + ypc.getDefaultKeyStatistics().isFound() );
            System.out.println("FinancialData " + ypc.getFinancialData().isFound() );
            System.out.println("Recommendation " + ypc.getRecommendation().isFound() );
            System.out.println("SummaryDetail " +  ypc.getSummaryDetail().isFound() );
            System.out.println("SummaryProfile " + ypc.getSummaryProfile().isFound() );
            return;
        }

        // Default Key Statistics
        System.out.println("Default Key Statistics");
        System.out.println("trailingEps         " + ypc.getDefaultKeyStatistics().getTrailingEps().getRaw());
        System.out.println("enterpriseValue     " + ypc.getDefaultKeyStatistics().getEnterpriseValue().getRaw());
        System.out.println("pegRatio            " + ypc.getDefaultKeyStatistics().getPegRatio().getRaw());
        System.out.println("priceToBook         " + ypc.getDefaultKeyStatistics().getPriceToBook().getRaw());
        System.out.println("enterpriseToRevenue " + ypc.getDefaultKeyStatistics().getEnterpriseToRevenue().getRaw());
        System.out.println("enterpriseToEbitda  " + ypc.getDefaultKeyStatistics().getEnterpriseToEbitda().getRaw());
        System.out.println("");

        //Financial data
        System.out.println("Financial data");
        System.out.println("ebitdaMargins " + ypc.getFinancialData().getEbitdaMargins().getRaw());
        System.out.println("profitMargins " + ypc.getFinancialData().getProfitMargins().getRaw());
        System.out.println("grossMargins " + ypc.getFinancialData().getGrossMargins().getRaw());
        System.out.println("operatingCashflow " + ypc.getFinancialData().getOperatingCashflow().getRaw());
        System.out.println("revenueGrowth " + ypc.getFinancialData().getRevenueGrowth().getRaw());
        System.out.println("operatingMargins " + ypc.getFinancialData().getOperatingMargins().getRaw());
        System.out.println("ebitda " + ypc.getFinancialData().getEbitda().getRaw());
        System.out.println("freeCashflow " + ypc.getFinancialData().getFreeCashflow().getRaw());
        System.out.println("earningsGrowth " + ypc.getFinancialData().getEarningsGrowth().getRaw());
        System.out.println("currentRatio " + ypc.getFinancialData().getCurrentRatio().getRaw());
        System.out.println("returnOnAssets " + ypc.getFinancialData().getReturnOnAssets().getRaw());;
        System.out.println("debtToEquity " + ypc.getFinancialData().getDebtToEquity().getRaw());
        System.out.println("returnOnEquity " + ypc.getFinancialData().getReturnOnEquity().getRaw());
        System.out.println("totalCash " + ypc.getFinancialData().getTotalCash().getRaw());
        System.out.println("totalDebt " + ypc.getFinancialData().getTotalDebt().getRaw());
        System.out.println("totalRevenue " + ypc.getFinancialData().getTotalRevenue().getRaw());
        System.out.println("totalCashPerShare " + ypc.getFinancialData().getTotalCashPerShare().getRaw());
        System.out.println("");

        // Recommendation
        System.out.println("Recommendation");
        System.out.println("recommendations  " + ypc.getRecommendation().getRecommendations());
        System.out.println("strongBuy  " + ypc.getRecommendation().getStrongBuy());
        System.out.println("Buy        " + ypc.getRecommendation().getBuy());
        System.out.println("hold       " + ypc.getRecommendation().getHold());
        System.out.println("sell       " + ypc.getRecommendation().getSell());
        System.out.println("strongSell " + ypc.getRecommendation().getStrongSell());
        System.out.println("");

        // Summary detail
        System.out.println("Summary detail");
        System.out.println("averageDailyVolume10Day " + ypc.getSummaryDetail().getAverageDailyVolume10Day().getRaw());
        System.out.println("beta " + ypc.getSummaryDetail().getBeta().getRaw());
        System.out.println("trailing " + ypc.getSummaryDetail().getTrailingPE().getRaw());
        System.out.println("forwardPE " + ypc.getSummaryDetail().getForwardPE().getRaw());
        System.out.println("marketCap " + ypc.getSummaryDetail().getMarketCap().getRaw());
        System.out.println("priceToSalesTrailing12Months " + ypc.getSummaryDetail().getPriceToSalesTrailing12Months().getRaw());
        System.out.println("dividendRate " + ypc.getSummaryDetail().getDividendRate().getRaw());
        System.out.println("dividendYield " + ypc.getSummaryDetail().getDividendYield().getRaw());
        System.out.println("");

        // Summary profile
        System.out.println("Summary profile");
        System.out.println("sector " + ypc.getSummaryProfile().getSector());
        System.out.println("industry " + ypc.getSummaryProfile().getIndustry());
        System.out.println("longBusinessSummary " + ypc.getSummaryProfile().getLongBusinessSummary());
        System.out.println("");
    }

}
