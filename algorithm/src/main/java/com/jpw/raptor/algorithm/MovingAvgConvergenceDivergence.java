package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Average;
import com.jpw.raptor.model.Bollinger;
import com.jpw.raptor.model.Macd;
import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ported to new design 5/16/2019
 *
 * Moving Average Convergence Divergence (MACD) is a trend-following momentum indicator
 * that shows the relationship between two moving averages of a security’s price.
 *
 * The MACD is calculated by subtracting the 26-period Exponential Moving Average (EMA)
 * from the 12-period EMA. The result of that calculation is the MACD line.
 *
 * A nine-day EMA of the MACD, called the "signal line," is then plotted on top of the MACD line,
 * which can function as a trigger for buy and sell signals.
 *
 */
public class MovingAvgConvergenceDivergence {


    // Quotes are in descending order by date
    public List<Macd> generateMacd(List<Quote> data, int fastPeriods, int slowPeriods) {

        // validate enough data is provided
        if (data.size() <= slowPeriods)
            return Collections.<Macd>emptyList();

        // allocate space for stochastic object list
        List<Macd> list = new ArrayList<>(data.size());

        // populate the list with default data
        for ( Quote q : data ) {
            Macd m = new Macd(q);
            m.setFastEma(q.getClose());
            m.setSlowEma(q.getClose());
            m.setMacd(0.0);
            m.setSignal(0.0);
            m.setHistogram(0.0);
            list.add(m);
        }

        // helper objects
        double                   fastEma;
        double                   slowEma;
        double                   macd;
        double                   signal;
        double                   histogram;
        ExponentialMovingAverage ema        = new ExponentialMovingAverage();
        DecimalFormat            df         = new DecimalFormat("#.##");

        // Since dates are descending start at the end of the array
        // and process to the front
        int start = data.size() - 1;

        // Note the first entry has already been set

        // create averages for each quote
        for ( int i=start-1; i>=0; i-- ) {

            Macd obj = list.get(i);

            fastEma     = ema.getExpAvg(data.get(i).getClose(), list.get(i+1).getFastEma(), fastPeriods);
            slowEma     = ema.getExpAvg(data.get(i).getClose(), list.get(i+1).getSlowEma(), slowPeriods);
            macd        = fastEma - slowEma;
            signal      = ema.getExpAvg(macd, list.get(i+1).getSignal(), 9);
            histogram   = macd - signal;

            obj.setFastEma(Double.valueOf(df.format(fastEma)));
            obj.setSlowEma(Double.valueOf(df.format(slowEma)));
            obj.setMacd(Double.valueOf(df.format(macd)));
            obj.setSignal(Double.valueOf(df.format(signal)));
            obj.setHistogram(Double.valueOf(df.format(histogram)));
        }

        return list;
    }
}
