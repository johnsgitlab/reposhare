package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by john on 11/20/18.
 */

public class SummaryData {

    private Date    date;

    private String 	assetClass;
    private String 	fundType;
    private String 	fundSubType;
    private String 	factor;

    private double 	ytd;
    private double 	ytdCount;

    private double 	oneDay;
    private double 	oneDayCount;

    private double 	oneWeek;
    private double 	oneWeekCount;

    private double 	twoWeeks;
    private double 	twoWeeksCount;

    private double 	fourWeeks;
    private double 	fourWeeksCount;

    private double 	threeMonths;
    private double 	threeMonthsCount;

    private double 	oneYear;
    private double 	oneYearCount;

    private DecimalFormat df;

    // Constructor
    public SummaryData () {

        date               = new Date(0);

        assetClass         = null;
        fundType           = null;
        fundSubType        = null;
        factor             = null;

        ytd                = 0.0;
        ytdCount           = 0.0;
        oneDay             = 0.0;
        oneDayCount        = 0.0;
        oneWeek            = 0.0;
        oneWeekCount       = 0.0;
        twoWeeks           = 0.0;
        twoWeeksCount      = 0.0;
        fourWeeks          = 0.0;
        fourWeeksCount     = 0.0;
        threeMonths        = 0.0;
        threeMonthsCount   = 0.0;
        oneYear            = 0.0;
        oneYearCount       = 0.0;

        df                 = new DecimalFormat("#.##");
    }


    public Date 	getDate()			     {return date;}
    public void 	setDate(Date v)	         {date = v;}

    public String 	getAssetClass()			 {return assetClass;}
    public void 	setAssetClass(String v)	 {assetClass = v;}

    public String 	getFundTpye()			 {return fundType;}
    public void 	setFundType(String v)	 {fundType = v;}

    public String 	getFundSubTpye()		 {return fundSubType;}
    public void 	setFundSubType(String v) {fundSubType = v;}

    public String 	getFactor()			     {return factor;}
    public void 	setFactor(String v)	     {factor = v;}

    public int      getCount()               { return (int) oneDayCount; }

    public double 	getYtd() {
        if ( ytdCount > 0.0)
            return Double.valueOf(df.format(ytd/ytdCount));
        else
            return -9999.0;
    }

    public void 	setYtd(double v) {
        if ( v > -9999.0 && v < 9999.0 ) {
            ytdCount++;
            ytd += v;
        }
    }

    public double 	getOneDay() {
        if ( oneDayCount > 0.0)
            return Double.valueOf(df.format(oneDay/oneDayCount));
        else
            return -9999.0;
    }

    public void 	setOneDay(double v) {
        if ( v > -9999.0 && v < 9999.0 ) {
            oneDayCount++;
            oneDay += v;
        }
    }

    public double 	getOneWeek() {
        if ( oneWeekCount > 0.0)
            return Double.valueOf(df.format(oneWeek/oneWeekCount));
        else
            return -9999.0;
    }

    public void 	setOneWeek(double v) {
        if ( v > -9999.0 && v < 9999.0 ) {
            oneWeekCount++;
            oneWeek += v;
        }
    }

    public double 	getTwoWeeks() {
        if ( twoWeeksCount > 0.0)
            return Double.valueOf(df.format(twoWeeks/twoWeeksCount));
        else
            return -9999.0;
    }

    public void 	setTwoWeeks(double v) {
        if ( v > -9999.0 && v < 9999.0 ) {
            twoWeeksCount++;
            twoWeeks += v;
        }
    }

    public double 	getFourWeeks() {
        if ( fourWeeksCount > 0.0)
            return Double.valueOf(df.format(fourWeeks/fourWeeksCount));
        else
            return -9999.0;
    }

    public void setFourWeeks(double v) {
        if ( v > -9999.0 && v < 9999.0 ) {
            fourWeeksCount++;
            fourWeeks += v;
        }
    }

    public double 	getThreeMonths() {
        if ( threeMonthsCount > 0.0)
            return Double.valueOf(df.format(threeMonths/threeMonthsCount));
        else
            return -9999.0;
    }

    public void 	setThreeMonths(double v) {
        if ( v > -9999.0 && v < 9999.0 ) {
            threeMonthsCount++;
            threeMonths += v;
        }
    }

    public double 	getOneYear() {
        if ( oneYearCount > 0.0)
            return Double.valueOf(df.format(oneYear/oneYearCount));
        else
            return -9999.0;
    }

    public void 	setOneYear(double v) {
        if ( v > -9999.0 && v < 9999.0 ) {
            oneYearCount++;
            oneYear += v;
        }
    }

}
