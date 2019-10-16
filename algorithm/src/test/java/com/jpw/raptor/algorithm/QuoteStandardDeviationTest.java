package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

public class QuoteStandardDeviationTest {


    @Test
    // test beginning of array
    public void test01() throws ParseException {

        TestData        testData = new TestData();
        List<Quote>     data     = testData.getData();
        DecimalFormat   df       = new DecimalFormat("#.##");

        double sma = Double.valueOf(df.format( (259.49 + 258.29 + 255.77) / 3.0 ));

        double sum = 0.0;
        sum += ((259.49 -sma) * (259.49 -sma));
        sum += ((258.29 -sma) * (258.29 -sma));
        sum += ((255.77 -sma) * (255.77 -sma));

        double stdDev = Double.valueOf(df.format( Math.sqrt(sum / 3.0) ));

        QuoteStandardDeviation qsd = new QuoteStandardDeviation();

        double  result = qsd.stdDev(data, 0, 3);

        System.out.println("result " + result);

        assertEquals(stdDev, result, 0.001);

    }

    @Test
    // test not enough data
    public void test02() throws ParseException {

        TestData        testData = new TestData();
        List<Quote>     data     = testData.getData();
        DecimalFormat   df       = new DecimalFormat("#.##");

        double sma = Double.valueOf(df.format( (269.44 + 269.53 + 268.99) / 3.0 ));

        double sum = 0.0;
        sum += ((269.44 -sma) * (269.44 -sma));
        sum += ((269.53 -sma) * (269.53 -sma));
        sum += ((268.99 -sma) * (268.99 -sma));

        double stdDev = Double.valueOf(df.format( Math.sqrt(sum / 3.0) ));

        QuoteStandardDeviation qsd = new QuoteStandardDeviation();

        double  result = qsd.stdDev(data, 261, 4);

        assertEquals(stdDev, result, 0.001);

    }


    @Test
    // Only one data item
    public void test03() throws ParseException {

        TestData        testData = new TestData();
        List<Quote>     data     = testData.getData();
        DecimalFormat   df       = new DecimalFormat("#.##");

        double sma = 268.99;

        double sum = 0.0;
        sum += ((268.99 -sma) * (268.99 -sma));

        double stdDev = Double.valueOf(df.format( Math.sqrt(sum / 1.0) ));

        QuoteStandardDeviation qsd = new QuoteStandardDeviation();

        double  result = qsd.stdDev(data, 263, 4);

        assertEquals(stdDev, result, 0.001);

    }

}
