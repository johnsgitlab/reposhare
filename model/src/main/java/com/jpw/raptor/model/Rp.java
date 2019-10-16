package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Rp {

    private String  symbol;
    private Date    date;
    private double  close;
    private double  val;

    protected void init() {
        // Provide default values
        symbol      = null;
        date        = null;
        close       = 0.0;
        val         = 0.0;
    }

    // Constructor
    public Rp () {
        init();
    }
}
