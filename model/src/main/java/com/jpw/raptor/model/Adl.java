package com.jpw.raptor.model;


import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Adl {

    private String  symbol;
    private Date    date;
    private String  datestr;
    private double  close;
    private long    val;
    private long    sma;


    protected void init() {
        // Provide default values
        symbol  = null;
        date    = null;
        datestr = null;
        close   = 0.0;
        val     = 0;
        sma     = 0;
    }

    // Constructor
    public Adl () {
        init();
    }

    public Adl(Quote v) {
        init();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setClose(v.getClose());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDatestr(sdf.format(v.getDate()));
    }
}