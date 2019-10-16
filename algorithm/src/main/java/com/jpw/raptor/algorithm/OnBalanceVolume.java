package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Average;
import com.jpw.raptor.model.Obv;
import com.jpw.raptor.model.Quote;


import java.util.ArrayList;
import java.util.List;

/**
 * Ported to new design 5/17/2019
 *
 * On-balance volume (OBV) is a technical trading momentum indicator that uses
 * volume flow to predict changes in stock price.
 *
 * OBV shows crowd sentiment that can predict a bullish or bearish outcome.
 *
 * Comparing relative action between price bars and OBV generates more
 * actionable signals than the green or red volume histograms commonly found
 * at the bottom of price charts.
 *
 */
public class OnBalanceVolume {

    //
    // Data is in descending order by date
    private void populateMovingAvg(List<Obv> data, int n ) {

        SimpleMovingAverage smaFactory = new SimpleMovingAverage();

        for ( int i=0; i<data.size(); i++ ) {
            long sma = smaFactory.smaObv(data, i, n);
            data.get(i).setSma(sma);
        }

    }


    public List<Obv> generateObv(List<Quote> data, int n) {

        // allocate space for object list
        List<Obv> list = new ArrayList<>(data.size());

        // Create the obv list
        for ( int i=0; i<data.size(); i++) {
            Obv obv = new Obv(data.get(i));
            list.add(obv);
        }

        // Since dates are descending start at the end of the array
        // and process to the front
        int start = data.size() - 1;

        // default the first obv value to that days volume
        long obvVal = list.get(start).getVolume();
        list.get(start).setVal(obvVal);

        // create averages for each quote
        for ( int i=start -1; i>=0; i-- ) {

            double  priorClose  = list.get(i+1).getClose();
            double  currClose   = list.get(i).getClose();

            if ( currClose > priorClose ) {
                obvVal += list.get(i).getVolume();
            } else if (currClose < priorClose ) {
                obvVal -= list.get(i).getVolume();
            } else {
                obvVal += 0;
            }

            list.get(i).setVal(obvVal);
        }

        // Generate the moving values
        populateMovingAvg(list, n);

        return list;
    }
}
