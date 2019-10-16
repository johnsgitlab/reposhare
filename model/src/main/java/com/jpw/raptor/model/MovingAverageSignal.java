package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by john on 7/4/18.
 */
public class MovingAverageSignal {

    private String signal1;
    private String signal2;

    private void init() {
        signal1 = new String("");
        signal2 = new String("");
    }

    public MovingAverageSignal() {
        init();
    }
}
