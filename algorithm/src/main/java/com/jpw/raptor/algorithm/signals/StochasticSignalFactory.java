package com.jpw.raptor.algorithm.signals;

import com.jpw.raptor.model.Stochastic;
import com.jpw.raptor.model.StochasticSignal;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by john on 6/20/18.
 */
public class StochasticSignalFactory {

    public StochasticSignalFactory() {}

    public int getPositiveDays(List<Stochastic> list, int offset) {
        int     result  = 1;
        boolean cont    = true;
        int     i       = offset + 1;

        while ( i<list.size() && cont ) {
            if ( list.get(i).getKSlow() >= list.get(i).getDSlow() ) {
                result++;
                i++;
            } else {
                cont = false;
            }
        }

        return result;
    }


    public int getNegativeDays(List<Stochastic> list, int offset) {
        int     result  = 1;
        boolean cont    = true;
        int     i       = offset + 1;

        while ( i<list.size() && cont ) {
            if ( list.get(i).getKSlow() < list.get(i).getDSlow() ) {
                result++;
                i++;
            } else {
                cont = false;
            }
        }

        return result;
    }


    public StochasticSignal getSignal(List<Stochastic> list, int offset ) {

        boolean             textCreated      = false;
        StringBuilder       sb               = new StringBuilder();
        DecimalFormat       strengthFormat   = new DecimalFormat("000");
        DecimalFormat       divergenceFormat = new DecimalFormat("##.#");

        Stochastic          current          = list.get(offset);
        Stochastic          prior            = list.get(offset+1);
        StochasticSignal    result           = new StochasticSignal();

        // Strength
        result.setStrength(strengthFormat.format(current.getDSlow()));

        // Trend
        if ( current.getKSlow() >= current.getDSlow() ) {
            if (prior.getKSlow() < prior.getDSlow()){
                //sb.append("Rising crossover");
                //sb.append("Rising");
                sb.append("K above");
            } else {
                //sb.append("Rising");
                sb.append("K above");
            }
            result.setDays(Integer.toString(getPositiveDays(list, offset)));
        } else {
            // current.getKSlow() < current.getDSlow()
            if ( prior.getKSlow() >= prior.getDSlow()){
                //sb.append("Declining crossover");
                //sb.append("Declining");
                sb.append("K below");
            } else {
                //sb.append("Declining");
                sb.append("K below");
            }
            result.setDays(Integer.toString(getNegativeDays(list, offset)));
        }

        result.setTrend(sb.toString());

        // divergence
        // just for testing
        result.setDivergence(divergenceFormat.format( Math.abs(current.getDSlow() - current.getKSlow()) ));

        // summary
        // TBD

        return result;
    }
}
