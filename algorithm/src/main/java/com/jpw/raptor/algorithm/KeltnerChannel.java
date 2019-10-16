package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Adx;
import com.jpw.raptor.model.Bollinger;
import com.jpw.raptor.model.Keltner;
import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ported to new design 7/8/2019
 *
 * The Keltner Channel is an Envelop-based indicator having three lines:
 *
 *     Middle Line: 20-period Exponential Moving Average (EMA)
 *     Upper Channel Line: 20 EMA + (2 * Average True Range)
 *     Lower Channel Line: 20 EMA – (2 * Average True Range)
 *
 *     Price reversals usually occur when there’s an extreme move into market structure (like Support or Resistance).
 *     Look for the price to close outside the Keltner Channel. This tells you the price is at an extreme level.
 *
 */
public class KeltnerChannel {


    // get true range
    private double getTrueRange(Quote today, Quote yesterday ) {

        DecimalFormat   df      = new DecimalFormat("#.##");
        double          result  = 0.0;

        if ( Math.abs(today.getHigh() - today.getLow()) > result )
            result = Math.abs(today.getHigh() - today.getLow());

        if ( Math.abs(today.getHigh() - yesterday.getClose()) > result)
            result =  Math.abs(today.getHigh() - yesterday.getClose());

        if ( Math.abs(today.getLow() - yesterday.getClose()) > result)
            result =  Math.abs(today.getLow() - yesterday.getClose());

        return Double.valueOf(df.format( result));
    }


    // Quotes are in descending order by date
    public List<Keltner> generateKeltner(List<Quote> data) {

        // allocate space for object list
        List<Keltner> keltnerList = new ArrayList<>(data.size());

        // create object for each quote
        for ( int i=0; i<data.size(); i++ ) {
            keltnerList.add( new Keltner( data.get(i) ));
        }

        // The first true range is ABS(first high - first low)
        // The first DM plus  is zero
        // The first DM minus is zero
        int firstIdx = data.size() - 1;
        double firstTrueRng = Math.abs( data.get(firstIdx).getHigh() - data.get(firstIdx).getLow() );
        keltnerList.get(firstIdx).setTrueRange(firstTrueRng);

        // compute true range
        for ( int i =  firstIdx - 1; i >= 0; i-- ) {
            Quote today     = data.get(i);
            Quote yesterday = data.get(i+1);

            keltnerList.get(i).setTrueRange( getTrueRange(today, yesterday) );
        }

        DecimalFormat               df  = new DecimalFormat("#.##");
        ExponentialMovingAverage    ema = new ExponentialMovingAverage();
        SimpleMovingAverage         sma = new SimpleMovingAverage();

        // compute average true range, average dm plus, average dm minus and dx
        for ( int i =  firstIdx - 1; i >= 0; i-- ) {

            Keltner obj       = keltnerList.get(i);
            Keltner priorObj  = keltnerList.get(i+1);

            double  avgTrueRange = sma.smaKeltnerAvgTrueRange(keltnerList, i, 14);
            double  middle       = ema.getExpAvg(obj.getClose(), priorObj.getClose(), 20);
            double  upper        = Double.valueOf(df.format(middle + (2.0 * avgTrueRange)) );
            double  lower        = Double.valueOf(df.format(middle - (2.0 * avgTrueRange)) );

            obj.setAvgTrueRange(avgTrueRange);
            obj.setMiddle( middle );
            obj.setUpper( upper );
            obj.setLower( lower );
        }

        return keltnerList;
    }

}
