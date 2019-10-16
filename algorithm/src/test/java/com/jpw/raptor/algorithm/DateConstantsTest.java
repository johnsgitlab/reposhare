package com.jpw.raptor.algorithm;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by John on 8/1/2017.
 */
public class DateConstantsTest {



    @Test
    public void test() {
        System.out.println("started");

        DateConstants    dc         = new DateConstants();
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");

        assertTrue( formatter.format(dc.getYearStart(2017)).equalsIgnoreCase("2017-01-01"));
        assertTrue( formatter.format(dc.getYearEnd(2017)).equalsIgnoreCase("2017-12-31"));

        assertTrue( formatter.format(dc.getQuarterEndDate(2017,1)).equalsIgnoreCase("2017-03-31"));
        assertTrue( formatter.format(dc.getQuarterEndDate(2017,2)).equalsIgnoreCase("2017-06-30"));
        assertTrue( formatter.format(dc.getQuarterEndDate(2017,3)).equalsIgnoreCase("2017-09-30"));
        assertTrue( formatter.format(dc.getQuarterEndDate(2017,4)).equalsIgnoreCase("2017-12-31"));

        System.out.println("finished");
    }
}
