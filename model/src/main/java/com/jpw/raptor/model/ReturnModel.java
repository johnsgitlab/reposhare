package com.jpw.raptor.model;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by john on 4/5/18.
 */
public class ReturnModel {

    DecimalFormat df = new DecimalFormat("#.##");

    private String  symbol;
    private Date    date;
    private String 	assetClass;
    private String 	fundType;
    private String 	fundSubType;
    private String 	factor;

    private double  currYtd;
    private double  currQtr4;
    private double  currQtr3;
    private double  currQtr2;
    private double  currQtr1;

    private double  priorYtd;
    private double  priorQtr4;
    private double  priorQtr3;
    private double  priorQtr2;
    private double  priorQtr1;

    public ReturnModel() {

        symbol      = null;
        date        = null;
        assetClass  = null;
        fundType    = null;
        fundSubType = null;
        factor      = null;

        currYtd     = 0.0;
        currQtr4    = 0.0;
        currQtr3    = 0.0;
        currQtr2    = 0.0;
        currQtr1    = 0.0;

        priorYtd    = 0.0;
        priorQtr4   = 0.0;
        priorQtr3   = 0.0;
        priorQtr2   = 0.0;
        priorQtr1   = 0.0;
    }

    public String   getSymbol()                   { return symbol; }
    public void     setSymbol(String v)           { symbol = v; }

    public void 	setDate(Date v)               { date = v; }
    public Date 	getDate()                     { return date; }

    public String 	getAssetClass()			      {return assetClass;}
    public void 	setAssetClass(String v)       {assetClass = v;}

    public String 	getFundType()			      {return fundType;}
    public void 	setFundType(String v)         {fundType = v;}

    public String 	getFundSubType()			  {return fundSubType;}
    public void 	setFundSubType(String v)      {fundSubType = v;}

    public String 	getFactor()			          {return factor;}
    public void 	setFactor(String v)           {factor = v;}

    public double   getCurrYtd()                  { return currYtd; }
    public void     setCurrYtd(double v)          { currYtd = Double.valueOf(df.format(v)); }

    public double   getCurrQtr1()                 { return currQtr1; }
    public void     setCurrQtr1(double v)         { currQtr1 = Double.valueOf(df.format(v)); }

    public double   getCurrQtr2()                 { return currQtr2; }
    public void     setCurrQtr2(double v)         { currQtr2 = Double.valueOf(df.format(v)); }

    public double   getCurrQtr3()                 { return currQtr3; }
    public void     setCurrQtr3(double v)         { currQtr3 = Double.valueOf(df.format(v)); }

    public double   getCurrQtr4()                 { return currQtr4; }
    public void     setCurrQtr4(double v)         { currQtr4 = Double.valueOf(df.format(v)); }

    public double   getPriorYtd()                 { return priorYtd; }
    public void     setPriorYtd(double v)         { priorYtd = Double.valueOf(df.format(v)); }

    public double   getPriorQtr1()                { return priorQtr1; }
    public void     setPriorQtr1(double v)        { priorQtr1 = Double.valueOf(df.format(v)); }

    public double   getPriorQtr2()                { return priorQtr2; }
    public void     setPriorQtr2(double v)        { priorQtr2 = Double.valueOf(df.format(v)); }

    public double   getPriorQtr3()                { return priorQtr3; }
    public void     setPriorQtr3(double v)        { priorQtr3 = Double.valueOf(df.format(v)); }

    public double   getPriorQtr4()                { return priorQtr4; }
    public void     setPriorQtr4(double v)        { priorQtr4 = Double.valueOf(df.format(v)); }

}
