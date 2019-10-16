package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Average;
import com.jpw.raptor.model.Quote;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by john on 3/3/18.
 */
public class ExponentialMovingAverageTest {

    @Test
    public void test() throws ParseException {
        System.out.println("Exponential moving average test started");

        SimpleDateFormat sdf    = new SimpleDateFormat("yyyyMMdd");
        DecimalFormat    df     = new DecimalFormat("#.##");
        ArrayList<Quote> data   = new ArrayList(253);

        data.add(new Quote("SPY", sdf.parse("20180323"), 0.0, 0.0, 0.0, 258.05, 0));
        data.add(new Quote("SPY", sdf.parse("20180322"), 0.0, 0.0, 0.0, 263.67, 0));
        data.add(new Quote("SPY", sdf.parse("20180321"), 0.0, 0.0, 0.0, 270.43, 0));
        data.add(new Quote("SPY", sdf.parse("20180320"), 0.0, 0.0, 0.0, 270.95, 0));
        data.add(new Quote("SPY", sdf.parse("20180319"), 0.0, 0.0, 0.0, 270.49, 0));
        ExponentialMovingAverage doit = new ExponentialMovingAverage();

        double days         = 4.0;
        double multiplier   = 2.0 / (days + 1.0);

        List<Average> list = doit.generateEmaList(data, 4);

        // entry 4
        double entry4 = 270.49;
        Assert.assertEquals(entry4, list.get(4).getVal(), 0.001);

        // entry 3
        double entry3 = Double.valueOf(df.format( (270.95 * multiplier) + (entry4 * (1.0 - multiplier)) ));
        Assert.assertEquals(entry4, list.get(4).getVal(), 0.001);

        // entry 2
        double entry2 = Double.valueOf(df.format( (270.43 * multiplier) + (entry3 * (1.0 - multiplier)) ));
        Assert.assertEquals(entry4, list.get(4).getVal(), 0.001);

        // entry 1
        double entry1 = Double.valueOf(df.format( (263.67 * multiplier) + (entry2 * (1.0 - multiplier)) ));
        Assert.assertEquals(entry4, list.get(4).getVal(), 0.001);

        // entry 0
        double entry0 = Double.valueOf(df.format( (258.05 * multiplier) + (entry2 * (1.0 - multiplier)) ));
        Assert.assertEquals(entry4, list.get(4).getVal(), 0.001);

        System.out.println("Exponential moving average test finished");
    }
}
