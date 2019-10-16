package com.jpw.raptor.model;

import lombok.*;
import java.util.Date;

@Getter
@Setter
public class AsburySummaryModel {

    private String  spyPerf;
    private String  spyRoc;
    private String  spyJnk;
    private String  spyObv;
    private String  spread;
    private String  advdecl;
    private String  vixPerf;

    protected void init() {
        // Provide default values
        spyPerf = null;
        spyRoc  = null;
        spyJnk  = null;
        spyObv  = null;
        spread  = null;
        advdecl = null;
        vixPerf = null;
    }

    // Constructor
    public AsburySummaryModel () {
        init();
    }
}
