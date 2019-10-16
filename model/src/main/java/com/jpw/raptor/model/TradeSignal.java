package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by john on 7/5/18.
 */
@Getter
@Setter
public class TradeSignal {

    private String maEntry1;
    private String stoEntry1;
    private String atrExit1;


    private void init() {
        maEntry1 = new String("");
        stoEntry1 = new String("");
        atrExit1 = new String("");
    }

    public TradeSignal() {
        init();
    }
}
