package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Ported to new design 5/18/2019
 *
 * The relative strength index (RSI) is a momentum indicator to evaluate overbought or oversold
 * conditions in the price of a stock or other asset.
 *
 * Signals are considered overbought when the indicator is above 70% and oversold when the
 * indicator is below 30%.
 *
 */
public class RelativeStrengthIndicator {

    //
    // Data is in descending order by date
    public double relativeStrengthIndicator (List<Quote> data, int offset, int n) {

        DecimalFormat   df      = new DecimalFormat("#.##");
        int             start   = offset;
        int             end     = offset + n;

        // validate parameters
        if ( (end) < data.size() ) {
            // valid parameters
        } else {
            System.out.println("Not enough data for relative strength indicator offset " );
            return 0.0;
        }

        //
        // comput gains and losses
        double gain     = 0.0;
        double loss     = 0.0;
        for ( int i=start; i<end; i++ ) {
            double change = data.get(i).getClose() - data.get(i+1).getClose();

            if ( change > 0.0 ) {
                gain += change;
            } else {
                loss += Math.abs(change);
            }
        }

        //
        // compute average gain and loss
        double avgGain  = Double.parseDouble(df.format(gain / (double) n ));
        double avgLoss  = Double.parseDouble(df.format(loss / (double) n ));

        //
        // Compute relative strength
        double rs = 0.0;
        if ( loss > 0.0 ) {
            rs = Double.parseDouble(df.format(rs = avgGain / avgLoss ));
        }

        //
        // Compute relative strength index
        double rsi = Double.parseDouble(df.format(100.0 - (100.0 / (1.0 + rs)) ));

        return rsi;
    }

}
