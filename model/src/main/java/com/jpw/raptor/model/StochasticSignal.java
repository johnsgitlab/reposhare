package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by john on 6/20/18.
 */
@Getter
@Setter
public class StochasticSignal {

    private String summary;
    private String strength;        // %d value
    private String trend;           // %k to %d relationship
    private String days;            // length of current relationship
    private String divergence;      // Difference between %k and %d



    private void init() {
        summary     = new String("");
        strength    = new String("");
        trend       = new String("");
        days        = new String("");
        divergence  = new String("");

    }

    public StochasticSignal() {
        init();
    }
}
