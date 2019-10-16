package com.jpw.raptor.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by john on 2/20/18.
 */
public class IndexTest {

    private String     symbol;
    private String     name;
    private Date       date;
    private Date       lastUpdate;
    
    private double     ytd;
    private double     oneDay;
    private double     oneWeek;
    private double     twoWeeks;
    private double     fourWeeks;
    private double     threeMonths;
    private double     oneYear;
    private double     threeYears;

    private String     overview;

    protected void init() {

        // Provide default values
        symbol = "testit";
        name = "name";
        
        date = new Date(-1);
        lastUpdate = new Date();
        
        ytd = 7.0;
        oneDay = 8.0;
        oneWeek = 9.0;
        twoWeeks = 10.0;
        fourWeeks = 11.0;
        threeMonths = 12.0;
        oneYear = 13.0;
        threeYears = 14.0;
        
        overview = "overview";
    }

    // Constructor
    public IndexTest () {
        init();
    }

    public void setSymbolOnly(Index rec) {
        rec.setSymbol(symbol);
    }

    public void setValues(Index rec) {

        rec.setSymbol(symbol);
        rec.setName(name);
        
        rec.setDate(new Date(-1));
        rec.setLastUpdate(new Date());
        
        rec.setYtd(ytd);
        rec.setOneDay(oneDay);
        rec.setOneWeek(oneWeek);
        rec.setTwoWeeks(twoWeeks);
        rec.setFourWeeks(fourWeeks);
        rec.setThreeMonths(threeMonths);
        rec.setOneYear(oneYear);
        rec.setThreeYears(threeYears);

        rec.setOverview(overview);
    }


    public void validateValues(Index rec) {

        assertTrue(rec.getSymbol().equalsIgnoreCase(symbol));
        assertTrue(rec.getName().equalsIgnoreCase(name));

//        assertTrue(rec.getDate().equals(date));
//        assertTrue(rec.getLastUpdate().equals(lastUpdate));

        assertEquals(rec.getYtd(),ytd,0.0001);
        assertEquals(rec.getOneDay(),oneDay,0.0001);
        assertEquals(rec.getOneWeek(),oneWeek,0.0001);
        assertEquals(rec.getTwoWeeks(),twoWeeks,0.0001);
        assertEquals(rec.getFourWeeks(),fourWeeks,0.0001);
        assertEquals(rec.getThreeMonths(),threeMonths,0.0001);
        assertEquals(rec.getOneYear(),oneYear,0.0001);
        assertEquals(rec.getThreeYears(),threeYears,0.0001);

        assertTrue(rec.getOverview().equalsIgnoreCase(overview));
    }


    @Test
    public void test01() {
        Index r = new Index();
        setValues(r);
        validateValues(r);
    }

}
