package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.List;

public class GeometricMean {

    // Quotes are in descending order by date
    public double gmQuote(List<Quote> data, int offset, int count) {

        int     end         = offset + count;
        int     arraySize   = data.size();
        double  divisor     = (double) count;
        double  sum         = 0.0;
        double  result      = 0.0;
        double  result_fmt  = 0.0;

        // The moving average includes the current day
        // Note data is in descending order by date

        // count must be at least 1
        if ( count < 1 ) {
            return result;
        }

        // validate there is enough data to compute the moving average
        if ( end > arraySize ) {
            return result;
        }

        // loop through array and sum the closes
        for ( int i=offset; i<end; i++ ) {
            sum = sum + (double)Math.log(data.get(i).getClose());
        }

        // Compute the mean
        result = Math.exp(sum / divisor);

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result_fmt = Double.valueOf(df.format(result));

        // return it
        return result_fmt;
    }

}
