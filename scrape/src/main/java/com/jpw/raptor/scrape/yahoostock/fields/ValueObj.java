package com.jpw.raptor.scrape.yahoostock.fields;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by john on 10/20/18.
 */
@Getter
@Setter
public class ValueObj {

    private String raw;
    private String fmt;
    private String longFmt;

    // Constructor
    public ValueObj() {
        raw = null;
        fmt = null;
        longFmt = null;
    }

    public String getString() {
        return raw;
    }

    public double getDouble() {

        if ( raw != null ) {
            double result = Double.parseDouble(raw);
            return Math.round(result*100.0)/100.0;
        } else {
            return 0.0;
        }
    }

    public long getLong() {
        if ( raw != null ) {
            return Long.parseLong(raw);
        } else {
            return 0l;
        }
    }
}
