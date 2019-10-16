package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 9/8/18.
 */
@Getter
@Setter
public class Roc {

    private String  symbol;
    private Date    date;
    private String  datestr;
    private double  close;
    private double  value;


    protected void init() {
        // Provide default values
        symbol  = null;
        date    = null;
        datestr = null;
        close   = 0.0;
        value   = 0.0;
    }

    // Constructor
    public Roc () {
        init();
    }

    public Roc(Quote v) {
        init();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setClose(v.getClose());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDatestr(sdf.format(v.getDate()));
    }

}
