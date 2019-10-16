package com.jpw.raptor.model;

import java.util.Date;

/**
 * Created by john on 3/27/18.
 */
public class EtfFundBase extends Performance {

    private String     assetClass;
    private String     fundType;
    private String     fundSubType;
    private String     factor;

    protected void init() {
        // Provide default values
        assetClass = null;
        fundType = null;
        fundSubType = null;
        factor = null;
    }

    // Constructor
    public EtfFundBase () {
        super();
        init();
    }

    public String   getAssetClass()                    {return assetClass;}
    public void     setAssetClass(String v)            {assetClass = v;}

    public String   getFundType()                      {return fundType;}
    public void     setFundType(String v)              {fundType = v;}

    public String   getFundSubType()                   {return fundSubType;}
    public void     setFundSubType(String v)           {fundSubType = v;}

    public String   getFactor()                        {return factor;}
    public void     setFactor(String v)                {factor = v;}

}
