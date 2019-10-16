package com.jpw.raptor.scrape.marketwatch;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Fund;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by john on 3/24/18.
 */
@Getter
@Setter
public class MarketWatchPageContents {

    private int     totalReturn;
    private int     consistentReturn;
    private int     preservation;
    private int     taxEfficiency;
    private int     expense;
    private boolean found;
    private String  exchange;

    public MarketWatchPageContents() {
        totalReturn         = 0;
        consistentReturn    = 0;
        preservation        = 0;
        taxEfficiency       = 0;
        expense             = 0;
        found               = false;
        exchange            = null;
    }


    public void updateEtfFromMarketWatch(Etf rec) {

        // Market Watch scrape
        rec.setLipperTotal(getTotalReturn());
        rec.setLipperConsistent(getConsistentReturn());
        rec.setLipperPreservation(getPreservation());
        rec.setLipperTax(getTaxEfficiency());
        rec.setLipperExpense(getExpense());
        rec.setExchange(getExchange());
    }


    public void updateFundFromMarketWatch(Fund rec) {

        // Market Watch scrape
        rec.setLipperTotal(getTotalReturn());
        rec.setLipperConsistent(getConsistentReturn());
        rec.setLipperPreservation(getPreservation());
        rec.setLipperTax(getTaxEfficiency());
        rec.setLipperExpense(getExpense());
        rec.setExchange(getExchange());
    }
}
