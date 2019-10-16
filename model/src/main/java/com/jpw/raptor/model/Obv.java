package com.jpw.raptor.model;


import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 7/14/18.
 */
@Getter
@Setter
public class Obv extends Quote {

    public long    val;
    public long    sma;
    public String  datestr;


    protected void init() {
        // Provide default values
        val     = 0l;
        sma     = 0l;
        datestr = null;
    }

    // Constructor
    public Obv () {
        init();
    }

    public Obv(Quote v) {
        init();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setClose(v.getClose());
        setVolume(v.getVolume());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDatestr( sdf.format(v.getDate()) );
    }

}
