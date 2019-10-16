package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 3/14/18.
 */

public class RelativeStrengthIndicatorTest {


    @Before
    public void setup() throws ParseException {




    }

    @Test
    public void test() throws ParseException {

        System.out.println(" test started");

        DecimalFormat    df  = new DecimalFormat("#.##");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Quote[] dataArray = {
                new Quote("SPY", sdf.parse("20160219"), 183.36, 186.42, 182.41, 46.76, 218437700),  // rsi 51.73
                new Quote("SPY", sdf.parse("20160218"), 183.36, 186.42, 182.41, 47.21, 218437700),  // rsi56.52
                new Quote("SPY", sdf.parse("20160217"), 183.36, 186.40, 182.39, 48.08, 148214300),  // rsi 69.46
                new Quote("SPY", sdf.parse("20160216"), 183.36, 186.38, 182.37, 47.61, 184512600),
                new Quote("SPY", sdf.parse("20160215"), 183.36, 186.36, 182.35, 47.57, 191526500),
                new Quote("SPY", sdf.parse("20160214"), 183.36, 186.34, 182.19, 48.2, 180788300),
                new Quote("SPY", sdf.parse("20160213"), 183.36, 186.32, 182.21, 49.23, 139531700),
                new Quote("SPY", sdf.parse("20160212"), 183.36, 186.30, 182.23, 49.25, 205054900),
                new Quote("SPY", sdf.parse("20160211"), 183.36, 186.28, 182.25, 47.54, 205054900),
                new Quote("SPY", sdf.parse("20160210"), 183.36, 186.26, 182.27, 47.69, 205054900),
                new Quote("SPY", sdf.parse("20160209"), 183.36, 186.24, 182.29, 46.83, 205054900),
                new Quote("SPY", sdf.parse("20160208"), 183.36, 186.22, 182.31, 46.03, 205054900),
                new Quote("SPY", sdf.parse("20160207"), 183.36, 186.20, 182.33, 46.08, 205054900),
                new Quote("SPY", sdf.parse("20160206"), 183.36, 186.18, 182.17, 46.23, 205054900),
                new Quote("SPY", sdf.parse("20160205"), 183.36, 186.16, 182.15, 46.5, 205054900),
                new Quote("SPY", sdf.parse("20160204"), 183.36, 186.14, 182.13, 46.26, 205054900),
                new Quote("SPY", sdf.parse("20160203"), 183.36, 186.12, 182.11, 45.15, 205054900)
        };

        List<Quote> data = Arrays.asList( dataArray );

        double i0AvgLoss = 0.26;
        double i0AvgGain = 0.28;
        double rs0       = Double.parseDouble(df.format(i0AvgGain / i0AvgLoss ));
        double rsi0      = Double.parseDouble(df.format(100.0 - (100.0 / (1.0 + rs0)) ));

        double i1AvgLoss = 0.23;
        double i1AvgGain = 0.29;
        double rs1       = Double.parseDouble(df.format(i1AvgGain / i1AvgLoss ));
        double rsi1      = Double.parseDouble(df.format(100.0 - (100.0 / (1.0 + rs1)) ));

        double i2AvgLoss = 0.16;
        double i2AvgGain = 0.37;
        double rs2       = Double.parseDouble(df.format(i2AvgGain / i2AvgLoss ));
        double rsi2      = Double.parseDouble(df.format(100.0 - (100.0 / (1.0 + rs2)) ));

        double rsi3      = 0.0;

        RelativeStrengthIndicator rsi = new RelativeStrengthIndicator();

        assertEquals(rsi0, rsi.relativeStrengthIndicator(data, 0, 14), 0.001);
        assertEquals(rsi1, rsi.relativeStrengthIndicator(data, 1, 14), 0.001);
        assertEquals(rsi2, rsi.relativeStrengthIndicator(data, 2, 14), 0.001);
        assertEquals(rsi3, rsi.relativeStrengthIndicator(data, 3, 14), 0.001);

        System.out.println(" test finished");
    }
}
