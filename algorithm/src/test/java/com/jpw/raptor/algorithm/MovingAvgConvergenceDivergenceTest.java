package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Bollinger;
import com.jpw.raptor.model.Macd;
import com.jpw.raptor.model.Quote;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 9/18/18.
 */
public class MovingAvgConvergenceDivergenceTest {

    public ArrayList<Quote> list;


    @Test
    public void test01() throws ParseException {

        System.out.println("test 01 started");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Quote[] testData = {
                new Quote("IVV", fmt.parse("2019-01-09"), 259.09, 260.48, 257.72, 259.49, 4096900),
                new Quote("IVV", fmt.parse("2019-01-08"), 258.38, 258.82, 255.52, 258.29, 5340600),
                new Quote("IVV", fmt.parse("2019-01-07"), 254.24, 257.46, 253.21, 255.77, 5961300),
                new Quote("IVV", fmt.parse("2019-01-04"), 249.06, 254.59, 248.64, 254.06, 6430100),
                new Quote("IVV", fmt.parse("2019-01-03"), 249.79, 250.03, 245.08, 245.43, 5828300),
                new Quote("IVV", fmt.parse("2019-01-02"), 247.54, 252.7, 247.42, 251.72, 5978400),
                new Quote("IVV", fmt.parse("2018-12-31"), 251.04, 251.63, 248.99, 251.61, 10117800),
                new Quote("IVV", fmt.parse("2018-12-28"), 251.03, 252.88, 247.95, 249.33, 10856300)
        };

        List<Quote> data = Arrays.asList( testData );

        ExponentialMovingAverage    ema        = new ExponentialMovingAverage();

        double                      fastDays   = 4.0;
        double                      fastMult   = 2.0 / (fastDays + 1.0);

        double                      slowDays   = 6.0;
        double                      slowMult   = 2.0 / (slowDays + 1.0);


        double fastEma7     = 249.33;
        double slowEma7     = 249.33;
        double macd7        = 0.0;
        double signal7      = 0.0;
        double histogram7   = 0.0;

        double fastEma6     = ema.getExpAvg(251.61, fastEma7, fastDays);
        double slowEma6     = ema.getExpAvg(251.61, slowEma7, slowDays);
        double macd6        = fastEma6 - slowEma6;
        double signal6      = ema.getExpAvg(macd6, signal7, 9);
        double histogram6   = macd6 - signal6;

        double fastEma5     = ema.getExpAvg(251.72, fastEma6, fastDays);
        double slowEma5     = ema.getExpAvg(251.72, slowEma6, slowDays);
        double macd5        = fastEma5 - slowEma5;
        double signal5      = ema.getExpAvg(macd5, signal6, 9);
        double histogram5   = macd5 - signal5;

        double fastEma4     = ema.getExpAvg(245.43, fastEma5, fastDays);
        double slowEma4     = ema.getExpAvg(245.43, slowEma5, slowDays);
        double macd4        = fastEma4 - slowEma4;
        double signal4      = ema.getExpAvg(macd4, signal5, 9);
        double histogram4   = macd4 - signal4;

        double fastEma3     = ema.getExpAvg(254.06, fastEma4, fastDays);
        double slowEma3     = ema.getExpAvg(254.06, slowEma4, slowDays);
        double macd3        = fastEma3 - slowEma3;
        double signal3      = ema.getExpAvg(macd3, signal4, 9);
        double histogram3   = macd3 - signal3;

        double fastEma2     = ema.getExpAvg(255.77, fastEma3, fastDays);
        double slowEma2     = ema.getExpAvg(255.77, slowEma3, slowDays);
        double macd2        = fastEma2 - slowEma2;
        double signal2      = ema.getExpAvg(macd2, signal3, 9);
        double histogram2   = macd2 - signal2;

        double fastEma1     = ema.getExpAvg(258.29, fastEma2, fastDays);
        double slowEma1     = ema.getExpAvg(258.29, slowEma2, slowDays);
        double macd1        = fastEma1 - slowEma1;
        double signal1      = ema.getExpAvg(macd1, signal2, 9);
        double histogram1   = macd1 - signal1;

        double fastEma0     = ema.getExpAvg(259.49, fastEma1, fastDays);
        double slowEma0     = ema.getExpAvg(259.49, slowEma1, slowDays);
        double macd0        = fastEma0 - slowEma0;
        double signal0      = ema.getExpAvg(macd0, signal1, 9);
        double histogram0   = macd0 - signal0;



        MovingAvgConvergenceDivergence macd = new MovingAvgConvergenceDivergence();
        List<Macd> mList = macd.generateMacd(data, 4, 6);

        assertEquals ( fastEma7,    mList.get(7).getFastEma(),      0.001);
        assertEquals ( slowEma7,    mList.get(7).getSlowEma(),      0.001);
        assertEquals ( macd7,       mList.get(7).getMacd(),         0.001);
        assertEquals ( signal7,     mList.get(7).getSignal(),       0.001);
        assertEquals ( histogram7,  mList.get(7).getHistogram(),    0.001);

        assertEquals ( fastEma6,    mList.get(6).getFastEma(),      0.001);
        assertEquals ( slowEma6,    mList.get(6).getSlowEma(),      0.001);
        assertEquals ( macd6,       mList.get(6).getMacd(),         0.001);
        assertEquals ( signal6,     mList.get(6).getSignal(),       0.001);
        assertEquals ( histogram6,  mList.get(6).getHistogram(),    0.001);

        assertEquals ( fastEma5,    mList.get(5).getFastEma(),      0.001);
        assertEquals ( slowEma5,    mList.get(5).getSlowEma(),      0.001);
        assertEquals ( macd5,       mList.get(5).getMacd(),         0.001);
        assertEquals ( signal5,     mList.get(5).getSignal(),       0.001);
        assertEquals ( histogram5,  mList.get(5).getHistogram(),    0.001);

        assertEquals ( fastEma4,    mList.get(4).getFastEma(),      0.001);
        assertEquals ( slowEma4,    mList.get(4).getSlowEma(),      0.001);
        assertEquals ( macd4,       mList.get(4).getMacd(),         0.001);
        assertEquals ( signal4,     mList.get(4).getSignal(),       0.001);
        assertEquals ( histogram4,  mList.get(4).getHistogram(),    0.001);

        assertEquals ( fastEma3,    mList.get(3).getFastEma(),      0.001);
        assertEquals ( slowEma3,    mList.get(3).getSlowEma(),      0.001);
        assertEquals ( macd3,       mList.get(3).getMacd(),         0.001);
        assertEquals ( signal3,     mList.get(3).getSignal(),       0.001);
        assertEquals ( histogram3,  mList.get(3).getHistogram(),    0.001);

        assertEquals ( fastEma2,    mList.get(2).getFastEma(),      0.001);
        assertEquals ( slowEma2,    mList.get(2).getSlowEma(),      0.001);
        assertEquals ( macd2,       mList.get(2).getMacd(),         0.001);
        assertEquals ( signal2,     mList.get(2).getSignal(),       0.001);
        assertEquals ( histogram2,  mList.get(2).getHistogram(),    0.001);

        assertEquals ( fastEma1,    mList.get(1).getFastEma(),      0.001);
        assertEquals ( slowEma1,    mList.get(1).getSlowEma(),      0.001);
        assertEquals ( macd1,       mList.get(1).getMacd(),         0.001);
        assertEquals ( signal1,     mList.get(1).getSignal(),       0.001);
        assertEquals ( histogram1,  mList.get(1).getHistogram(),    0.001);

        assertEquals ( fastEma0,    mList.get(0).getFastEma(),      0.001);
        assertEquals ( slowEma0,    mList.get(0).getSlowEma(),      0.001);
        assertEquals ( macd0,       mList.get(0).getMacd(),         0.001);
        assertEquals ( signal0,     mList.get(0).getSignal(),       0.001);
        assertEquals ( histogram0,  mList.get(0).getHistogram(),    0.001);

        System.out.println("test 01 ended");

    }
}