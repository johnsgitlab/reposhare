package com.jpw.raptor.model;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 4/7/18.
 */
public class HoldingModelTest {

    private String symbol;
    private String holdingName;
    private double holdingPercent;

    protected void init() {

        // Provide default values
        symbol = "S&P 500";

        holdingName = "assetClass";

        holdingPercent = 13.0;
    }

    // Constructor
    public HoldingModelTest () {
        init();
    }

    public void setSymbolOnly(HoldingModel rec) {
        rec.setSymbol(symbol);
    }

    public void setValues(HoldingModel rec) {

        rec.setSymbol(symbol);
        rec.setHoldingName(holdingName);
        rec.setHoldingPercent(holdingPercent);
    }


    public void validateValues(HoldingModel rec) {

        assertTrue(rec.getSymbol().equalsIgnoreCase(symbol));

        assertTrue(rec.getHoldingName().equalsIgnoreCase(holdingName));

        assertEquals(rec.getHoldingPercent(),holdingPercent,0.0001);

    }


    @Test
    public void test01() {
        HoldingModel r = new HoldingModel();
        setValues(r);
        validateValues(r);
    }
}
