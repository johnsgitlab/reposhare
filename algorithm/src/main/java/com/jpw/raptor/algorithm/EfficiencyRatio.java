package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Ported to new design 5/10/2019
 *
 * The efficiency ratio measures price noise which is the erratic movement
 * that surrounds the underlying direction of prices at any given time
 *
 * A ratio near 0 indicates only noise
 * A ratio near 1 shows a strong trend with little noise
 */
public class EfficiencyRatio {

    // Quotes are in descending order by date
    public double generateValue(List<Quote> data, int offset, int periods ) {

        // compute loop end
        if ( (offset + periods) > (data.size() - 1) ) {
            return 0.0;
        }

        // compute net price change
        Quote   first       = data.get(offset);
        Quote   last        = data.get( (offset + periods) );
        double  changeNet   = Math.abs(first.getClose() - last.getClose());

        //sum individual price changes over the period
        double  changeSum   = 0.0;
        for ( int i = offset; i < (offset+periods); i++) {
            Quote   current = data.get(i);
            Quote   prior   = data.get(i + 1);
            changeSum      += Math.abs(current.getClose() - prior.getClose());
        }

        DecimalFormat df = new DecimalFormat("#.##");
        if ( changeSum > 0.0 )
            return Double.valueOf(df.format(changeNet / changeSum));
        else
            return 0.0;

    }
}
