package com.jpw.raptor.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Ported to new design 5/14/2019
 *
 * Helper routines for date manipulation
 *
 */
@Slf4j
public class DateConstants {

    //lombak creates log object from LoggerFactory.getLogger

    private static final String[]   qtrEnd      = {"", "-03-31", "-06-30", "-09-30", "-12-31"};
    private static final String     dateFormat  = "yyyy-MM-dd";

    public Date getQuarterEndDate(int year, int qtr) {

        Date date = null;

        try {
            if (year >= 2000 && year <= 2035) {
                if (qtr >= 1 && qtr <= 4) {
                    String dateString = String.valueOf(year) + qtrEnd[qtr];
                    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                    date = formatter.parse(dateString);
                }
            }
        } catch (ParseException ex) {
            log.error(ex.toString());
        }

        return date;
    }


    public Date getYearStart(int year) {

        Date date = null;

        try {
            if (year >= 2000 && year <= 2035) {
                String dateString = String.valueOf(year) + "-01-01";
                SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                date = formatter.parse(dateString);
            }
        } catch (ParseException ex) {
            log.error(ex.toString());
        }

        return date;
    }


    public Date getYearEnd(int year) {

        Date date = null;

        try {
            if (year >= 2000 && year <= 2035) {
                String dateString = String.valueOf(year) + "-12-31";
                SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                date = formatter.parse(dateString);
            }
        } catch (ParseException ex) {
            log.error(ex.toString());
        }

        return date;
    }
}
