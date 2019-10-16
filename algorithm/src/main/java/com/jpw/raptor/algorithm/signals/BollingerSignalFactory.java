package com.jpw.raptor.algorithm.signals;

import com.jpw.raptor.model.Bollinger;
import com.jpw.raptor.model.BollingerSignal;
import com.jpw.raptor.model.Stochastic;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by john on 8/8/18.
 */
public class BollingerSignalFactory {

    public static final int SIX_MONTHS = 126;

    public double findLowWidth (List<Bollinger> list, int offset) {

        double lowWidth = 100.0;
        int     maxI    = offset + SIX_MONTHS;

        for ( int i=offset; i<maxI; i++ ) {
            if ( list.get(i).getWidth() <= lowWidth ) {
                lowWidth = list.get(i).getWidth();
            }
        }

        return lowWidth;
    }


    public BollingerSignal getSignal(List<Bollinger> list, int offset) {

        BollingerSignal result = new BollingerSignal();
        DecimalFormat   format = new DecimalFormat("###.#");
        DecimalFormat   width  = new DecimalFormat("#.###");

        result.setUpper(format.format( list.get(offset).getUpper() ));
        result.setMiddle(format.format( list.get(offset).getMiddle() ));
        result.setLower(format.format( list.get(offset).getLower() ));

        result.setWidth(width.format( list.get(offset).getWidth() ));
        result.setPercentB(format.format( list.get(offset).getPercentB() ));

        if ( list.size() >= offset + SIX_MONTHS ) {

            if ( list.get(offset).getWidth() <= findLowWidth (list, offset) ) {
                result.setSqueeze("yes");
            }
        }

        return result;
    }
}
