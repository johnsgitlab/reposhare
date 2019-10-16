package com.jpw.raptor.model;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 4/7/18.
 */
public class SummaryModelTest {

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

    protected void init() {

        // Provide default values
        date                = new Date();

        assetClass          = "assetClass";
        fundType            = "fundType";
        fundSubType         = "fundSubType";
        factor              = "factor";

        assetClassEncoded   = "assetClassEncoded";
        fundTypeEncoded     = "fundTypeEncoded";
        fundSubTypeEncoded  = "fundSubTypeEncoded";
        factorEncoded       = "factorEncoded";

        ytd                 = 7.0;
        oneDay              = 8.0;
        oneWeek             = 9.0;
        twoWeeks            = 10.0;
        fourWeeks           = 11.0;
        threeMonths         = 12.0;
        oneYear             = 13.0;

        count               = 14;
    }

    // Constructor
    public SummaryModelTest () {
        init();
    }

    public void setValues(SummaryModel rec) {

        rec.setDate(date);

        rec.setAssetClass(assetClass);
        rec.setFundType(fundType);
        rec.setFundSubType(fundSubType);
        rec.setFactor(factor);

        rec.setAssetClassEncoded(assetClassEncoded);
        rec.setFundTypeEncoded(fundTypeEncoded);
        rec.setFundSubTypeEncoded(fundSubTypeEncoded);
        rec.setFactorEncoded(factorEncoded);

        rec.setYtd(ytd);
        rec.setOneDay(oneDay);
        rec.setOneWeek(oneWeek);
        rec.setTwoWeeks(twoWeeks);
        rec.setFourWeeks(fourWeeks);
        rec.setThreeMonths(threeMonths);
        rec.setOneYear(oneYear);

        rec.setCount(count);
    }


    public void validateValues(SummaryModel rec) {

        assertEquals(0, rec.getDate().compareTo(date));

        assertTrue(rec.getAssetClass().equalsIgnoreCase(assetClass));
        assertTrue(rec.getFundType().equalsIgnoreCase(fundType));
        assertTrue(rec.getFundSubType().equalsIgnoreCase(fundSubType));
        assertTrue(rec.getFactor().equalsIgnoreCase(factor));

        assertTrue(rec.getAssetClassEncoded().equalsIgnoreCase(assetClassEncoded));
        assertTrue(rec.getFundTypeEncoded().equalsIgnoreCase(fundTypeEncoded));
        assertTrue(rec.getFundSubTypeEncoded().equalsIgnoreCase(fundSubTypeEncoded));
        assertTrue(rec.getFactorEncoded().equalsIgnoreCase(factorEncoded));

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
        SummaryModel r = new SummaryModel();
        setValues(r);
        validateValues(r);
    }
}
