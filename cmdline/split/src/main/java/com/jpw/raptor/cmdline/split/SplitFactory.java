package com.jpw.raptor.cmdline.split;


import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.jpw.raptor.model.Quote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 3/30/18.
 */
public class SplitFactory {

    public void adjust(List<Quote> recs, double split) {

        // Adjust the values for each quote record
        for (Quote rec : recs) {
            rec.setOpen( rec.getOpen() / split );
            rec.setHigh( rec.getHigh() / split );
            rec.setLow( rec.getLow() / split );
            rec.setClose( rec.getClose() / split );
            double temp = (double) rec.getVolume() * split;
            rec.setVolume( (long) temp );
            //System.out.println(rec.getDate() + " " + rec.getOpen() + " " + rec.getHigh() +
            //            " " + rec.getLow() + " " + rec.getClose() + " " + rec.getVolume() );
        }
    }
}
