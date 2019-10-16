package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 2/19/18.
 */
@Getter
@Setter
public class Stochastic extends Quote {

    private double 	        kSlow;  // 3 day sma of kfast
    private double 	        dSlow;  // 3 day sma of kslow
    private double 	        kFast;  // ((close - lowest low)/(highest high - lowest low)) * 100.0
    private double 	        dFast;  // 3 day sma of kfast

    protected void init() {
        // Provide default values
        kSlow = 0.0;
        dSlow = 0.0;
        kFast = 0.0;
        dFast = 0.0;
    }

    // Constructor
    public Stochastic () {
        super();
        init();
    }

    public Stochastic(Quote v) {
        super();
        init();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setOpen(v.getOpen());
        setHigh(v.getHigh());
        setLow(v.getLow());
        setClose(v.getClose());
        setVolume(v.getVolume());
    }

}
