package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.text.StringEscapeUtils;

import java.util.Date;

/**
 * Created by john on 11/13/18.
 */
@Getter
@Setter
public class StockListModel {

    private String 	symbol;
    private String 	symbolEncoded;
    private String 	name;
    private String  sector;
    private String  industry;
    private Date    date;
    private String 	spIndex;
    private String 	dowIndex;
    private String 	russellIndex;
    private double 	ytd;
    private double 	oneDay;
    private double 	oneWeek;
    private double 	twoWeeks;
    private double 	fourWeeks;
    private double 	threeMonths;
    private double 	oneYear;
    private long 	marketCap;

    // Constructor
    public StockListModel () {

        symbol          = null;
        symbolEncoded   = null;
        name            = null;
        sector          = null;
        industry        = null;
        date            = null;
        spIndex         = null;
        dowIndex        = null;
        russellIndex    = null;
        ytd             = 0.0;
        oneDay          = 0.0;
        oneWeek         = 0.0;
        twoWeeks        = 0.0;
        fourWeeks       = 0.0;
        threeMonths     = 0.0;
        oneYear         = 0.0;
        marketCap       = 0L;
    }

    public StockListModel (String aSymbol,   String aName,      String aSector, String aIndustry, Date aDate,
                           String aSpIndex,  String aDowIndex,  String aRussellIndex,
                           double aYtd,      double aOneDay,    double aOneWeek,
                           double aTwoWeeks, double aFourWeeks, double aThreeMonths,
                           double aOneYear,  long aMarketCap ) {

        symbol            = aSymbol;
        //name              = StringEscapeUtils.escapeHtml4(aName);
        //sector            = StringEscapeUtils.escapeHtml4(aSector);
        //industry          = StringEscapeUtils.escapeHtml4(aIndustry);
        name              = aName;
        sector            = aSector;
        industry          = aIndustry;
        date              = aDate;
        spIndex           = aSpIndex;
        dowIndex          = aDowIndex;
        russellIndex      = aRussellIndex;
        ytd               = aYtd;
        oneDay            = aOneDay;
        oneWeek           = aOneWeek;
        twoWeeks          = aTwoWeeks;
        fourWeeks         = aFourWeeks;
        threeMonths       = aThreeMonths;
        oneYear           = aOneYear;
        marketCap         = aMarketCap;
        symbolEncoded     = StringEscapeUtils.escapeHtml4(symbol);
    }

}
