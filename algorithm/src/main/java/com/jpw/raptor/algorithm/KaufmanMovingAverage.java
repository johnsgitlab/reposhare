package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by john on 3/2/18.
 */
public class KaufmanMovingAverage {

    /******************
     * Developed by Perry Kaufman, Kaufman's Adaptive Moving Average (KAMA) is a moving average
     * designed to account for market noise or volatility.
     * KAMA will closely follow prices when the price swings are relatively small and the noise is low.
     * KAMA will adjust when the price swings widen and follow prices from a greater distance.
     * This trend-following indicator can be used to identify the overall trend,
     * time turning points and filter price movements.
     *
     * The settings recommended by Perry Kaufman are KAMA (10,2,30).
     *      10 is the number of periods for the Efficiency Ratio (ER).
     *      2 is the number of periods for the fastest EMA constant.
     *      30 is the number of periods for the slowest EMA constant.
     *
     *
     */

    public double getOldestValue(Quote quote) {
        return 0.0;
    }


    protected double getVolatilityDesc (List<Quote> data, int offset, int n) {

        int    maxI     = offset+n;
        double result   = 0.0;

        for ( int i=offset; i<maxI; i++ ) {
            double val = data.get(i).getClose() - data.get(i+1).getClose();
            result += Math.abs(val);
        }

        return result;
    }


    public double kaufmanMovingAverageDesc( List<Quote> data, int offset, int n, int fastPeriods, int slowPeriods, double priorKaufman) {

        /*
         * Note data is in descending order by date
         * offset is starting index into the data array
         * n is the number of periods
         * note algorithm is designed for periods > 2
         */

        // since array offest is zero base decrement n by 1
        n = n - 1;

        double  sc = 2 / (slowPeriods + 1.0);
        double  fc = 2 / (fastPeriods + 1.0);

        int     maxIndex    = offset + n;
        int     arraySize   = data.size();
        double  result      = 0.0;

        // validate there is enough data to compute the fractal moving average
        if ( arraySize >= (maxIndex+1) ) {
            // good to go
        } else {
            return result;
        }

        // compute change
        double change = Math.abs(data.get(offset).getClose() - data.get(offset + n).getClose());

        // get the volatility
        double volatility = getVolatilityDesc ( data, offset, n);

        // compute efficiency ration
        double efficiencyRatio = 0.0;
        if ( volatility > 0.0 ) {
            efficiencyRatio = change / volatility;
        }

        // compute smoothingConstant
        double temp      = (efficiencyRatio * (fc - sc)) + sc;
        double smoothing = Math.pow(temp,2);

        // compute kaufman moving average
        result = priorKaufman + (smoothing *(data.get(offset).getClose() - priorKaufman));

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        // return it
        return result;
    }


    protected double getVolatilityAsc (List<Quote> data, int offset, int n) {

        int    minI     = offset-n;
        double result   = 0.0;

        for ( int i=offset; i>minI; i-- ) {
            double val = data.get(i).getClose() - data.get(i-1).getClose();
            result += Math.abs(val);
        }

        return result;
    }


    public double kaufmanMovingAverageAsc( List<Quote> data, int offset, int n, int fastPeriods, int slowPeriods, double priorKaufman) {

        /*
         * Note data is in descending order by date
         * offset is starting index into the data array
         * n is the number of periods
         * note algorithm is designed for periods > 2
         */

        // since array offest is zero base decrement n by 1
        n = n - 1;

        double  sc = 2 / (slowPeriods + 1.0);
        double  fc = 2 / (fastPeriods + 1.0);

        int     arraySize   = data.size();
        int     end         = offset - n;

        double  result      = 0.0;

        // Validate the data
        if ( (offset < arraySize) && (end >= 0) ) {
            // good to go
        } else {
            return result;
        }

        // compute change
        double change = Math.abs(data.get(offset).getClose() - data.get(end).getClose());

        // get the volatility
        double volatility = getVolatilityAsc ( data, offset, n);

        // compute efficiency ration
        double efficiencyRatio = 0.0;
        if ( volatility > 0.0 ) {
            efficiencyRatio = change / volatility;
        }

        // compute smoothingConstant
        double temp      = (efficiencyRatio * (fc - sc)) + sc;
        double smoothing = Math.pow(temp,2);

        // compute kaufman moving average
        result = priorKaufman + (smoothing *(data.get(offset).getClose()- priorKaufman));

        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result = Double.valueOf(df.format(result));

        // return it
        return result;
    }

}
