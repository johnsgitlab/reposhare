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

import java.util.Date;

/**
 * Created by john on 4/3/17.
 */
@Data
@Getter
@Setter
@Entity
@Table(
        name = "fund_tbl",
        indexes = {
                @Index(name = "idx_fund_asset_class", columnList = "asset_class"),
                @Index(name = "idx_fund_category", columnList = "category"),
                @Index(name = "idx_fund_family", columnList = "family"),
                @Index(name = "idx_fund_factor", columnList = "factor"),
                @Index(name = "idx_fund_fund_sub_type", columnList = "fund_sub_type"),
                @Index(name = "idx_fund_type", columnList = "fund_type"),
                @Index(name = "idx_fund_lipper_consistent", columnList = "lipper_consistent"),
                @Index(name = "idx_fund_lipper_total", columnList = "lipper_total"),
                @Index(name = "idx_fund_morning_stars", columnList = "morning_stars"),
                @Index(name = "idx_fund_tracks", columnList = "tracks"),
        }
)
public class Fund  {

    @Id
    @Column(name = "symbol", unique=true, nullable = false, columnDefinition="")
    private String     symbol;

    @Column(name = "category", columnDefinition="")
    private String     category;

    @Column(name = "fund_type", columnDefinition="")
    private String     fundType;

    @Column(name = "asset_class", columnDefinition="")
    private String     assetClass;

    @Column(name = "family", columnDefinition="")
    private String     family;

    @Column(name = "exchange", columnDefinition="")
    private String     exchange;

    @Column(name = "status", columnDefinition="")
    private String     status;

    @Column(name = "front_load", columnDefinition="")
    private double     frontLoad;

    @Column(name = "back_load", columnDefinition="")
    private double     backLoad;

    @Column(name = "expense_ratio", columnDefinition="")
    private double     expenseRatio;

    @Column(name = "minimum", columnDefinition="")
    private double     minimum ;

    @Column(name = "alpha", columnDefinition="")
    private double     alpha;

    @Column(name = "beta", columnDefinition="")
    private double     beta;

    @Column(name = "deviation", columnDefinition="")
    private double     deviation;

    @Column(name = "r_squared", columnDefinition="")
    private double     rSquared;

    @Column(name = "morning_stars", columnDefinition="")
    private int        morningStars;

    @Column(name = "lipper_total", columnDefinition="")
    private int        lipperTotal;

    @Column(name = "lipper_consistent", columnDefinition="")
    private int        lipperConsistent;

    @Column(name = "lipper_preservation", columnDefinition="")
    private int        lipperPreservation;

    @Column(name = "lipper_tax", columnDefinition="")
    private int        lipperTax;

    @Column(name = "lipper_expense", columnDefinition="")
    private int        lipperExpense;

    @Column(name = "ytd", columnDefinition="")
    private double     ytd;

    @Column(name = "four_weeks", columnDefinition="")
    private double     fourWeeks;       // 20

    @Column(name = "three_months", columnDefinition="")
    private double     threeMonths;     // 62

    @Column(name = "one_year", columnDefinition="")
    private double     oneYear;         // 250

    @Column(name = "three_years", columnDefinition="")
    private double     threeYears;      // 750

    @Column(name = "basic_materials", columnDefinition="")
    private double     basicMaterials;

    @Column(name = "consumer_cyclical", columnDefinition="")
    private double     consumerCyclical;

    @Column(name = "financial_services", columnDefinition="")
    private double     financialServices;

    @Column(name = "realestate", columnDefinition="")
    private double     realestate;

    @Column(name = "consumer_defensive", columnDefinition="")
    private double     consumerDefensive;

    @Column(name = "healthcare", columnDefinition="")
    private double     healthcare;

    @Column(name = "utilities", columnDefinition="")
    private double     utilities;

    @Column(name = "communication_services", columnDefinition="")
    private double     communicationServices;

    @Column(name = "energy", columnDefinition="")
    private double     energy;

    @Column(name = "industrials", columnDefinition="")
    private double     industrials;

    @Column(name = "technology", columnDefinition="")
    private double     technology;

    @Column(name = "name", columnDefinition="")
    private String     name;

    @Column(name = "overview", columnDefinition="")
    private String     overview;

    @Column(name = "date_tx", columnDefinition="")
    @Temporal(TemporalType.DATE)
    private Date       date;

    @Column(name = "dividend_yield", columnDefinition="")
    private double     dividendYield;

    @Column(name = "one_day", columnDefinition="")
    private double     oneDay;          // 1

    @Column(name = "one_week", columnDefinition="")
    private double     oneWeek;         // 5

    @Column(name = "two_weeks", columnDefinition="")
    private double     twoWeeks;        // 10

    @Column(name = "factor", columnDefinition="")
    private String     factor;

    @Column(name = "fund_sub_type", columnDefinition="")
    private String     fundSubType;

    @Column(name = "morning_rating", columnDefinition="")
    private String     morningRating;

    @Column(name = "tracks", columnDefinition="")
    private String     tracks;

    @Column(name = "last_update", columnDefinition="")
    @Temporal(TemporalType.DATE)
    private Date       lastUpdate;

    @Column(name = "inception", columnDefinition="")
    private Date       inception;

    @Column(name = "market_cap", columnDefinition="")
    private double     marketCap;

    @Column(name = "mean_annual_return", columnDefinition="")
    private double     meanAnnualReturn;

    @Column(name = "sharpe_ratio", columnDefinition="")
    private double     sharpeRatio;

    @Column(name = "treynor_ratio", columnDefinition="")
    private double     treynorRatio;

    @Column(name = "ps", columnDefinition="")
    private double     ps;

    @Column(name = "pc", columnDefinition="")
    private double     pc;

    @Column(name = "earnings_growth", columnDefinition="")
    private double     earningsGrowth;

    @Column(name = "median_market_cap", columnDefinition="")
    private double     medianMarketCap;

    @Column(name = "bond_maturity", columnDefinition="")
    private double     bondMaturity;

    @Column(name = "bond_duration", columnDefinition="")
    private double     bondDuration;

    @Column(name = "bond_credit_quality", columnDefinition="")
    private double     bondCreditQuality;

    @Column(name = "bond_aaa_percent", columnDefinition="")
    private double     bondAaaPercent;

    @Column(name = "bond_aa_percent", columnDefinition="")
    private double     bondAaPercent;

    @Column(name = "bond_a_percent", columnDefinition="")
    private double     bondAPercent;

    @Column(name = "bond_bbb_percent", columnDefinition="")
    private double     bondBbbPercent;

    @Column(name = "bond_bb_percent", columnDefinition="")
    private double     bondBbPercent;

    @Column(name = "bond_b_percent", columnDefinition="")
    private double     bondBPercent;

    @Column(name = "bond_belowb_percent", columnDefinition="")
    private double     bondBelowbPercent;

    @Column(name = "bond_us_percent", columnDefinition="")
    private double     bondUsPercent;

    @Column(name = "bond_position", columnDefinition="")
    private double     bondPosition;

    @Column(name = "bond_other_percent", columnDefinition="")
    private double     bondOtherPercent;

    @Column(name = "cash_position", columnDefinition="")
    private double     cashPosition;

    @Column(name = "convertible_position", columnDefinition="")
    private double     convertiblePosition;

    @Column(name = "other_position", columnDefinition="")
    private double     otherPosition;

    @Column(name = "preferred_position", columnDefinition="")
    private double     preferredPosition;

    @Column(name = "stock_position", columnDefinition="")
    private double     stockPosition;

    @Column(name = "top_holdings", columnDefinition="")
    private String     topHoldings;

    @Column(name = "pe", columnDefinition="")
    private double     pe;

    @Column(name = "pb", columnDefinition="")
    private double     pb;

    protected void init() {
        // Provide default values
        symbol = null;
        name = null;

        category = null;
        tracks = null;
        lastUpdate = null;
        family = null;
        exchange = null;

        status = null;
        minimum = -9999.0;
        frontLoad = -9999.0;
        backLoad = -9999.0;
        inception = null;
        marketCap = -9999.0;
        expenseRatio = -9999.0;
        dividendYield = -9999.0;

        morningRating = null;
        morningStars  = -9999;
        lipperTotal = -9999;
        lipperConsistent = -9999;
        lipperPreservation = -9999;
        lipperTax = -9999;
        lipperExpense = -9999;

        date        = null;
        ytd         = -9999.0;
        oneDay      = -9999.0;
        oneWeek     = -9999.0;
        twoWeeks    = -9999.0;
        fourWeeks   = -9999.0;
        threeMonths = -9999.0;
        oneYear     = -9999.0;
        threeYears  = -9999.0;

        basicMaterials = 0.0;
        consumerCyclical = 0.0;
        financialServices = 0.0;
        realestate = 0.0;
        consumerDefensive = 0.0;
        healthcare = 0.0;
        utilities = 0.0;
        communicationServices = 0.0;
        energy = 0.0;
        industrials = 0.0;
        technology = 0.0;

        alpha = 0.0;
        beta = 0.0;
        meanAnnualReturn = 0.0;
        rSquared = 0.0;
        deviation = 0.0;
        sharpeRatio = 0.0;
        treynorRatio = 0.0;

        pe = 0.0;
        pb = 0.0;
        ps = 0.0;
        pc = 0.0;
        earningsGrowth = 0.0;
        medianMarketCap = 0.0;

        bondMaturity = 0.0;
        bondDuration = 0.0;
        bondCreditQuality = 0.0;
        bondAaaPercent = 0.0;
        bondAaPercent = 0.0;
        bondAPercent = 0.0;
        bondBbbPercent = 0.0;
        bondBbPercent = 0.0;
        bondBPercent = 0.0;
        bondBelowbPercent = 0.0;
        bondUsPercent = 0.0;
        bondOtherPercent = 0.0;

        bondPosition = 0.0;
        cashPosition = 0.0;
        convertiblePosition = 0.0;
        otherPosition = 0.0;
        preferredPosition = 0.0;
        stockPosition = 0.0;

        topHoldings = null;
        overview = null;
    }

    // Constructor
    public Fund () {
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
