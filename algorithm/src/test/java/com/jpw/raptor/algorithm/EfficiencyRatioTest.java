package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import org.junit.Test;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;


public class EfficiencyRatioTest {

    @Test
    public void test01() throws ParseException {

        TestData    testData = new TestData();
        List<Quote> data     = testData.getData();

        DecimalFormat   df = new DecimalFormat("#.##");
        EfficiencyRatio er = new EfficiencyRatio();

        double  result;

        // test not enough data
        result = er.generateValue(data, 261, 3);
        assertEquals(0.00, result, 0.001);

    }


    @Test
    public void test02() throws ParseException {

        TestData    testData = new TestData();
        List<Quote> data     = testData.getData();

        DecimalFormat   df = new DecimalFormat("#.##");
        EfficiencyRatio er = new EfficiencyRatio();

        int     size = data.size();
        double  result;
        double  net;
        double  v1;
        double  v2;
        double  v3;
        double  ratio;
        double  expected;

        // test beginning of array
        result      = er.generateValue(data, 0, 3);
        net         = Math.abs(259.49 - 254.06);
        v1          = Math.abs(259.49 - 258.29);
        v2          = Math.abs(258.29 - 255.77);
        v3          = Math.abs(255.77 - 254.06);
        ratio       = net / ( v1 + v2 + v3);
        expected    = Double.valueOf(df.format(ratio));
        assertEquals ( expected, result, 0.001);
    }


    @Test
    public void test03() throws ParseException {

        TestData    testData = new TestData();
        List<Quote> data     = testData.getData();

        DecimalFormat   df = new DecimalFormat("#.##");
        EfficiencyRatio er = new EfficiencyRatio();

        int     size = data.size();
        double  result;
        double  net;
        double  v1;
        double  v2;
        double  v3;
        double  ratio;
        double  expected;

        // test middle of array
        result      = er.generateValue(data, 2, 3);
        net         = Math.abs(255.77 - 251.72);
        v1          = Math.abs(255.77 - 254.06);
        v2          = Math.abs(254.06 - 245.43);
        v3          = Math.abs(245.43 - 251.72);
        ratio       = net / ( v1 + v2 + v3);
        expected    = Double.valueOf(df.format(ratio));
        assertEquals ( expected, result, 0.001);
    }


    @Test
    public void test04() throws ParseException {

        TestData    testData = new TestData();
        List<Quote> data     = testData.getData();

        DecimalFormat   df = new DecimalFormat("#.##");
        EfficiencyRatio er = new EfficiencyRatio();

        int     size = data.size();
        double  result;
        double  net;
        double  v1;
        double  v2;
        double  v3;
        double  ratio;
        double  expected;

        // test end of array
        result      = er.generateValue(data, size - 4, 3);
        net         = Math.abs(269.12 - 268.99);
        v1          = Math.abs(269.12 - 269.46);
        v2          = Math.abs(269.46 - 269.53);
        v3          = Math.abs(269.53 - 268.99);
        ratio       = net / ( v1 + v2 + v3);
        expected    = Double.valueOf(df.format(ratio));
        assertEquals ( expected, result, 0.001);
    }

}
