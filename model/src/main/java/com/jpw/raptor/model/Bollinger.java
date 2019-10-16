package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 7/13/18.
 */
@Getter
@Setter
public class Bollinger extends Quote {

    private double  stdDeviation;

    private double  upper;
    private double  middle;
    private double  lower;
    private double  width;
    private double  percentB;


    protected void init() {
        // Provide default values
        upper           = 0.0;
        middle          = 0.0;
        lower           = 0.0;
        width           = 0.0;
        stdDeviation    = 0.0;
        percentB        = 0.0;
    }

    // Constructor
    public Bollinger () {
        init();
    }

    public Bollinger(Quote v) {
        init();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setClose(v.getClose());
    }

}
