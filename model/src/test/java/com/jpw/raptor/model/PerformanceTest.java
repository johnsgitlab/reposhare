package com.jpw.raptor.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 3/28/18.
 */
public class PerformanceTest {

    private Date date;

    private double     ytd;
    private double     oneDay;
    private double     oneWeek;
    private double     twoWeeks;
    private double     fourWeeks;
    private double     threeMonths;
    private double     oneYear;
    private double     threeYears;

    protected void init() {

        // Provide default values
        date = new Date();

        ytd = 7.0;
        oneDay = 8.0;
        oneWeek = 9.0;
        twoWeeks = 10.0;
        fourWeeks = 11.0;
        threeMonths = 12.0;
        oneYear = 13.0;
        threeYears = 14.0;
    }

    // Constructor
    public PerformanceTest () {
        init();
    }


    public void setValues(Performance rec) {

        rec.setDate(date);

        rec.setYtd(ytd);
        rec.setOneDay(oneDay);
        rec.setOneWeek(oneWeek);
        rec.setTwoWeeks(twoWeeks);
        rec.setFourWeeks(fourWeeks);
        rec.setThreeMonths(threeMonths);
        rec.setOneYear(oneYear);
        rec.setThreeYears(threeYears);
    }



    public void validateValues(Performance rec) {
        assertTrue(rec.getDate().equals(date));

        assertEquals(rec.getYtd(),ytd,0.0001);
        assertEquals(rec.getOneDay(),oneDay,0.0001);
        assertEquals(rec.getOneWeek(),oneWeek,0.0001);
        assertEquals(rec.getTwoWeeks(),twoWeeks,0.0001);
        assertEquals(rec.getFourWeeks(),fourWeeks,0.0001);
        assertEquals(rec.getThreeMonths(),threeMonths,0.0001);
        assertEquals(rec.getOneYear(),oneYear,0.0001);
        assertEquals(rec.getThreeYears(),threeYears,0.0001);
    }


    @Test
    public void test01() {
        Performance r = new Performance();
        setValues(r);
        validateValues(r);
    }
}
