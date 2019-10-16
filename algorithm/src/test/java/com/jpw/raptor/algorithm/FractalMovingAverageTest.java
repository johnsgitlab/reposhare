package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created by john on 4/1/17.
 */
public class FractalMovingAverageTest {

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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        e00 = new Quote("SPY",sdf.parse("20160211"),182.34,184.1,181.09,109.4,218437700);
        e01 = new Quote("SPY",sdf.parse("20160210"),186.41,188.34,185.12,109.24,148214300);
        e02 = new Quote("SPY",sdf.parse("20160209"),183.36,186.94,183.2,107.76,184512600);
        e03 = new Quote("SPY",sdf.parse("20160208"),185.77,186.12,182.8,107.94,191526500);
        e04 = new Quote("SPY",sdf.parse("20160205"),190.99,191.67,187.2,109.05,180788300);
        e05 = new Quote("SPY",sdf.parse("20160204"),190.71,192.75,189.96,109.31,139531700);

        e06 = new Quote("FUSVX",sdf.parse("20160203"),67.29,67.29,67.29,67.29,0);
        e07 = new Quote("FUSVX",sdf.parse("20160202"),66.94,66.94,66.94,66.94,0);
        e08 = new Quote("FUSVX",sdf.parse("20160201"),68.21,68.21,68.21,68.21,0);
        e09 = new Quote("FUSVX",sdf.parse("20160129"),68.24,68.24,68.24,68.24,0);
        e10 = new Quote("FUSVX",sdf.parse("20160128"),66.59,66.59,66.59,66.59,0);
        e11 = new Quote("FUSVX",sdf.parse("20160127"),66.22,66.22,66.22,66.22,0);

        c00 = 109.4;
        c01 = 109.24;
        c02 = 107.76;
        c03 = 107.94;
        c04 = 109.05;
        c05 = 109.31;
        c06 = 67.29;
        c07 = 66.94;
        c08 = 68.21;
        c09 = 68.24;
        c10 = 66.59;
        c11 = 66.22;
    }

    double offset0(double prior) {

        // etf, offset 0; periods 6

        // h1 covers 3,4,5
        double h1 = ( 192.75 - 182.8 ) / 3.0;

        // h2 covers 0,1,2;
        double h2 = ( 188.34 - 181.09 ) / 3.0;

        // hl covers 0,1,2,3,4,5
        double hl = ( 192.75 - 181.09 ) / 6.0;

        // fractal dimension d
        double d = ( Math.log(h1+h2) - Math.log(hl) ) / Math.log(2.0);

        double w = -4.6;

        double alpha = Math.exp(w * (d - 1.0) );

        double result =  ( prior + ( alpha * ( 109.4 - prior)));
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(result));
    }

    double offset6(double prior) {

        // mutual fund, offset 6; periods 6

        // h1 covers 9.10.11
        double h1 = ( 68.24 - 66.22 ) / 3.0;

        // h2 covers 6,7,8
        double h2 = ( 68.21 - 66.94 ) / 3.0;

        // hl covers 6,7,8,9,10,11
        double hl = ( 68.24 - 66.22 ) / 6.0;

        // fractal dimension d
        double d = ( Math.log(h1+h2) - Math.log(hl) ) / Math.log(2.0);

        double w = -4.6;

        double alpha = Math.exp(w * (d - 1.0) );

        double result = ( prior + ( alpha * ( 67.29 - prior)));

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(result));
    }


    @Test
    public void test() {

        Quote[] data = {e00, e01, e02, e03, e04, e05, e06, e07, e08, e09, e10, e11};

        FractalMovingAverage fma = new FractalMovingAverage();

        Assert.assertEquals(offset0( 182.86), fma.fractalMovingAverage(Arrays.asList(data), 0, 6, 182.86), 0.001);

        Assert.assertEquals(offset6( 66.59), fma.fractalMovingAverage( Arrays.asList(data), 6, 6, 66.59), 0.001 );
    }
}
