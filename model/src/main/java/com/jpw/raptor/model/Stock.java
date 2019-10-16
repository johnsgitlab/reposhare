package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

/**
 * Created by john on 10/31/18.
 */
@Data
public class Stock {

    private String      symbol;             // manual entry
    private String      name;               // manual entry
    private Date       date;
    private Date       lastUpdate;
    private String      spIndex;            // manual entry
    private String      dowIndex;           // manual entry
    private String      russellIndex;       // manual entry
    private String      sector;             // summary profile sector
    private String      industry;           // summary profile industry
    private String      tracks;             // manual entry
    private String      exchange;           // market watch
    private int         recommendations;    // Recommendations
    private int         strongBuy;          // Recommendation strongBuy
    private int         buy;                // Recommendation buy
    private int         hold;               // Recommendation hold
    private int         sell;               // Recommendation sell
    private int         strongSell;         // Recommendation strongSell
    private long        marketCap;          // Summary detail marketCap
    private long        enterpriseValue;    // default key statistics enterpriseValue
    private long        operatingCashflow;  // financial data operatingCashflow
    private long        ebitda;             // financial data ebitda
    private long        freeCashflow;       // freeCashflow
    private long        totalCash;          // financial data totalCash
    private long        totalDebt;          // financial data totalDebt
    private long        totalRevenue;       // financial data totalRevenue
    private long        avgDailyVol;        // Summary detail averageDailyVolume10Day
    private double      dividendRate;       // Summary detail dividendRate
    private double      dividendYield;      // Summary detail dividendYield
    private double      beta;               // Summary detail beta
    private double      pe;                 // Summary detail trailingPE
    private double      peForward;          // Summary detail forwardPE
    private double      ps;                 // Summary detail priceToSalesTrailing12Months
    private double      pb;                 // default key statistics priceToBook
    private double      trailingEps;        // default key statistics trailingEps
    private double      pegRatio;           // default key statistics trailipegRationgEps
    private double      enterpriseToRevenue;// default key statistics enterpriseToRevenue
    private double      enterpriseToEbitda; // default key statistics enterpriseToEbitda
    private double      ebitdaMargins;      // financial data ebitdaMargins
    private double      profitMargins;      // financial data profitMargins
    private double      grossMargins;       // financial data grossMargins
    private double      revenueGrowth;      // financial data revenueGrowth
    private double      operatingMargins;   // financial data operatingMargins
    private double      earningsGrowth;     // financial data earningsGrowth
    private double      currentRatio;       // financial data currentRatio
    private double      returnOnAssets;     // financial data returnOnAssets
    private double      debtToEquity;       // financial data debtToEquity
    private double      returnOnEquity;     // financial data returnOnEquity
    private double      totalCashPerShare;  // financial data totalCashPerShare
    private double     ytd;
    private double     oneDay;          // 1
    private double     oneWeek;         // 5
    private double     twoWeeks;        // 10
    private double     fourWeeks;       // 20
    private double     threeMonths;     // 62
    private double     oneYear;         // 250
    private double     threeYears;      // 750
    private String      overview;           // summary profile longBusinessSummary


    protected void init() {
        // Provide default values
        symbol = null;
        name = null;

        lastUpdate = null;

        spIndex = null;
        dowIndex = null;
        russellIndex = null;
        sector = null;
        industry = null;
        tracks = null;
        exchange = null;

        recommendations = 0;
        strongBuy = 0;
        buy = 0;
        hold = 0;
        sell = 0;
        strongSell = 0;

        marketCap = 0L;
        enterpriseValue = 0L;
        operatingCashflow = 0L;
        ebitda = 0L;
        freeCashflow = 0L;
        totalCash = 0L;
        totalDebt = 0L;
        totalRevenue = 0L;
        avgDailyVol = 0L;

        dividendRate = 0.0;
        dividendYield = 0.0;
        beta = 0.0;
        pe = 0.0;
        peForward = 0.0;
        ps = 0.0;
        pb = 0.0;
        trailingEps = 0.0;
        pegRatio = 0.0;
        enterpriseToRevenue = 0.0;
        enterpriseToEbitda = 0.0;
        ebitdaMargins = 0.0;
        profitMargins = 0.0;
        grossMargins = 0.0;
        revenueGrowth = 0.0;
        operatingMargins = 0.0;
        earningsGrowth = 0.0;
        currentRatio = 0.0;
        returnOnAssets = 0.0;
        debtToEquity = 0.0;
        returnOnEquity = 0.0;
        totalCashPerShare = 0.0;


        overview = null;
    }

    // Constructor
    public Stock () {
        super();
        init();
    }


    public void updatePerformance(Performance rec) {
        date        = rec.getDate();
        ytd         = rec.getYtd();
        oneDay      = rec.getOneDay();
        oneWeek     = rec.getOneWeek();
        twoWeeks    = rec.getTwoWeeks();
        fourWeeks   = rec.getFourWeeks();
        threeMonths = rec.getThreeMonths();
        oneYear     = rec.getOneYear();
        threeYears  = rec.getThreeYears();
    }
}
