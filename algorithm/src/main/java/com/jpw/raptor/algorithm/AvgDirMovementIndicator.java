package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Adx;
import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Ported to new design 5/13/2019
 *
 * The Average Directional Index (ADX) along with the
 * Negative Directional Indicator (-DI) and the Positive Directional Indicator (+DI)
 * are momentum indicators. The ADX helps investors determine trend strength
 * while -DI and +DI help determine trend direction.
 *
 * The ADX identifies a strong trend when the ADX is over 25 and a weak trend when the ADX is below 20.
 *
 * The price is moving up when +DI is above -DI, and the price is moving down when -DI is above +DI.
 *
 * Crosses between +DI and -DI are potential trading signals as bears or bulls gain the upper hand.
 */
public class AvgDirMovementIndicator {


    // Quotes are in descending order by date
    public List<Adx> generateAdx(List<Quote> data, int periods) {

        DecimalFormat            df  = new DecimalFormat("#.##");
        ExponentialMovingAverage ema = new ExponentialMovingAverage();

        // allocate space for object list
        List<Adx> adxList = new ArrayList<>(data.size());

        // create Average Directional Movement object for each quote
        for ( int i=0; i<data.size(); i++ ) {
            adxList.add( new Adx( data.get(i) ));
        }

        // The first true range is ABS(first high - first low)
        // The first DM plus  is zero
        // The first DM minus is zero
        int firstIdx = data.size() - 1;
        double firstTrueRng = Math.abs( data.get(firstIdx).getHigh() - data.get(firstIdx).getLow() );
        adxList.get(firstIdx).setTrueRange(firstTrueRng);
        adxList.get(firstIdx).setDmPlus( 0.0 );
        adxList.get(firstIdx).setDmMinus( 0.0 );

        // compute true range, dm plus, dm minus
        for ( int i =  firstIdx - 1; i >= 0; i-- ) {
            Quote today     = data.get(i);
            Quote yesterday = data.get(i+1);

            adxList.get(i).setTrueRange( getTrueRange(today, yesterday) );
            adxList.get(i).setDmPlus( getDmPlus(today, yesterday) );
            adxList.get(i).setDmMinus( getDmMinus(today, yesterday) );
        }


        // The first average true range is the first true range
        // The first average DM plus  is first dm plus
        // The first averate DM minus is first dm minus
        adxList.get(firstIdx).setAverageTrueRange(adxList.get(firstIdx).getTrueRange());
        adxList.get(firstIdx).setAverageDmPlus(adxList.get(firstIdx).getDmPlus());
        adxList.get(firstIdx).setAverageDmMinus( adxList.get(firstIdx).getDmMinus());

        // compute average true range, average dm plus, average dm minus and dx
        for ( int i =  firstIdx - 1; i >= 0; i-- ) {
            Adx    adxObj       = adxList.get(i);
            Adx    adxPriorObj  = adxList.get(i+1);

            double averageTrueRange = ema.getExpAvg(adxObj.getTrueRange(), adxPriorObj.getAverageTrueRange(), periods);
            adxObj.setAverageTrueRange(averageTrueRange);

            double averageDmPlus = ema.getExpAvg(adxObj.getDmPlus(), adxPriorObj.getAverageDmPlus(), periods);
            adxObj.setAverageDmPlus(averageDmPlus);

            double averageDmMinus = ema.getExpAvg(adxObj.getDmMinus(), adxPriorObj.getAverageDmMinus(), periods);
            adxObj.setAverageDmMinus(averageDmMinus);

            double diPlus  = Double.valueOf(df.format( 100.0 * ( averageDmPlus  / averageTrueRange )));
            adxObj.setDiPlus( diPlus );

            double diMinus = Double.valueOf(df.format( 100.0 * ( averageDmMinus / averageTrueRange )));
            adxObj.setDiMinus( diMinus );

            double diDiff  = Double.valueOf(Math.abs( diPlus - diMinus ));
            double diSum   = Double.valueOf(df.format( diPlus + diMinus));
            double dx      = Double.valueOf(df.format( 100.0 * ( diDiff / diSum )));
            adxObj.setDx( dx );
        }

        // The first average DX is the first dx
        adxList.get(firstIdx).setAverageDx( adxList.get(firstIdx).getDx() );

        // compute remaining average dx
        for ( int i =  firstIdx - 1; i >= 0; i-- ) {
            Adx adxObj      = adxList.get(i);
            Adx adxPriorObj = adxList.get(i + 1);

            double averageDx = ema.getExpAvg(adxObj.getDx(), adxPriorObj.getAverageDx(), periods);
            adxObj.setAverageDx(averageDx);
        }

        return adxList;
    }

    // get true range
    private double getTrueRange(Quote today, Quote yesterday ) {

        double result = 0.0;

        if ( Math.abs(today.getHigh() - today.getLow()) > result )
            result = Math.abs(today.getHigh() - today.getLow());

        if ( Math.abs(today.getHigh() - yesterday.getClose()) > result)
            result =  Math.abs(today.getHigh() - yesterday.getClose());

        if ( Math.abs(today.getLow() - yesterday.getClose()) > result)
            result =  Math.abs(today.getLow() - yesterday.getClose());

        return result;
    }

    // get plus directional movement
    private double getDmPlus(Quote today, Quote yesterday ) {

        double plusMove  = today.getHigh() - yesterday.getHigh();
        double minusMove = yesterday.getLow() - today.getLow();

        if ( plusMove < 0.0 ) plusMove = 0.0;
        if ( minusMove < 0.0 ) minusMove = 0.0;

        // plus move
        if ( plusMove > minusMove )
            return plusMove;
        else
            return 0.0;
    }

    // get minus directional movement
    private double getDmMinus(Quote today, Quote yesterday ) {

        double result = 0.0;

        double plusMove  = today.getHigh() - yesterday.getHigh();
        double minusMove = yesterday.getLow() - today.getLow();

        if ( plusMove < 0.0 ) plusMove = 0.0;
        if ( minusMove < 0.0 ) minusMove = 0.0;

        // plus move
        if ( minusMove > plusMove )
            return minusMove;
        else
            return 0.0;
    }

}
