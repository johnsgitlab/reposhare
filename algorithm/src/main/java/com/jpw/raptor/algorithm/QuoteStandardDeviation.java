package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Ported to new design 5/10/2019
 *
 * Compute close  standard deviation for a collection of quotes
 *
 */
public class QuoteStandardDeviation {

    // Quotes are in descending order by date
    public double stdDev(List<Quote> data, int offset, int count) {

        int     end         = offset + count;
        int     arraySize   = data.size();
        double  period      = 0.0;
        double  result      = 0.0;

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            // adjust to compute moving average for provided data
            end     = arraySize;
        }

        // Compute the mean

        SimpleMovingAverage smaFactory  = new SimpleMovingAverage();
        DecimalFormat       df          = new DecimalFormat("#.###");
        double              sma         = Double.parseDouble(df.format( smaFactory.smaQuote(data, offset, count) ));

        // Compute standard deviation
        double diff;
        double stdDev   = 0.0;
        for ( int i=offset; i<end; i++){
            diff    = data.get(i).getClose() - sma;
            stdDev  = stdDev + (diff * diff);
            period += 1.0;
        }

        if ( period > 0.0 ) {
            result = Math.sqrt(stdDev / period);
        }

        // Round to 2 decimal places
        DecimalFormat resultFormat = new DecimalFormat("#.##");
        result                     = Double.valueOf(resultFormat.format(result));

        return result;
    }
}
