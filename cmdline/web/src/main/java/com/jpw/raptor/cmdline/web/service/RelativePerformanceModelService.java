package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.RelativePerformanceModel;
import com.jpw.raptor.model.Rp;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RelativePerformanceModelService {


    // Quotes are in descending order by date
    private List<Rp> computeRelativePerformance(List<Quote> quotes) {

        List<Rp> result = new ArrayList<>(quotes.size());

        // Performance is measured from the last entry in the list
        double        base = quotes.get(quotes.size() - 1).getClose();
        DecimalFormat df   = new DecimalFormat("#.#");

        // populate the performance list
        for ( Quote quote : quotes ) {
            Rp relative = new Rp();
            relative.setSymbol(quote.getSymbol());
            relative.setDate(quote.getDate());
            relative.setClose(quote.getClose());

            double val = ((quote.getClose() - base) / base) * 100.0;
            relative.setVal( Double.valueOf(df.format(val)) );
            result.add(relative);
        }

        return result;
    }


    public List<RelativePerformanceModel> computeRelativePerformanceModel(
            List<Quote> refQuotes, List<Quote> equityQuotes) {

        List<Rp> ref       = computeRelativePerformance(refQuotes);
        List<Rp> equity    = computeRelativePerformance(equityQuotes);

        // Allocate space for the relative performance list
        int                             max    = Math.min(ref.size(), equity.size());
        List<RelativePerformanceModel>  result = new ArrayList<>(max);

        // Used to generate date key
        SimpleDateFormat                sdf    = new SimpleDateFormat("yyyy-MM-dd");

        // Create a sorted map to hold the model data
        // data will be stored in ascending order by date string
        TreeMap<String,RelativePerformanceModel> map = new TreeMap<>();

        //
        // Populate the map with reference data defaulting the equity value to -9999
        for ( Rp refVal : ref ) {
            RelativePerformanceModel model = new RelativePerformanceModel();
            model.setDate(refVal.getDate());
            model.setDatestr(sdf.format(refVal.getDate()));
            model.setRefSymbol(refVal.getSymbol());
            model.setRef(refVal.getVal());
            model.setEquity(-9999.0);
            map.put(model.getDatestr(), model);
        }

        //
        // update the map with equity data
        for ( Rp equityVal : equity ) {
            RelativePerformanceModel model = map.get(sdf.format(equityVal.getDate()));

            // if an entry exist then update it
            if (model != null) {
                model.setEquitySymbol(equityVal.getSymbol());
                model.setEquity(equityVal.getVal());
                map.put(model.getDatestr(), model);
            }
        }

        //
        // Populate a list with entries that have both ref and equity values
        // traverse the map in sorted order which is ascending order
        for (Map.Entry<String, RelativePerformanceModel> entry : map.entrySet()) {
            if ( entry.getValue().getEquity() > -9999.0 ) {
                result.add(entry.getValue());
            }
        }

        return result;
    }

}
