package com.jpw.raptor.algorithm.signals;

import com.jpw.raptor.model.Adx;
import com.jpw.raptor.model.AdxSignal;
import com.jpw.raptor.model.Stochastic;
import com.jpw.raptor.model.StochasticSignal;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by john on 6/20/18.
 */
public class AdxSignalFactory {

    public AdxSignalFactory() {}

    public int getUpDays(List<Adx> list, int offset) {
        int     result  = 1;
        boolean cont    = true;
        int     i       = offset + 1;

        while ( i<list.size() && cont ) {
            if ( list.get(i).getDiPlus() >= list.get(i).getDiMinus() ) {
                result++;
                i++;
            } else {
                cont = false;
            }
        }

        return result;
    }


    public int getDownDays(List<Adx> list, int offset) {
        int     result  = 1;
        boolean cont    = true;
        int     i       = offset + 1;

        while ( i<list.size() && cont ) {
            if ( list.get(i).getDiPlus() < list.get(i).getDiMinus() ) {
                result++;
                i++;
            } else {
                cont = false;
            }
        }

        return result;
    }

    public AdxSignal getSignal(List<Adx> list, int offset ) {

        //boolean             textCreated      = false;
        //StringBuilder       sb               = new StringBuilder();
        //DecimalFormat       strengthFormat   = new DecimalFormat("000");
        //DecimalFormat       divergenceFormat = new DecimalFormat("##.#");

        Adx          current = list.get(offset);
        Adx          prior   = list.get(offset+1);
        AdxSignal    result  = new AdxSignal();

        // Strength
        if (current.getAverageDx() > 75.0 ) {
            result.setTrend("Extremeley Strong");
        } else if (current.getAverageDx() > 50.0  ) {
            result.setTrend("Very Strong");
        } else if (current.getAverageDx() > 25.0  ) {
            result.setTrend("Strong");
        } else if (current.getAverageDx() < 20.0 ) {
            result.setTrend("None");
        } else {
            result.setTrend("Unknown");
        }

        // Direction and Days
        if ( current.getDiPlus() >= current.getDiMinus() ) {
            if (prior.getDiPlus() < prior.getDiMinus()){
                //result.setDirection("Up with crossover")
                result.setDirection("Up");
            } else {
                result.setDirection("Up");
            }
            result.setDays(Integer.toString(getUpDays(list, offset)));
        } else {
            // current.getDiPlus() < current.getDiMinus()
            if (prior.getDiPlus() >= prior.getDiMinus()){
                //result.setDirection("Down with crossover")
                result.setDirection("Down");
            } else {
                result.setDirection("Down");
            }
            result.setDays(Integer.toString(getDownDays(list, offset)));
        }

        // Signal
        double n = 2.0;
        if ( current.getClose() > (prior.getClose() + (n*prior.getAverageTrueRange())) ) {
            result.setSignal("Buy");
        }

        if ( current.getClose() < (prior.getClose() - (n*prior.getAverageTrueRange())) ) {
            result.setSignal("Sell");
        }
        // summary
        // TBD

        return result;
    }
}
