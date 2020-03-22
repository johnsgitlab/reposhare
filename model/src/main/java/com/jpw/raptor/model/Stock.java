package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.IdClass;
import javax.persistence.EmbeddedId;
import javax.persistence.Embeddable;

import java.util.Date;

/**
 * Created by john on 10/31/18.
 */
@Data
@Entity
@Table(
        name = "stock_tbl",
        indexes = {
                @Index(name = "idx_stock_dow", columnList = "dow"),
                @Index(name = "idx_stock_industry", columnList = "industry"),
                @Index(name = "idx_stock_russell", columnList = "russell"),
                @Index(name = "idx_stock_sector", columnList = "sector"),
                @Index(name = "idx_stock_sp", columnList = "sp"),
                @Index(name = "idx_stock_tracks", columnList = "tracks")
        }
)
public class Stock {

    @Id
    @Column(name = "symbol", columnDefinition="")
    private String      symbol;             // manual entry

    @Column(name = "name", columnDefinition="")
    private String      name;               // manual entry

    @Column(name = "date_tx", columnDefinition="")
    @Temporal(TemporalType.DATE)
    private Date       date;

    @Column(name = "last_update", columnDefinition="")
    @Temporal(TemporalType.DATE)
    private Date       lastUpdate;

    @Column(name = "sp_index", columnDefinition="")
    private String      spIndex;            // manual entry

    @Column(name = "dow_index", columnDefinition="")
    private String      dowIndex;           // manual entry

    @Column(name = "russell_index", columnDefinition="")
    private String      russellIndex;       // manual entry

    @Column(name = "sector", columnDefinition="")
    private String      sector;             // summary profile sector

    @Column(name = "industry", columnDefinition="")
    private String      industry;           // summary profile industry

    @Column(name = "tracks", columnDefinition="")
    private String      tracks;             // manual entry

    @Column(name = "exchange", columnDefinition="")
    private String      exchange;             // market watch

    @Column(name = "recommendations", columnDefinition="")
    private int         recommendations;    // Recommendations

    @Column(name = "strong_buy", columnDefinition="")
    private int         strongBuy;          // Recommendation strongBuy

    @Column(name = "buy", columnDefinition="")
    private int         buy;                // Recommendation buy

    @Column(name = "hold", columnDefinition="")
    private int         hold;               // Recommendation hold

    @Column(name = "sell", columnDefinition="")
    private int         sell;               // Recommendation sell

    @Column(name = "strong_sell", columnDefinition="")
    private int         strongSell;         // Recommendation strongSell

    @Column(name = "market_cap", columnDefinition="")
    private long        marketCap;          // Summary detail marketCap

    @Column(name = "enterprise_value", columnDefinition="")
    private long        enterpriseValue;    // default key statistics enterpriseValue

    @Column(name = "operating_cashflow", columnDefinition="")
    private long        operatingCashflow;  // financial data operatingCashflow

    @Column(name = "ebitda", columnDefinition="")
    private long        ebitda;             // financial data ebitda

    @Column(name = "free_cashflow", columnDefinition="")
    private long        freeCashflow;       // freeCashflow

    @Column(name = "total_cash", columnDefinition="")
    private long        totalCash;          // financial data totalCash

    @Column(name = "total_debt", columnDefinition="")
    private long        totalDebt;          // financial data totalDebt

    @Column(name = "total_revenue", columnDefinition="")
    private long        totalRevenue;       // financial data totalRevenue

    @Column(name = "avg_daily_vol", columnDefinition="")
    private long        avgDailyVol;        // Summary detail averageDailyVolume10Day

    @Column(name = "dividend_rate", columnDefinition="")
    private double      dividendRate;       // Summary detail dividendRate

    @Column(name = "dividend_yield", columnDefinition="")
    private double      dividendYield;      // Summary detail dividendYield

    @Column(name = "beta", columnDefinition="")
    private double      beta;               // Summary detail beta

    @Column(name = "pe", columnDefinition="")
    private double      pe;                 // Summary detail trailingPE

    @Column(name = "pe_forward", columnDefinition="")
    private double      peForward;          // Summary detail forwardPE

    @Column(name = "ps", columnDefinition="")
    private double      ps;                 // Summary detail priceToSalesTrailing12Months

    @Column(name = "pb", columnDefinition="")
    private double      pb;                 // default key statistics priceToBook

    @Column(name = "trailing_eps", columnDefinition="")
    private double      trailingEps;        // default key statistics trailingEps

    @Column(name = "peg_ratio", columnDefinition="")
    private double      pegRatio;           // default key statistics trailipegRationgEps

    @Column(name = "enterprise_to_revenue", columnDefinition="")
    private double      enterpriseToRevenue;// default key statistics enterpriseToRevenue

    @Column(name = "enterprise_to_ebitda", columnDefinition="")
    private double      enterpriseToEbitda; // default key statistics enterpriseToEbitda

    @Column(name = "ebitda_margins", columnDefinition="")
    private double      ebitdaMargins;      // financial data ebitdaMargins

    @Column(name = "profit_margins", columnDefinition="")
    private double      profitMargins;      // financial data profitMargins

    @Column(name = "gross_margins", columnDefinition="")
    private double      grossMargins;       // financial data grossMargins

    @Column(name = "revenue_growth", columnDefinition="")
    private double      revenueGrowth;      // financial data revenueGrowth

    @Column(name = "operating_margins", columnDefinition="")
    private double      operatingMargins;   // financial data operatingMargins

    @Column(name = "earnings_growth", columnDefinition="")
    private double      earningsGrowth;     // financial data earningsGrowth

    @Column(name = "current_ratio", columnDefinition="")
    private double      currentRatio;       // financial data currentRatio

    @Column(name = "return_on_assets", columnDefinition="")
    private double      returnOnAssets;     // financial data returnOnAssets

    @Column(name = "debt_to_equity", columnDefinition="")
    private double      debtToEquity;       // financial data debtToEquity

    @Column(name = "return_on_equity", columnDefinition="")
    private double      returnOnEquity;     // financial data returnOnEquity

    @Column(name = "total_cash_per_share", columnDefinition="")
    private double      totalCashPerShare;  // financial data totalCashPerShare

    @Column(name = "ytd", columnDefinition="")
    private double     ytd;

    @Column(name = "one_day", columnDefinition="")
    private double     oneDay;          // 1

    @Column(name = "one_week", columnDefinition="")
    private double     oneWeek;         // 5

    @Column(name = "two_weeks", columnDefinition="")
    private double     twoWeeks;        // 10

    @Column(name = "four_weeks", columnDefinition="")
    private double     fourWeeks;       // 20

    @Column(name = "three_months", columnDefinition="")
    private double     threeMonths;     // 62

    @Column(name = "one_year", columnDefinition="")
    private double     oneYear;         // 250

    @Column(name = "three_years", columnDefinition="")
    private double     threeYears;      // 750

    @Column(name = "last_update", columnDefinition="")
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
