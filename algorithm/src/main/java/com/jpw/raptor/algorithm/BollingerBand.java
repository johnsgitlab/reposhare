package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Bollinger;
import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ported to new design 5/14/2019
 *
 * A Bollinger Band defines a set of lines plotted two standard deviations (positively and negatively)
 * away from a 20 day simple moving average (SMA) of the security's price.
 *
 * When the markets become more volatile the bands widen; during less volatile periods, the bands contract.
 *
 */
public class BollingerBand {

    // Quotes are in descending order by date
    public Bollinger createBollinger(List<Quote> data, int offset, int periods) {

        DecimalFormat   df      = new DecimalFormat("#.###");
        DecimalFormat   percent = new DecimalFormat("###.##");
        Bollinger       result  = new Bollinger(data.get(offset));

        // current close
        double close = data.get(offset).getClose();

        // Compute middle band which is a simple moving average
        SimpleMovingAverage smaFactory  = new SimpleMovingAverage();
        double              sma         = Double.parseDouble(df.format( smaFactory.smaQuote(data, offset, periods) ));
        result.setMiddle(sma);

        // Compute standard deviation
        QuoteStandardDeviation qsd = new QuoteStandardDeviation();
        double stdDev   = qsd.stdDev(data, offset, periods);
        result.setStdDeviation(stdDev);

        // Compute lower
        double lower = sma - (2.0 * stdDev);
        result.setLower(Double.parseDouble(df.format(sma - (2.0 * stdDev))));

        // Compute upper
        double upper = sma + (2.0 * stdDev);
        result.setUpper(Double.parseDouble(df.format(sma + (2.0 * stdDev))));

        // Compute width
        double width = (upper - lower) / sma;
        result.setWidth(Double.parseDouble(df.format(width)));

        // Compute percent B
        // dont know about the * 100
        double percentB =  ((close-lower)/(upper-lower)) * 100.0;
        result.setPercentB(Double.parseDouble(percent.format(percentB)));

        return result;
    }


    // Quotes are in descending order by date
    public List<Bollinger> generateBollinger(List<Quote> data, int periods) {

        // validate enough data is provided
        if (data.size() <= periods)
            return Collections.<Bollinger>emptyList();

        // allocate space for list
        List<Bollinger> list = new ArrayList<>(data.size());

        // create bollinger object for each quote
        int end = data.size();
        for ( int i=0; i<end; i++ ) {
            list.add( createBollinger( data, i, periods) );
        }

        return list;
    }
}
