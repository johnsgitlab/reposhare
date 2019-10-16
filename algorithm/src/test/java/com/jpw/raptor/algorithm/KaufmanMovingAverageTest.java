package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by john on 3/4/18.
 */
public class KaufmanMovingAverageTest {

    Quote e00;
    Quote e01;
    Quote e02;
    Quote e03;
    Quote e04;
    Quote e05;
    Quote e06;
    Quote e07;
    Quote e08;
    Quote e09;
    Quote e10;
    Quote e11;

    double c00;
    double c01;
    double c02;
    double c03;
    double c04;
    double c05;
    double c06;
    double c07;
    double c08;
    double c09;
    double c10;
    double c11;


    @Before
    public void setup() throws ParseException {

        SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMdd");

        e00 = new Quote("SPY",sdf.parse("20160211"),182.34,184.1,181.09,109.4,218437700);
        e01 = new Quote("SPY",sdf.parse("20160210"),186.41,188.34,185.12,109.24,148214300);
        e02 = new Quote("SPY",sdf.parse("20160209"),183.36,186.94,183.2,107.76,184512600);
        e03 = new Quote("SPY",sdf.parse("20160208"),185.77,186.12,182.8,107.94,191526500);
        e04 = new Quote("SPY",sdf.parse("20160205"),190.99,191.67,187.2,109.05,180788300);
        e05 = new Quote("SPY",sdf.parse("20160204"),190.71,192.75,189.96,109.31,139531700);
        e06 = new Quote("SPY",sdf.parse("20160203"),191.41,191.78,187.1,110.15,205054900);
        e07 = new Quote("SPY",sdf.parse("20160203"),191.41,191.78,187.1,109.82,205054900);
        e08 = new Quote("SPY",sdf.parse("20160203"),191.41,191.78,187.1,110.17,205054900);
        e09 = new Quote("SPY",sdf.parse("20160203"),191.41,191.78,187.1,109.8,205054900);
        e10 = new Quote("SPY",sdf.parse("20160203"),191.41,191.78,187.1,110.46,205054900);
        e11 = new Quote("SPY",sdf.parse("20160203"),191.41,191.78,187.1,111.06,205054900);

        c00 = 109.4;
        c01 = 109.24;
        c02 = 107.76;
        c03 = 107.94;
        c04 = 109.05;
        c05 = 109.31;
        c06 = 110.15;
        c07 = 109.82;
        c08 = 110.17;
        c09 = 109.8;
        c10 = 110.46;
        c11 = 111.06;
    }


    public double firstValue(double prior) {

        // compute kaufman for e00

        double change       = Math.abs(c00-c09);
        double volatility   =
                Math.abs(c00-c01) +
                        Math.abs(c01-c02) +
                        Math.abs(c02-c03) +
                        Math.abs(c03-c04) +
                        Math.abs(c04-c05) +
                        Math.abs(c05-c06) +
                        Math.abs(c06-c07) +
                        Math.abs(c07-c08) +
                        Math.abs(c08-c09);

        double er           = change / volatility;
        double fc           = 0.66667;
        double sc           = 0.06452;

        double smoothing    = Math.pow( ((er * (fc-sc)) + sc), 2);

        return ( prior + (smoothing * (c00-prior)) );
    }


    public double secondValue(double prior) {

        // compute kaufman for e00

        double change       = Math.abs(c01-c10);
        double volatility   =
                Math.abs(c01-c02) +
                        Math.abs(c02-c03) +
                        Math.abs(c03-c04) +
                        Math.abs(c04-c05) +
                        Math.abs(c05-c06) +
                        Math.abs(c06-c07) +
                        Math.abs(c07-c08) +
                        Math.abs(c08-c09) +
                        Math.abs(c09-c10);

        double er           = change / volatility;
        double fc           = 0.66667;
        double sc           = 0.06452;

        double smoothing    = Math.pow( ((er * (fc-sc)) + sc), 2);

        return ( prior + (smoothing * (c01-prior)) );
    }


    @Test
    public void test() {
        System.out.println("kaufman moving average test started");

        Quote[] dataArrayDesc = {e00, e01, e02, e03, e04, e05, e06, e07, e08, e09, e10, e11};
        Quote[] dataArrayAsc  = {e11, e10, e09, e08, e07, e06, e05, e04, e03, e02, e01, e00};

        List<Quote> dataDesc = Arrays.asList(dataArrayDesc);
        List<Quote> dataAsc  = Arrays.asList(dataArrayAsc);

        KaufmanMovingAverage fma = new KaufmanMovingAverage();

        System.out.println("kaufman moving average = " +
                fma.kaufmanMovingAverageDesc(dataDesc, 0, 10, 2, 30, 110.4) + " " +
                fma.kaufmanMovingAverageAsc (dataAsc, 11, 10, 2, 30, 110.4) + " " +
                firstValue(110.4) );
        assertEquals(
                fma.kaufmanMovingAverageDesc(dataDesc, 0, 10, 2, 30, 110.4),
                fma.kaufmanMovingAverageAsc (dataAsc, 11, 10, 2, 30, 110.4),
                0.001);

        System.out.println(" ");

        System.out.println("kaufman moving average = " +
                fma.kaufmanMovingAverageDesc(dataDesc, 1, 10, 2, 30, 111.6) + " " +
                fma.kaufmanMovingAverageAsc (dataAsc, 10, 10, 2, 30, 111.6) + " " +
                secondValue(111.6)  );
        // Rounding error on this test
        assertEquals(
                fma.kaufmanMovingAverageDesc(dataDesc, 1, 10, 2, 30, 111.6),
                fma.kaufmanMovingAverageAsc (dataAsc, 10, 10, 2, 30, 111.6),
                0.001);


        //System.out.println("w = " + Math.log(2.0 / (199.0)));
        //DecimalFormat df = new DecimalFormat("#.##");
        //System.out.println( Double.valueOf(df.format((d02 + d03 + d04) / 3.0)) );

        System.out.println("kaufman moving average test finished");
    }
}
