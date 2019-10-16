package com.jpw.raptor.model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 4/2/17.
 */
public class QuoteTest {

    @Test
    public void test01() {
        System.out.println("test 01 started");

        String  symbol  = "abc";

        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        String              date_string         = new String("2018-01-10");
        Date                date_set            = new Date(-1);
        Date                date_get            = new Date(-1);
        try {
            date_set = simpleDateFormat.parse(date_string);
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote delete date format exception");
        }

        double  open    = 1.0;
        double  high    = 2.0;
        double  low     = 3.0;
        double  close   = 4.0;
        long    volume  = 5l;

        Quote q = new Quote();
        q.setSymbol(symbol);
        q.setDate(date_set);
        q.setOpen(open);
        q.setHigh(high);
        q.setLow(low);
        q.setClose(close);
        q.setVolume(volume);

        System.out.println("Symbol " + q.getSymbol());
        System.out.println("Date   " + q.getDate());
        System.out.println("Open   " + q.getOpen());
        System.out.println("High   " + q.getHigh());
        System.out.println("Low    " + q.getLow());
        System.out.println("Close  " + q.getClose());
        System.out.println("Volume " + q.getVolume());

    }
}
