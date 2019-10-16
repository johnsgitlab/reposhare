package com.jpw.raptor.model;

import lombok.*;
import java.util.Date;

@Getter
@Setter
public class AsburyListModel {

    private String  symbol;
    private Date    date;
    private String  datestr;
    private double  val;
    private double  sma;

    protected void init() {
        // Provide default values
        symbol  = null;
        date    = null;
        datestr = null;
        val     = 0.0;
        sma     = 0.0;
    }

    // Constructor
    public AsburyListModel () {
        init();
    }
}
