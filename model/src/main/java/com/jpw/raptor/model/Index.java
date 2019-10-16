package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by john on 4/3/17.
 */
@Data
public class Index {

    private String     symbol;
    private String     name;
    private Date       date;
    private Date       lastUpdate;
    private String     overview;
    private double     ytd;
    private double     oneDay;          // 1
    private double     oneWeek;         // 5
    private double     twoWeeks;        // 10
    private double     fourWeeks;       // 20
    private double     threeMonths;     // 62
    private double     oneYear;         // 250
    private double     threeYears;      // 750

    protected void init() {
        // Provide default values
        symbol      = null;
        name        = null;

        date        = null;
        lastUpdate  = null;

        ytd         = -9999.0;
        oneDay      = -9999.0;
        oneWeek     = -9999.0;
        twoWeeks    = -9999.0;
        fourWeeks   = -9999.0;
        threeMonths = -9999.0;
        oneYear     = -9999.0;
        threeYears  = -9999.0;

        overview    = null;
    }

    // Constructor
    public Index () {
        init();
    }

}
