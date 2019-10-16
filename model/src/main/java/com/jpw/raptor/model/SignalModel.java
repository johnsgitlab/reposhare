package com.jpw.raptor.model;

import java.util.Date;

/**
 * Created by john on 4/5/18.
 */
public class SignalModel {

    private String  symbol;
    private String  category;
    private String  strategy;
    private String  signal;
    private Date    date;
    private String 	assetClass;
    private String 	fundType;
    private String 	fundSubType;
    private String 	factor;

    public SignalModel() {

        symbol      = null;
        category    = null;
        strategy    = null;
        signal      = null;
        date        = null;
        assetClass  = null;
        fundType    = null;
        fundSubType = null;
        factor      = null;
    }

    public String   getSymbol()                 { return symbol; }
    public void     setSymbol(String v)         { symbol = v; }

    public String   getCategory()               { return category; }
    public void     setCategory(String v)       { category = v; }

    public String   getStrategy()               { return strategy; }
    public void     setStrategy(String v)       { strategy = v; }

    public String   getSignal()                 { return signal; }
    public void     setSignal(String v)         { signal = v; }

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

}
