package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Ported to new design 5/16/2019
 *
 * Fractal Adaptive Moving Average Technical Indicator (FRAMA) was developed by John Ehlers.
 * This indicator is constructed based on the algorithm of the Exponential Moving Average,
 * in which the smoothing factor is calculated based on the current fractal dimension of the price series.
 *
 */
public class FractalMovingAverage {

    protected double maxHigh ( List<Quote> data, int offset, int n) {

        int    maxI     = offset+n;
        double result   = data.get(offset).getHigh();

        for ( int i=offset; i<maxI; i++ ) {
            if ( data.get(i).getHigh() > result )
                result = data.get(i).getHigh();
        }

        return result;
    }

    protected double minLow ( List<Quote> data, int offset, int n) {

        int    maxI     = offset+n;
        double result   = data.get(offset).getLow();

        for ( int i=offset; i<maxI; i++ ) {
            if ( data.get(i).getLow() < result )
                result = data.get(i).getLow();
        }

        return result;
    }


    public double fractalMovingAverage(List<Quote> data, int offset, int n, double priorFma) {

        /*
         * Note data is in descending order by date
         * offset is starting index into the data array
         * n is the number of periods
         * sc is the slow constant
         * fc is the fast constant
         */

        int     maxIndex    = offset + n;
        int     arraySize   = data.size();
        int     halfPeriod  = n / 2;
        double  result      = 0.0;

        // validate that the number of periods is an even number
        if ( n != ( halfPeriod * 2) )
            return result;

        // validate there is enough data to compute the fractal moving average
        if ( maxIndex > arraySize )
            return result;

        // compute w
        double w = -4.6;

        // max and min values over entire period
        double maxAll = maxHigh(data, offset, n);
        double minAll = minLow (data, offset, n);

        // max and min values over half period closest to current h2
        double h2High = maxHigh(data, offset, halfPeriod);
        double h2Low  = minLow (data, offset, halfPeriod);

        // max and min values over half period furthest from current h1
        double h1High = maxHigh(data, offset+halfPeriod, halfPeriod);
        double h1Low  = minLow (data, offset+halfPeriod, halfPeriod);

        double hl   = (maxAll - minAll) / n;
        double hl1  = (h1High - h1Low) / halfPeriod;
        double hl2  = (h2High - h2Low) / halfPeriod;

        double dimen = 0.0;
        double alpha = 0.0;

        if ( hl > 0.0 && hl1 > 0.0 && hl2 > 0.0 ) {
            dimen = ( Math.log(hl1 + hl2) - Math.log(hl) ) / Math.log(2.0);
        } else {
            dimen = 0.0;
        }

        alpha = Math.exp(w * (dimen - 1.0));
        if ( alpha < 0.01 )
            alpha = 0.01;
        else if ( alpha > 1.0 )
            alpha = 1.0;

        result = priorFma + (alpha * (data.get(offset).getClose() - priorFma));

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        // return it
        return result;
    }

}
