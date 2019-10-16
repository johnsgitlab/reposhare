package com.jpw.raptor.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 2/27/18.
 */
public class SummaryTest {

    private String     name;
    private String     equity;

    private Date       date;
    private String     assetClass;
    private String     fundType;
    private String     fundSubType;
    private String     factor;

    private double     ytd;
    private double     oneDay;
    private double     oneWeek;
    private double     twoWeeks;
    private double     fourWeeks;
    private double     threeMonths;
    private double     oneYear;

    private int        count;



    protected void init() {

        // Provide default values

        name = "name";
        equity = "equity";
        date = new Date();
        assetClass = "assetClass";
        fundType = "fundType";
        fundSubType = "fundSubType";
        factor = "factor";

        ytd = 7.0;
        oneDay = 8.0;
        oneWeek = 9.0;
        twoWeeks = 10.0;
        fourWeeks = 11.0;
        threeMonths = 12.0;
        oneYear = 13.0;

        count = 1;

    }

    // Constructor
    public SummaryTest () {
        init();
    }


    public void setValues(Summary rec) {

        rec.setName(name);
        rec.setEquity(equity);
        rec.setDate(date);

        rec.setAssetClass(assetClass);
        rec.setFundType(fundType);
        rec.setFundSubType(fundSubType);
        rec.setFactor(factor);

        rec.setYtd(ytd);
        rec.setOneDay(oneDay);
        rec.setOneWeek(oneWeek);
        rec.setTwoWeeks(twoWeeks);
        rec.setFourWeeks(fourWeeks);
        rec.setThreeMonths(threeMonths);
        rec.setOneYear(oneYear);

        rec.setCount(count);

    }


    public void validateValues(Summary rec) {

        assertTrue(rec.getName().equalsIgnoreCase(name));
        assertTrue(rec.getEquity().equalsIgnoreCase(equity));

        assertTrue(rec.getAssetClass().equalsIgnoreCase(assetClass));
        assertTrue(rec.getFundType().equalsIgnoreCase(fundType));
        assertTrue(rec.getFundSubType().equalsIgnoreCase(fundSubType));
        assertTrue(rec.getFactor().equalsIgnoreCase(factor));

        assertTrue(rec.getDate().equals(date));

        assertEquals(rec.getYtd(),ytd,0.0001);
        assertEquals(rec.getOneDay(),oneDay,0.0001);
        assertEquals(rec.getOneWeek(),oneWeek,0.0001);
        assertEquals(rec.getTwoWeeks(),twoWeeks,0.0001);
        assertEquals(rec.getFourWeeks(),fourWeeks,0.0001);
        assertEquals(rec.getThreeMonths(),threeMonths,0.0001);
        assertEquals(rec.getOneYear(),oneYear,0.0001);

        assertEquals(rec.getCount(),count);

    }


    @Test
    public void test01() {
        Summary r = new Summary();
        setValues(r);
        validateValues(r);
    }
}
