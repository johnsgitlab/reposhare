package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 9/17/18.
 */
@Getter
@Setter
public class Macd extends Quote {

    private double fastEma;
    private double slowEma;
    private double macd;
    private double signal;
    private double histogram;

    protected void init() {
        // Provide default values
        fastEma     = 0.0;
        slowEma     = 0.0;
        macd        = 0.0;
        signal      = 0.0;
        histogram   = 0.0;
    }

    // Constructor
    public Macd () {
        super();
        init();
    }

    public Macd(Quote v) {
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
