package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Average;
import com.jpw.raptor.model.Quote;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GeometricMeanTest {

    @Test
    public void test() throws ParseException {
        System.out.println("Geometric mean test started");

        SimpleDateFormat    sdf    = new SimpleDateFormat("yyyyMMdd");
        DecimalFormat       df     = new DecimalFormat("#.##");
        ArrayList<Quote>    data   = new ArrayList(253);

        data.add(new Quote("SPY", sdf.parse("20180323"), 0.0, 0.0, 0.0, 258.05, 0));
        data.add(new Quote("SPY", sdf.parse("20180322"), 0.0, 0.0, 0.0, 263.67, 0));
        data.add(new Quote("SPY", sdf.parse("20180321"), 0.0, 0.0, 0.0, 270.43, 0));
        data.add(new Quote("SPY", sdf.parse("20180320"), 0.0, 0.0, 0.0, 270.95, 0));
        data.add(new Quote("SPY", sdf.parse("20180319"), 0.0, 0.0, 0.0, 270.49, 0));
        GeometricMean doit = new GeometricMean();

        double result;

        // 0 start 3 days
        double test1 = doit.gmQuote(data, 0, 3);
        result = Math.log(data.get(0).getClose()) + Math.log(data.get(1).getClose()) + Math.log(data.get(2).getClose());
        result = Math.exp(result / 3.0);
        Assert.assertEquals(Double.valueOf(df.format(result)), test1, 0.001);

        // 2 start 3 days
        double test2 = doit.gmQuote(data, 2, 3);
        result = Math.log(data.get(2).getClose()) + Math.log(data.get(3).getClose()) + Math.log(data.get(4).getClose());
        result = Math.exp(result / 3.0);
        Assert.assertEquals(Double.valueOf(df.format(result)), test2, 0.001);

        // 3 start 3 days
        double test3 = doit.gmQuote(data, 3, 3);
        Assert.assertEquals(0.0, test3, 0.001);

        System.out.println("Geometric mean  test finished");
    }

}
