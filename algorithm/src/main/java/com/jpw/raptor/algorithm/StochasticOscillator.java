package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.Stochastic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Ported to new design 5/18/2019
 *
 * A momentum indicator comparing a particular closing price of a security to
 * a range of its prices over a certain period of time. The sensitivity of the oscillator
 * to market movements is reducible by adjusting that time period or by taking a
 * moving average of the result. It is used to generate overbought and oversold trading signals,
 * utilizing a 0-100 bounded range of values.
 *
 * Stochastic oscillators are sensitive to momentum rather than absolute price.
 *
 * Traditionally, readings over 80 are considered in the overbought range, and readings
 * under 20 are considered oversold. However, these are not always indicative of impending reversal
 */
public class StochasticOscillator {

    //
    // Data is in descending order by date
    public double maxHigh (List<Quote> data, int offset, int n) {

        // validate there is enough data
        if ( (offset + n) > data.size() )
            return 0.0;

        // set default
        double  result = data.get(offset).getHigh();

        for ( int i=offset; i<offset+n; i++){
            if (data.get(i).getHigh() > result)
                result = data.get(i).getHigh();
        }

        return result;
    }

    //
    // Data is in descending order by date
    public double minLow ( List<Quote> data, int offset, int n) {

        // validate there is enough data
        if ( (offset + n) > data.size() )
            return 0.0;

        // set default
        double  result = data.get(offset).getLow();

        for ( int i=offset; i<offset+n; i++){
            if (data.get(i).getLow() < result)
                result = data.get(i).getLow();
        }

        return result;
    }


    //
    // Data is in descending order by date
    public Stochastic createOscillator(List<Quote> data, int offset, int n) {

        Stochastic result = new Stochastic(data.get(offset));

        // get the k values
        double low      = minLow (data, offset, n);
        double high     = maxHigh (data, offset, n);
        double k;
        if ( high == 0.0 )
            k = 0.0;
        else
            k =  ((data.get(offset).getClose() - low) / (high - low)) * 100.0;

        // Update object
        // Round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        result.setKFast(Double.valueOf(df.format(k)));;

        return result;
    }


    public List<Stochastic> generateStochastic(List<Quote> data, int periods) {

        // allocate space for stochastic object list
        List<Stochastic> stoList = new ArrayList<>(data.size());

        // create stochastic object with fast K value for each quote
        for ( int i=0; i<data.size(); i++ ) {
            stoList.add( createOscillator( data, i, periods) );
        }


        DecimalFormat   df      = new DecimalFormat("#.##");


        // Compute fast D
        SimpleMovingAverage sma = new SimpleMovingAverage();
        for ( int i=0; i<data.size(); i++ ) {
            double val = sma.smaDfast(stoList, i, 3);
            stoList.get(i).setDFast(val);
            stoList.get(i).setKSlow(val);
        }

        // Create slow D for each object (a three period slow k moving average)
        for ( int i=0; i<data.size(); i++ ) {
            double val = sma.smaDSlow(stoList, i, 3);
            stoList.get(i).setDSlow(val);
        }

        return stoList;
    }
}
