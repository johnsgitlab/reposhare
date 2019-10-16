package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Adl;
import com.jpw.raptor.model.Obv;
import com.jpw.raptor.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class AdvanceDeclineLine {

    //
    // Data is in descending order by date
    private void populateMovingAvg(List<Adl> data, int n ) {

        SimpleMovingAverage smaFactory = new SimpleMovingAverage();

        for ( int i=0; i<data.size(); i++ ) {
            long sma = smaFactory.smaAdl(data, i, n);
            data.get(i).setSma(sma);
        }

    }


    public List<Adl> generateAdl(List<Quote> data, int n) {

        // allocate space for object list
        List<Adl> list = new ArrayList<>(data.size());

        // Create the adl list
        for ( int i=0; i<data.size(); i++) {
            Adl adl = new Adl(data.get(i));
            list.add(adl);
        }

        // Since dates are descending start at the end of the array
        // and process to the front
        int start = data.size() - 1;

        long adlVal = 0l;

        // create adl value for each quote
        for ( int i=start; i>=0; i-- ) {

            Adl rec = list.get(i);
            adlVal += (long) rec.getClose();
            rec.setVal(adlVal);

        }

        // Generate the moving values
        populateMovingAvg(list, n);

        return list;
    }
}
