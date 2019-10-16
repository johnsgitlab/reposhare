package com.jpw.raptor.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 9/10/18.
 */
@Getter
@Setter
public class Mo {

    private String  symbol;
    private Date    date;
    private double  close;

    private double  value;
    private double  sma;


    protected void init() {
        // Provide default values
        symbol  = null;
        date    = null;
        close   = 0.0;
        value   = 0.0;
        sma      = 0.0;
    }

    // Constructor
    public Mo () {
        init();
    }

    public Mo(Quote v) {
        init();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setClose(v.getClose());
    }

}
