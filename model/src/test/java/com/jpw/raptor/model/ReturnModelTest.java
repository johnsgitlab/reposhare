package com.jpw.raptor.model;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 4/7/18.
 */
public class ReturnModelTest {

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

    protected void init() {

        // Provide default values
        symbol      = "S&P 500";
        date        = new Date();

        assetClass  = "assetClass";
        fundType    = "fundType";
        fundSubType = "fundSubType";
        factor      = "factor";

        currYtd     = 7.0;
        currQtr4    = 8.0;
        currQtr3    = 9.0;
        currQtr2    = 10.0;
        currQtr1    = 11.0;

        priorYtd    = 12.0;
        priorQtr4   = 13.0;
        priorQtr3   = 14.0;
        priorQtr2   = 15.0;
        priorQtr1   = 16.0;

    }

    // Constructor
    public ReturnModelTest () {
        init();
    }

    public void setSymbolOnly(ReturnModel rec) {
        rec.setSymbol(symbol);
    }

    public void setValues(ReturnModel rec) {

        rec.setSymbol(symbol);
        rec.setDate(date);
        rec.setAssetClass(assetClass);
        rec.setFundType(fundType);
        rec.setFundSubType(fundSubType);
        rec.setFactor(factor);

        rec.setCurrYtd(currYtd);
        rec.setCurrQtr4(currQtr4);
        rec.setCurrQtr3(currQtr3);
        rec.setCurrQtr2(currQtr2);
        rec.setCurrQtr1(currQtr1);

        rec.setPriorYtd(priorYtd);
        rec.setPriorQtr4(priorQtr4);
        rec.setPriorQtr3(priorQtr3);
        rec.setPriorQtr2(priorQtr2);
        rec.setPriorQtr1(priorQtr1);

    }


    public void validateValues(ReturnModel rec) {

        assertTrue(rec.getSymbol().equalsIgnoreCase(symbol));
        assertEquals(0, rec.getDate().compareTo(date));
        assertTrue(rec.getAssetClass().equalsIgnoreCase(assetClass));
        assertTrue(rec.getFundType().equalsIgnoreCase(fundType));
        assertTrue(rec.getFundSubType().equalsIgnoreCase(fundSubType));
        assertTrue(rec.getFactor().equalsIgnoreCase(factor));

        assertEquals(rec.getCurrYtd(),currYtd,0.0001);
        assertEquals(rec.getCurrQtr4(),currQtr4,0.0001);
        assertEquals(rec.getCurrQtr3(),currQtr3,0.0001);
        assertEquals(rec.getCurrQtr2(),currQtr2,0.0001);
        assertEquals(rec.getCurrQtr1(),currQtr1,0.0001);

        assertEquals(rec.getPriorYtd(),priorYtd,0.0001);
        assertEquals(rec.getPriorQtr4(),priorQtr4,0.0001);
        assertEquals(rec.getPriorQtr3(),priorQtr3,0.0001);
        assertEquals(rec.getPriorQtr2(),priorQtr2,0.0001);
        assertEquals(rec.getPriorQtr1(),priorQtr1,0.0001);

    }


    @Test
    public void test01() {
        ReturnModel r = new ReturnModel();
        setValues(r);
        validateValues(r);
    }
}
