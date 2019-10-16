package com.jpw.raptor.model;

import org.apache.commons.text.StringEscapeUtils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 4/8/18.
 */
public class ListModelTest {

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

    protected void init() {

        // Provide default values
        symbol = "S&P 500";
        symbolEncoded = "S&amp;P 500";
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

        marketCap = 3.0;
    }

    // Constructor
    public ListModelTest () {
        init();
    }

    public void setSymbolOnly(ListModel rec) {
        rec.setSymbol(symbol);
    }

    public void setValues(ListModel rec) {

        rec.setSymbol(symbol);
        rec.setSymbolEncoded(symbol);
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

        rec.setMarketCap(marketCap);
    }


    public void validateValues(ListModel rec) {

        assertTrue(rec.getSymbol().equalsIgnoreCase(symbol));
        assertTrue(rec.getSymbolEncoded().equalsIgnoreCase("S&amp;P 500"));
        assertTrue(StringEscapeUtils.unescapeHtml4(rec.getSymbolEncoded()).equalsIgnoreCase(symbol));

        assertEquals(0, rec.getDate().compareTo(date));

        assertTrue(rec.getAssetClass().equalsIgnoreCase(assetClass));
        assertTrue(rec.getFundType().equalsIgnoreCase(fundType));
        assertTrue(rec.getFundSubType().equalsIgnoreCase(fundSubType));
        assertTrue(rec.getFactor().equalsIgnoreCase(factor));

        assertEquals(rec.getYtd(),ytd,0.0001);
        assertEquals(rec.getOneDay(),oneDay,0.0001);
        assertEquals(rec.getOneWeek(),oneWeek,0.0001);
        assertEquals(rec.getTwoWeeks(),twoWeeks,0.0001);
        assertEquals(rec.getFourWeeks(),fourWeeks,0.0001);
        assertEquals(rec.getThreeMonths(),threeMonths,0.0001);
        assertEquals(rec.getOneYear(),oneYear,0.0001);

        assertEquals(rec.getMarketCap(),marketCap,0.0001);

    }


    @Test
    public void test01() {
        ListModel r = new ListModel();
        setValues(r);
        validateValues(r);
    }
}
