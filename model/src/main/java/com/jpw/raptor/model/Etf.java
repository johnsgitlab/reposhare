package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

/**
 * Created by john on 4/3/17.
 */
@Data
public class Etf {

    private String     symbol;
    private String     name;
    private Date       date;
    private Date       lastUpdate;
    private String     fundType;
    private String     category;
    private String     assetClass;
    private String     underlyingIndex;
    private String     overview;
    private double     marketCap;
    private double     shares;
    private double     avgDailyVol;
    private double     dividendYield;
    private double     beta;
    private double     pe;
    private double     pb;
    private double     expenseRatio;
    private double     ytd;
    private double     oneDay;          // 1
    private double     oneWeek;         // 5
    private double     twoWeeks;        // 10
    private double     fourWeeks;       // 20
    private double     threeMonths;     // 62
    private double     oneYear;         // 250
    private double     threeYears;      // 750
    private int        morningStars;
    private String     tracks;
    private double     basicMaterials;
    private double     consumerCyclical;
    private double     financialServices;
    private double     realestate;
    private double     consumerDefensive;
    private double     healthcare;
    private double     utilities;
    private double     communicationServices;
    private double     energy;
    private double     industrials;
    private double     technology;
    private String     factor;
    private String     fundSubType;
    private String     morningRating;
    private Date       inception;
    private String     family;
    private String     exchange;
    private double     alpha;
    private double     meanAnnualReturn;
    private double     sharpeRatio;
    private double     treynorRatio;
    private double     ps;
    private double     pc;
    private double     earningsGrowth;
    private double     medianMarketCap;
    private double     bondMaturity;
    private double     bondDuration;
    private double     bondCreditQuality;
    private double     bondAaaPercent;
    private double     bondAaPercent;
    private double     bondAPercent;
    private double     bondBbbPercent;
    private double     bondBbPercent;
    private double     bondBPercent;
    private double     bondBelowbPercent;
    private double     bondUsPercent;
    private double     bondPosition;
    private double     bondOtherPercent;
    private double     cashPosition;
    private double     convertiblePosition;
    private double     otherPosition;
    private double     preferredPosition;
    private double     stockPosition;
    private String     topHoldings;
    private double     rSquared;
    private double     deviation;
    private int        lipperTotal;
    private int        lipperConsistent;
    private int        lipperPreservation;
    private int        lipperTax;
    private int        lipperExpense;


    protected void init() {
        // Provide default values
        symbol = null;
        name = null;

        category = null;
        tracks = null;
        lastUpdate = null;
        family = null;
        exchange = null;

        underlyingIndex = null;
        avgDailyVol = -9999.0;
        inception = null;
        marketCap = -9999.0;
        expenseRatio = -9999.0;
        dividendYield = -9999.0;

        date        = null;
        ytd         = -9999.0;
        oneDay      = -9999.0;
        oneWeek     = -9999.0;
        twoWeeks    = -9999.0;
        fourWeeks   = -9999.0;
        threeMonths = -9999.0;
        oneYear     = -9999.0;
        threeYears  = -9999.0;

        morningRating = null;
        morningStars  = -9999;
        lipperTotal = -9999;
        lipperConsistent = -9999;
        lipperPreservation = -9999;
        lipperTax = -9999;
        lipperExpense = -9999;

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
    public Etf () {
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
