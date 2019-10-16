package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Pp;
import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Ported to new design 5/10/2019
 *
 * Used to determine the overall trend of the market over different time frames.
 * The pivot point itself is simply the average of the high, low and closing prices
 * from the previous trading day. On the subsequent day, trading above the pivot point
 * is thought to indicate ongoing bullish sentiment,
 * while trading below the pivot point indicates bearish sentiment.
 *
 */
public class PivotPoint {


    protected void fibonacci(Pp point, Quote prior) {

        double pivotPoint;

        double support1;
        double resistance1;

        double support2;
        double resistance2;

        double support3;
        double resistance3;

        DecimalFormat df      = new DecimalFormat("#.##");

        pivotPoint  = Double.valueOf(df.format( (prior.getHigh() + prior.getLow() + prior.getClose())/3.0 ) );

        support1    = Double.valueOf(df.format( pivotPoint - ( 0.382 * ( prior.getHigh() - prior.getLow() ) ) ));
        support2    = Double.valueOf(df.format( pivotPoint - ( 0.618 * ( prior.getHigh() - prior.getLow() ) ) ));
        support3    = Double.valueOf(df.format( pivotPoint - ( 1.0   * ( prior.getHigh() - prior.getLow() ) ) ));

        resistance1 = Double.valueOf(df.format( pivotPoint + ( 0.382 * ( prior.getHigh() - prior.getLow() ) ) ));
        resistance2 = Double.valueOf(df.format( pivotPoint + ( 0.618 * ( prior.getHigh() - prior.getLow() ) ) ));
        resistance3 = Double.valueOf(df.format( pivotPoint + ( 1.0   * ( prior.getHigh() - prior.getLow() ) ) ));

        point.setSupport1(support1);
        point.setSupport2(support2);
        point.setSupport3(support3);

        point.setResistance1(resistance1);
        point.setResistance2(resistance2);
        point.setResistance3(resistance3);

    }

    protected void demark(Pp point, Quote prior) {

        double pivotPoint;

        double support1;
        double resistance1;

        double support2;
        double resistance2;

        double support3;
        double resistance3;

        DecimalFormat   df  = new DecimalFormat("#.##");
        double          x   = 0.0;

        if ( prior.getClose() < prior.getOpen() ) {
            x = (2.0 * prior.getLow()) + prior.getClose() + prior.getHigh();
        } else if ( prior.getClose() > prior.getOpen() ) {
            x = (2.0 * prior.getHigh()) + prior.getClose() + prior.getLow();
        } else {
            x = (2.0 * prior.getClose()) + prior.getLow() + prior.getHigh();
        }

        pivotPoint  = Double.valueOf(df.format(x / 4.0 ));

        support1    = Double.valueOf( df.format( (x / 2.0 ) - prior.getHigh() ));
        support2    = 0.0;
        support3    = 0.0;

        resistance1 = Double.valueOf( df.format( (x / 2.0 ) - prior.getLow() ));
        resistance2 = 0.0;
        resistance3 = 0.0;

        point.setSupport1(support1);
        point.setSupport2(support2);
        point.setSupport3(support3);

        point.setResistance1(resistance1);
        point.setResistance2(resistance2);
        point.setResistance3(resistance3);

    }

    protected void standard(Pp point, Quote prior) {

        double pivotPoint;

        double support1;
        double resistance1;

        double support2;
        double resistance2;

        double support3;
        double resistance3;

        DecimalFormat df      = new DecimalFormat("#.##");

        pivotPoint  = Double.valueOf(df.format( (prior.getHigh() + prior.getLow() + prior.getClose())/3.0 ) );

        support1    = Double.valueOf(df.format( (pivotPoint * 2.0) - prior.getHigh() ) );
        support2    = Double.valueOf(df.format( pivotPoint - ( prior.getHigh() - prior.getLow() ) ) );
        support3    = Double.valueOf(df.format( pivotPoint - 2 * ( ( prior.getHigh() - prior.getLow() ) ) ) );

        resistance1 = Double.valueOf(df.format( (pivotPoint * 2.0) - prior.getLow() ) );
        resistance2 = Double.valueOf(df.format( pivotPoint + ( prior.getHigh() - prior.getLow() ) ) );
        resistance3 = Double.valueOf(df.format( pivotPoint + 2 * ( ( prior.getHigh() - prior.getLow() ) ) ) );

        point.setPivotPoint(pivotPoint);

        point.setSupport1(support1);
        point.setSupport2(support2);
        point.setSupport3(support3);

        point.setResistance1(resistance1);
        point.setResistance2(resistance2);
        point.setResistance3(resistance3);

    }


    //
    // Data is in descending order by date
    public List<Pp> generatePivotPoints(List<Quote> data) {

        int numberOfQuotes = data.size();

        // allocate space for object list
        List<Pp> list = new ArrayList<>(numberOfQuotes - 1);

        int end = numberOfQuotes - 1;

        // Create the Pp list
        for ( int i=0; i<numberOfQuotes - 1; i++ ) {
            Pp pp = new Pp(data.get(i));
            standard(pp, data.get(i+1));
            list.add(pp);
        }

        // last entry defaults to zero
        Pp pp = new Pp(data.get(end));
        list.add(pp);

        return list;
    }

}
