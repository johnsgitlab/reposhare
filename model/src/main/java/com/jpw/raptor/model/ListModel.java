package com.jpw.raptor.model;

import org.apache.commons.text.StringEscapeUtils;

import java.util.Date;

/**
 * Created by john on 4/8/18.
 */
public class ListModel {

    private String 	symbol;
    private String 	symbolEncoded;
    private Date    date;
    private String 	assetClass;
    private String 	fundType;
    private String 	fundSubType;
    private String 	factor;
    private double 	ytd;
    private double 	oneDay;
    private double 	oneWeek;
    private double 	twoWeeks;
    private double 	fourWeeks;
    private double 	threeMonths;
    private double 	oneYear;
    private double 	marketCap;

    // Constructor
    public ListModel () {

        symbol             = null;
        symbolEncoded      = null;
        date               = null;
        assetClass         = null;
        fundType           = null;
        fundSubType        = null;
        factor             = null;
        ytd                = 0.0;
        oneDay             = 0.0;
        oneWeek            = 0.0;
        twoWeeks           = 0.0;
        fourWeeks          = 0.0;
        threeMonths        = 0.0;
        oneYear            = 0.0;
        marketCap          = 0.0;
    }

    public ListModel (String aSymbol, Date aDate,
                         String aAssetClass, String aFundType, String aFundSubType, String aFactor,
                         double aYtd, double aOneDay,    double aOneWeek,
                         double aTwoWeeks,  double aFourWeeks, double aThreeMonths,
                         double aOneYear,   double aMarketCap ) {
        symbol            = aSymbol;
        date              = aDate;
        assetClass        = aAssetClass;
        fundType          = aFundType;
        fundSubType       = aFundSubType;
        factor            = aFactor;
        ytd               = aYtd;
        oneDay            = aOneDay;
        oneWeek           = aOneWeek;
        twoWeeks          = aTwoWeeks;
        fourWeeks         = aFourWeeks;
        threeMonths       = aThreeMonths;
        oneYear           = aOneYear;
        marketCap         = aMarketCap;
        symbolEncoded = StringEscapeUtils.escapeHtml4(symbol);
    }

    public String 	getSymbol()			        {return symbol;}
    public void 	setSymbol(String v)         {symbol = v;}

    public String 	getSymbolEncoded()			{return symbolEncoded;}
    public void 	setSymbolEncoded(String v)	{
        symbolEncoded = StringEscapeUtils.escapeHtml4(v);
    }

    public void 	setDate(Date v)             { date = v; }
    public Date 	getDate()                   { return date; }

    public String 	getAssetClass()			    {return assetClass;}
    public void 	setAssetClass(String v)     {assetClass = v;}

    public String 	getFundType()			    {return fundType;}
    public void 	setFundType(String v)       {fundType = v;}

    public String 	getFundSubType()			{return fundSubType;}
    public void 	setFundSubType(String v)    {fundSubType = v;}

    public String 	getFactor()			        {return factor;}
    public void 	setFactor(String v)         {factor = v;}

    public void 	setYtd(Double v)            { ytd = v; }
    public double 	getYtd()                    { return ytd; }

    public void 	setOneDay(Double v)         { oneDay = v; }
    public double 	getOneDay()                 { return oneDay; }

    public void 	setOneWeek(Double v)        { oneWeek = v; }
    public double 	getOneWeek()                { return oneWeek; }

    public void 	setTwoWeeks(Double v)       { twoWeeks = v; }
    public double 	getTwoWeeks()               { return twoWeeks; }

    public void 	setFourWeeks(Double v)      { fourWeeks = v; }
    public double 	getFourWeeks()              { return fourWeeks; }

    public void 	setThreeMonths(Double v)    { threeMonths = v; }
    public double 	getThreeMonths()            { return threeMonths; }

    public void 	setOneYear(Double v)        { oneYear = v; }
    public double 	getOneYear()                { return oneYear; }

    public void 	setMarketCap(Double v)      { marketCap = v; }
    public double 	getMarketCap()              { return marketCap; }

}
