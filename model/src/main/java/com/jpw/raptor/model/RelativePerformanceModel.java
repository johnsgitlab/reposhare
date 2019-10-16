package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 7/28/18.
 */
@Getter
@Setter
public class RelativePerformanceModel {

    private String  refSymbol;
    private String  equitySymbol;
    private Date    date;
    private String  datestr;
    private double  ref;
    private double  equity;

    protected void init() {
        // Provide default values
        refSymbol    = null;
        equitySymbol = null;
        date         = null;
        datestr      = null;
        ref          = 0.0;
        equity       = 0.0;
    }

    // Constructor
    public RelativePerformanceModel () {
        init();
    }
}
