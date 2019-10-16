package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Keltner extends Quote {

    private double  upper;
    private double  middle;
    private double  lower;
    private double  trueRange;
    private double  avgTrueRange;

    protected void init() {
        // Provide default values
        upper       = 0.0;
        middle      = 0.0;
        lower       = 0.0;
        trueRange   = 0.0;
        avgTrueRange   = 0.0;
    }

    // Constructor
    public Keltner () {
        init();
    }

    public Keltner(Quote v) {
        init();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setClose(v.getClose());
        setMiddle(v.getClose());
    }
}
