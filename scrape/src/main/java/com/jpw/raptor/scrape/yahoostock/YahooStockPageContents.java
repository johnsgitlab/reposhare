package com.jpw.raptor.scrape.yahoostock;


import com.jpw.raptor.model.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 10/21/18.
 */
@Getter
@Setter
public class YahooStockPageContents {

    private boolean                 found;
    private DefaultKeyStatistics    defaultKeyStatistics;
    private FinancialData           financialData;
    private Recommendation          recommendation;
    private SummaryDetail           summaryDetail;
    private SummaryProfile          summaryProfile;


    // Constructor
    public YahooStockPageContents () {
        found                   = false;
        defaultKeyStatistics    = new DefaultKeyStatistics();
        financialData           = new FinancialData();
        recommendation          = new Recommendation();
        summaryDetail           = new SummaryDetail();
        summaryProfile          = new SummaryProfile();
    }

    public void updateStockFromYahoo(Stock rec)  {

        // symbol manual entry
        // name manual entry

        // lastUpdate manual entry
        Date recentQuarter = null;
        if ( defaultKeyStatistics.getMostRecentQuarter() == null ) {
            recentQuarter = new Date(-1);
        } else {
            long dateAsLong = defaultKeyStatistics.getMostRecentQuarter().getLong() * 1000L;
            recentQuarter = new Date(dateAsLong);
        }
        rec.setLastUpdate(recentQuarter);

        // spIndex  manual entry
        // dowIndex  manual entry
        // russellIndex  manual entry
        rec.setSector(summaryProfile.getSector());
        rec.setIndustry(summaryProfile.getIndustry());
        // tracks  manual entry

        rec.setRecommendations(recommendation.getRecommendations());
        rec.setStrongBuy(recommendation.getStrongBuy());
        rec.setBuy(recommendation.getBuy());
        rec.setHold(recommendation.getHold());
        rec.setSell(recommendation.getSell());
        rec.setStrongSell(recommendation.getStrongSell());

        rec.setMarketCap(summaryDetail.getMarketCap().getLong());
        rec.setEnterpriseValue(defaultKeyStatistics.getEnterpriseValue().getLong());
        rec.setOperatingCashflow(financialData.getOperatingCashflow().getLong());
        rec.setEbitda(financialData.getEbitda().getLong());
        rec.setFreeCashflow(financialData.getFreeCashflow().getLong());
        rec.setTotalCash(financialData.getTotalCash().getLong());
        rec.setTotalDebt(financialData.getTotalDebt().getLong());
        rec.setTotalRevenue(financialData.getTotalRevenue().getLong());
        rec.setAvgDailyVol(summaryDetail.getAverageDailyVolume10Day().getLong());

        rec.setDividendRate(summaryDetail.getDividendRate().getDouble());
        rec.setDividendYield(summaryDetail.getDividendYield().getDouble());
        rec.setBeta(summaryDetail.getBeta().getDouble());
        rec.setPe(summaryDetail.getTrailingPE().getDouble());
        rec.setPeForward(summaryDetail.getForwardPE().getDouble());
        rec.setPs(summaryDetail.getPriceToSalesTrailing12Months().getDouble());
        rec.setPb(defaultKeyStatistics.getPriceToBook().getDouble());
        rec.setTrailingEps(defaultKeyStatistics.getTrailingEps().getDouble());
        rec.setPegRatio(defaultKeyStatistics.getPegRatio().getDouble());
        rec.setEnterpriseToRevenue(defaultKeyStatistics.getEnterpriseToRevenue().getDouble());
        rec.setEnterpriseToEbitda(defaultKeyStatistics.getEnterpriseToEbitda().getDouble());
        rec.setEbitdaMargins(financialData.getEbitdaMargins().getDouble());
        rec.setProfitMargins(financialData.getProfitMargins().getDouble());
        rec.setGrossMargins(financialData.getGrossMargins().getDouble());
        rec.setRevenueGrowth(financialData.getRevenueGrowth().getDouble());
        rec.setOperatingMargins(financialData.getOperatingMargins().getDouble());
        rec.setEarningsGrowth(financialData.getEarningsGrowth().getDouble());
        rec.setCurrentRatio(financialData.getCurrentRatio().getDouble());
        rec.setReturnOnAssets(financialData.getReturnOnAssets().getDouble());
        rec.setDebtToEquity(financialData.getDebtToEquity().getDouble());
        rec.setReturnOnEquity(financialData.getReturnOnEquity().getDouble());
        rec.setTotalCashPerShare(financialData.getTotalCashPerShare().getDouble());

        rec.setOverview(summaryProfile.getLongBusinessSummary());

    }

}
