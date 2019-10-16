package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleMovingAverageTest {

    Quote e00;
    Quote e01;
    Quote e02;
    Quote e03;
    Quote e04;
    Quote e05;
    Quote e06;

    double  d00;
    double  d01;
    double  d02;
    double  d03;
    double  d04;
    double  d05;
    double  d06;


    @Before
    public void setup() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        e00 = new Quote("SPY",sdf.parse("20160211"),182.34,184.1,181.09,182.86,218437700);
        e01 = new Quote("SPY",sdf.parse("20160210"),186.41,188.34,185.12,185.27,148214300);
        e02 = new Quote("SPY",sdf.parse("20160209"),183.36,186.94,183.2,185.43,184512600);
        e03 = new Quote("SPY",sdf.parse("20160208"),185.77,186.12,182.8,185.42,191526500);
        e04 = new Quote("SPY",sdf.parse("20160205"),190.99,191.67,187.2,187.95,180788300);
        e05 = new Quote("SPY",sdf.parse("20160204"),190.71,192.75,189.96,191.6,139531700);
        e06 = new Quote("SPY",sdf.parse("20160203"),191.41,191.78,187.1,191.3,205054900);

        d00 = 182.86;
        d01 = 185.27;
        d02 = 185.43;
        d03 = 185.42;
        d04 = 187.95;
        d05 = 191.6;
        d06 = 191.3;
    }


    @Test
    public void test1() {
        System.out.println("moving average test1 started");

        Quote[] dataArrayDesc = {e00, e01, e02, e03, e04, e05, e06};

        SimpleMovingAverage doit = new SimpleMovingAverage();
        DecimalFormat df = new DecimalFormat("#.##");
        Quote[]             dataArray;
        List<Quote> data;

        // Descending data
        dataArray   = dataArrayDesc;
        data        = Arrays.asList(dataArray);

        assertEquals(Double.valueOf(df.format((d04 + d05 + d06) / 3.0)), doit.smaQuote( data, 4, 3), 0.001);
        assertEquals(Double.valueOf(df.format((d04 + d05 + d06) / 3.0)), doit.simpleMovingAverageDesc( data, 4, 8), 0.001);
        assertEquals(Double.valueOf(df.format((d01 + d02) / 2.0)),       doit.smaQuote( data, 1, 2), 0.001);


        System.out.println("moving average test1 finished");
    }


    @Test
    public void test2() {
        System.out.println("moving average test2 started");

        Quote[] dataArrayAsc = {e06, e05, e04, e03, e02, e01, e00};

        SimpleMovingAverage doit = new SimpleMovingAverage();
        DecimalFormat       df = new DecimalFormat("#.##");
        Quote[]             dataArray;
        List<Quote>         data;

        // Descending data
        dataArray   = dataArrayAsc;
        data        = Arrays.asList(dataArray);

        assertEquals(Double.valueOf(df.format((d00) / 1.0)), doit.simpleMovingAverageAsc( data, 6, 1), 0.001);
        assertEquals(Double.valueOf(df.format((d00 + d01) / 2.0)), doit.simpleMovingAverageAsc( data, 6, 2), 0.001);
        assertEquals(Double.valueOf(df.format((d03 +d04 + d05 + d06) / 4.0)), doit.simpleMovingAverageAsc( data, 3, 10), 0.001);

        System.out.println("moving average test2 finished");
    }
}
