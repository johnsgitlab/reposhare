package com.jpw.raptor.model;

import org.apache.commons.text.StringEscapeUtils;
import java.util.Date;

/**
 * Created by john on 4/5/18.
 */
public class SummaryModel {

    private Date    date;
    private String 	assetClass;
    private String 	fundType;
    private String 	fundSubType;
    private String 	factor;
    private String 	assetClassEncoded;
    private String 	fundTypeEncoded;
    private String 	fundSubTypeEncoded;
    private String 	factorEncoded;
    private double 	ytd;
    private double 	oneDay;
    private double 	oneWeek;
    private double 	twoWeeks;
    private double 	fourWeeks;
    private double 	threeMonths;
    private double 	oneYear;
    private int 	count;

    // Constructor
    public SummaryModel () {
        date               = null;
        assetClass         = null;
        fundType           = null;
        fundSubType        = null;
        factor             = null;
        assetClassEncoded  = null;
        fundTypeEncoded    = null;
        fundSubTypeEncoded = null;
        factorEncoded      = null;
        ytd                = 0.0;
        oneDay             = 0.0;
        oneWeek            = 0.0;
        twoWeeks           = 0.0;
        fourWeeks          = 0.0;
        threeMonths        = 0.0;
        oneYear            = 0.0;
        count              = 0;
    }


    public void 	setDate(Date v)             { date = v; }
    public Date 	getDate()                   { return date; }

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

    public void 	setCount(int v)             { count = v; }
    public int 	    getCount()                  { return count; }

    public String 	getAssetClass()			    {return assetClass;}
    public void 	setAssetClass(String v)     {
        assetClass = v;
        setAssetClassEncoded(v);
    }

    public String 	getAssetClassEncoded()		{return assetClassEncoded;}
    public void 	setAssetClassEncoded(String v) {
        assetClassEncoded = StringEscapeUtils.escapeHtml4(v);
    }

    public String 	getFundType()			    {return fundType;}
    public void 	setFundType(String v)       {
        fundType = v;
        setFundTypeEncoded(v);
    }

    public String 	getFundTypeEncoded()		{return fundTypeEncoded;}
    public void 	setFundTypeEncoded(String v) {
        fundTypeEncoded = StringEscapeUtils.escapeHtml4(v);
    }

    public String 	getFundSubType()			    {return fundSubType;}
    public void 	setFundSubType(String v)        {
        fundSubType = v;
        setFundSubTypeEncoded(v);
    }

    public String 	getFundSubTypeEncoded()			{return fundSubTypeEncoded;}
    public void 	setFundSubTypeEncoded(String v) {fundSubTypeEncoded = StringEscapeUtils.escapeHtml4(v);
    }

    public String 	getFactor()			    {return factor;}
    public void 	setFactor(String v)     {
        factor = v;
        setFactorEncoded(v);
    }

    public String 	getFactorEncoded()		{return factorEncoded;}
    public void 	setFactorEncoded(String v) {
        factorEncoded = StringEscapeUtils.escapeHtml4(v);
    }
}
