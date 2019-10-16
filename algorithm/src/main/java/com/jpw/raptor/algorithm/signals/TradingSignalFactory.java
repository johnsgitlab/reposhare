package com.jpw.raptor.algorithm.signals;

import com.jpw.raptor.model.*;

import java.util.List;

/**
 * Created by john on 7/5/18.
 */
public class TradingSignalFactory {


    public TradeSignal getSignal(List<AveragesModel> maList, List<Adx> adxList, List<Stochastic> stoList, int offset ) {

        TradeSignal result = new TradeSignal();

        // moving average entry signal
        // Current day above
        if ( maList.get(offset).getClose() >= maList.get(offset).getSimple50() ) {

            // is this a crossover
            if ( maList.get(offset+1).getClose() < maList.get(offset+1).getSimple50() ) {
                result.setMaEntry1("cross");
            } else {
                result.setMaEntry1("ok");
            }
        } else {
            result.setMaEntry1("no");
        }

        // stocastic entry signal
        if ( stoList.get(offset).getKSlow() >= stoList.get(offset).getDSlow() ) {
            // k is above D
            if ( stoList.get(offset).getDSlow() < 40.0 ) {
                result.setStoEntry1("cross");
            } else {
                result.setStoEntry1("Ok");
            }
        } else {
            result.setStoEntry1("no");
        }

        // atr exit
        double trigger = maList.get(offset+1).getHigh() - (adxList.get(offset+1).getAverageTrueRange() * 5.0);
        if ( maList.get(offset).getClose() < trigger ) {
            result.setAtrExit1("exit");
        }

        return result;
    }
}
