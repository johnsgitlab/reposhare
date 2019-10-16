package com.jpw.raptor.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 4/7/18.
 */
public class DateConstants {

    static final String qtrEnd[] = {"", "-03-31", "-06-30", "-09-30", "-12-31"};

    public Date getQuarterEndDate(int year, int qtr) {

        Date date = null;

        try {
            if (year >= 2000 && year <= 2035) {
                if (qtr >= 1 && qtr <= 4) {
                    String dateString = String.valueOf(year) + qtrEnd[qtr];
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    date = formatter.parse(dateString);
                }
            }
        } catch (ParseException ex) {
            //logger.error(ex.toString());
        }

        return date;
    }


    public Date getYearStart(int year) {

        Date date = null;

        try {
            if (year >= 2000 && year <= 2035) {
                String dateString = String.valueOf(year) + "-01-01";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                date = formatter.parse(dateString);
            }
        } catch (ParseException ex) {
            //logger.error(ex.toString());
        }

        return date;
    }


    public Date getYearEnd(int year) {

        Date date = null;

        try {
            if (year >= 2000 && year <= 2035) {
                String dateString = String.valueOf(year) + "-12-31";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                date = formatter.parse(dateString);
            }
        } catch (ParseException ex) {
            //logger.error(ex.toString());
        }

        return date;
    }
}
