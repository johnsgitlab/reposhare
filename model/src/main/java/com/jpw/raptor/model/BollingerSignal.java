package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 8/8/18.
 */
@Getter
@Setter
public class BollingerSignal {

    private String  upper;
    private String  middle;
    private String  lower;
    private String  width;
    private String  percentB;
    private String  squeeze;

    private void init() {
        upper     = new String("");
        middle    = new String("");
        lower     = new String("");
        width     = new String("");
        percentB  = new String("");
        squeeze   = new String("");
    }

    public BollingerSignal () { init(); }
}
