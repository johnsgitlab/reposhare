package com.jpw.raptor.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 4/7/18.
 */
public class SignalModelTest {

    private String  symbol;
    private Date    date;
    private String 	assetClass;
    private String 	fundType;
    private String 	fundSubType;
    private String 	factor;

    private String  category;
    private String  strategy;
    private String  signal;






    protected void init() {

        // Provide default values
        symbol      = "S&P 500";
        date        = new Date();

        assetClass  = "assetClass";
        fundType    = "fundType";
        fundSubType = "fundSubType";
        factor      = "factor";

        category    = "category";
        strategy    = "strategy";
        signal      = "signal";

    }

    // Constructor
    public SignalModelTest () {
        init();
    }

    public void setSymbolOnly(SignalModel rec) {
        rec.setSymbol(symbol);
    }

    public void setValues(SignalModel rec) {

        rec.setSymbol(symbol);
        rec.setDate(date);
        rec.setAssetClass(assetClass);
        rec.setFundType(fundType);
        rec.setFundSubType(fundSubType);
        rec.setFactor(factor);

        rec.setCategory(category);
        rec.setStrategy(strategy);
        rec.setSignal(signal);
    }


    public void validateValues(SignalModel rec) {

        assertTrue(rec.getSymbol().equalsIgnoreCase(symbol));
        assertEquals(0, rec.getDate().compareTo(date));
        assertTrue(rec.getAssetClass().equalsIgnoreCase(assetClass));
        assertTrue(rec.getFundType().equalsIgnoreCase(fundType));
        assertTrue(rec.getFundSubType().equalsIgnoreCase(fundSubType));
        assertTrue(rec.getFactor().equalsIgnoreCase(factor));

        assertTrue(rec.getCategory().equalsIgnoreCase(category));
        assertTrue(rec.getStrategy().equalsIgnoreCase(strategy));
        assertTrue(rec.getSignal().equalsIgnoreCase(signal));
    }


    @Test
    public void test01() {
        SignalModel r = new SignalModel();
        setValues(r);
        validateValues(r);
    }
}
