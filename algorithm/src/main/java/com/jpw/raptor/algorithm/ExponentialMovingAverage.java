package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Average;
import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ported to new design 5/16/2019
 *
 * An exponential moving average (EMA) is a type of moving average (MA) that places a
 * greater weight and significance on the most recent data points.
 *
 * An exponentially weighted moving average reacts more significantly to recent price changes
 * than a simple moving average (SMA), which applies an equal weight to all observations in the period.
 *
 */
public class ExponentialMovingAverage {

    public double getExpAvg(double value, double priorExpAvg, double days) {

        double multiplier = 2.0 / (days + 1.0);
        double result1 = (value * multiplier) + (priorExpAvg * (1.0 - multiplier));

        /*
         *  note the two equations are the same
         *  (value * multiplier) + ( priorExpAvg * (1.0 - multiplier) )
         *  ( (value-priorExpAvg) * multiplier ) + (priorExpAvg)
         */

        // Round to 2 decimal places and return it
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(result1));

    }


    // Quotes are in descending order by date
    public List<Average> generateEmaList(List<Quote> data, int days) {

        // allocate space for averages object list
        List<Average> avgList = new ArrayList<>(data.size());

        // populate the list with default data
        for ( Quote q : data ) {
            Average a = new Average();
            a.setSymbol(q.getSymbol());
            a.setDate(q.getDate());
            a.setAvgType(Average.AverageType.EMA);
            a.setDays(days);
            a.setVal(q.getClose());
            avgList.add(a);
        }

        // Since dates are descending start at the end of the array
        // and process to the front
        int start = data.size() - 1;

        // Note the first EMA is just the close which has already been set

        // create averages for each quote
        for ( int i=start -1; i>=0; i-- ) {

            // Init Average record
            Average curr  = avgList.get(i);
            Average prior = avgList.get(i + 1);

            // Generate Exponental moving averages
            curr.setVal(getExpAvg(curr.getVal(), prior.getVal(), (double) days));
        }

        return avgList;
    }

    /************** Cut line ******************************************/

    public List<Average> generateEmaListDesc(List<Quote> data, int days) {

        Collections.reverse(data);

        // allocate space for averages object list
        List<Average> avgList = generateEmaListAsc(data, days);

        Collections.reverse(avgList);

        return avgList;
    }

    public List<Average> generateEmaListAsc(List<Quote> data, int days) {

        // allocate space for averages object list
        List<Average> avgList = new ArrayList<>(data.size());

        // Note the first EMA is just the close
        // Init Average record
        Average prior = new Average();
        prior.setSymbol(data.get(0).getSymbol());
        prior.setDate(data.get(0).getDate());
        prior.setAvgType(Average.AverageType.EMA);
        prior.setDays(days);

        // Generate Simple moving averages
        prior.setVal(data.get(0).getClose());

        avgList.add(prior);

        // create averages for each quote
        for ( int i=1; i<data.size(); i++ ) {

            // Init Average record
            Average avg = new Average();
            avg.setSymbol(data.get(i).getSymbol());
            avg.setDate(data.get(i).getDate());
            avg.setAvgType(Average.AverageType.SMA);
            avg.setDays(days);

            // Generate Exponental moving averages
            avg.setVal(getExpAvg(data.get(i).getClose(), prior.getVal(), (double) days));

            avgList.add(avg);
            prior = avg;
        }
        return avgList;
    }
}
