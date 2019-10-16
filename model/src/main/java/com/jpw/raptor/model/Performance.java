package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * Created by john on 3/28/18.
 */
@Getter
@Setter
public class Performance {

    private Date       date;

    private double     ytd;
    private double     oneDay;          // 1
    private double     oneWeek;         // 5
    private double     twoWeeks;        // 10
    private double     threeWeeks;      // 15
    private double     fourWeeks;       // 20
    private double     twoMonths;       // 41
    private double     threeMonths;     // 62
    private double     fourMonths;      // 83
    private double     fiveMonths;      // 104
    private double     sixMonths;       // 125
    private double     oneYear;         // 250
    private double     threeYears;      // 750

    protected void init() {
        // Provide default values
        date        = null;

        ytd         = -9999.0;
        oneDay      = -9999.0;
        oneWeek     = -9999.0;
        twoWeeks    = -9999.0;
        threeWeeks  = -9999.0;
        fourWeeks   = -9999.0;
        twoMonths   = -9999.0;
        threeMonths = -9999.0;
        fourMonths  = -9999.0;
        fiveMonths  = -9999.0;
        sixMonths   = -9999.0;
        oneYear     = -9999.0;
        threeYears  = -9999.0;
    }

    // Constructor
    public Performance () {
        init();
    }

    // 260 week days - 10 hollidays = 250 trading days in a year
    // about 20.8 days a month
}
