package com.jpw.raptor.algorithm;

import com.jpw.raptor.algorithm.TestData;
import com.jpw.raptor.model.Bollinger;
import com.jpw.raptor.model.Performance;
import com.jpw.raptor.model.Quote;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 7/13/18.
 */
public class BollingerBandTest {

    public ArrayList<Quote> list;


    @Test
    public void test01() throws ParseException {

        TestData                testData = new TestData();
        List<Quote>             data     = testData.getData();
        DecimalFormat           df       = new DecimalFormat("#.###");
        DecimalFormat           percent     = new DecimalFormat("###.##");
        QuoteStandardDeviation  qsd      = new QuoteStandardDeviation();

        double sma      = Double.parseDouble(df.format( (259.49 + 258.29 + 255.77) / 3.0 ));
        double stdDev   = qsd.stdDev(data, 0, 3);

        double upper    = Double.parseDouble(df.format(sma + (2.0 * stdDev) ));
        double lower    = Double.parseDouble(df.format(sma - (2.0 * stdDev) ));
        double width    = Double.parseDouble(df.format( ((upper - lower) / sma) ));
        double percentB = Double.parseDouble(percent.format( ((259.49-lower)/(upper-lower)) * 100.0 ));

        BollingerBand   bb  = new BollingerBand();
        Bollinger       b   = bb.createBollinger(data,0, 3);

        assertEquals(sma,       b.getMiddle(), 0.001);
        assertEquals(stdDev,    b.getStdDeviation(), 0.001);
        assertEquals(upper,     b.getUpper(), 0.001);
        assertEquals(lower,     b.getLower(), 0.001);
        assertEquals(width,     b.getWidth(), 0.001);
        assertEquals(percentB,  b.getPercentB(), 0.001);

    }


}
