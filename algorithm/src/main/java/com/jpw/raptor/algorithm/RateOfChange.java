package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Roc;
import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Ported to new design 5/18/2019
 *
 * Rate of change is used to mathematically describe the percentage change in value
 * over a defined period of time, and it represents the momentum of a variable.
 *
 * Asecurity with high momentum, or one that has a positive ROC, normally outperforms
 * the market in the short term.
 *
 * If the ROC of an index or other broad-market security is over 50%,
 * investors should be wary of a bubble.
 *
 */
public class RateOfChange {

    public void generateRoc(Roc roc, double prior) {

        double          result;
        DecimalFormat   df  = new DecimalFormat("#.##");

        result = ((roc.getClose() - prior) / prior) * 100.0;
        result = Double.valueOf(df.format(result));
        roc.setValue(result);

    }

    public List<Roc> generateRocList(List<Quote> data, int period) {

        // allocate space for object list
        List<Roc> list = new ArrayList<>(data.size());

        // Create the roc list
        for ( int i=0; i<data.size(); i++) {
            Roc obv = new Roc(data.get(i));
            list.add(obv);
        }

        // generate the roc values
        for ( int i=0; i<data.size(); i++ ) {

            int priorIdx = i + period;
            if (priorIdx < data.size()) {
                Roc roc = list.get(i);
                double prior = data.get(priorIdx).getClose();
                generateRoc(roc, prior);
            }
        }

        return list;
    }
}
