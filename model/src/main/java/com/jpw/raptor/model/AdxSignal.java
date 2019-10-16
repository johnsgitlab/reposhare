package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by john on 6/20/18.
 */
@Getter
@Setter
public class AdxSignal {

    private String summary;
    private String signal;
    private String trend;
    private String direction;
    private String days;

    private void init() {
        summary     = new String("");
        signal      = new String("");
        trend       = new String("");
        direction   = new String("");
        days        = new String("");
    }

    public AdxSignal () { init(); }


}
