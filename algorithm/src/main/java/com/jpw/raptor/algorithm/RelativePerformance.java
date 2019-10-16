package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.RelativePerformanceModel;
import com.jpw.raptor.model.Rp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Ported to new design
 *
 * Relative performance is a ratio which increases when the equity is out performing the reference
 * and decreases when the reference is out performing the equity
 *
 */
public class RelativePerformance {

    // Quotes are in descending order by date
    public List<Rp> computeRelativePerformance(
            List<Quote> refQuotes, List<Quote> equityQuotes) {

        // Allocate space for the relative performance list
        int       max    = Math.min(refQuotes.size(), equityQuotes.size());
        List<Rp>  result = new ArrayList<>(max);

        // Used to generate date key
        SimpleDateFormat sdf    = new SimpleDateFormat("yyyy-MM-dd");

        // used to format relative performance value
        DecimalFormat    df     = new DecimalFormat("#.###");

        // Create a sorted map to hold the model data
        // data will be stored in ascending order by date string
        TreeMap<String,RelativePerformanceModel> map = new TreeMap<>();

        //
        // Populate the map with reference data defaulting the equity value to -9999
        for ( Quote refVal : refQuotes ) {
            RelativePerformanceModel model = new RelativePerformanceModel();
            model.setDate(refVal.getDate());
            model.setDatestr(sdf.format(refVal.getDate()));
            model.setRefSymbol(refVal.getSymbol());
            model.setRef(refVal.getClose());
            model.setEquity(-9999.0);
            map.put(model.getDatestr(), model);
        }

        //
        // update the map with equity data
        for ( Quote equityVal : equityQuotes ) {
            RelativePerformanceModel model = map.get(sdf.format(equityVal.getDate()));

            // if an entry exist then update it
            if (model != null) {
                model.setEquitySymbol(equityVal.getSymbol());
                model.setEquity(equityVal.getClose());
                map.put(model.getDatestr(), model);
            }
        }

        //
        // Populate a list with entries that have both ref and equity values
        // traverse the map in sorted order which is ascending order
        for (Map.Entry<String, RelativePerformanceModel> entrySetVal : map.entrySet()) {
            RelativePerformanceModel entry = entrySetVal.getValue();
            if ( entry.getEquity() > -9999.0 ) {
                Rp rec = new Rp();
                rec.setSymbol(entry.getEquitySymbol() + ":" + entry.getRefSymbol());
                rec.setDate(entry.getDate());
                rec.setVal( Double.parseDouble( df.format(entry.getEquity() / entry.getRef() ) ) );
                result.add(rec);
            }
        }

        Collections.reverse(result);

        // Reverse the list from ascending to descending order
        return result;
    }

}
