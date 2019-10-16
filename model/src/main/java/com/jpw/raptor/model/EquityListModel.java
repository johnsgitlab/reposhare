package com.jpw.raptor.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EquityListModel {

    private String 	symbol;
    private String 	name;
    private Date    date;
    private double 	ytd;
    private double 	oneDay;
    private double 	oneWeek;
    private double 	twoWeeks;
    private double 	fourWeeks;
    private double 	threeMonths;
    private double 	oneYear;
    private double 	marketCap;

    // Constructor
    public EquityListModel (String aSymbol, String aName, Date aDate,
                      double aYtd, double aOneDay,    double aOneWeek,
                      double aTwoWeeks,  double aFourWeeks, double aThreeMonths,
                      double aOneYear,   double aMarketCap ) {
        symbol            = aSymbol;
        name              = aName;
        date              = aDate;
        ytd               = aYtd;
        oneDay            = aOneDay;
        oneWeek           = aOneWeek;
        twoWeeks          = aTwoWeeks;
        fourWeeks         = aFourWeeks;
        threeMonths       = aThreeMonths;
        oneYear           = aOneYear;
        marketCap         = aMarketCap;
    }

    // Constructor
    public EquityListModel() {
        init();
    }

    protected void init () {
        symbol      = null;
        name        = null;
        date        = null;
        ytd         = 0.0;
        oneDay      = 0.0;
        oneWeek     = 0.0;
        twoWeeks    = 0.0;
        fourWeeks   = 0.0;
        threeMonths = 0.0;
        oneYear     = 0.0;
        marketCap   = 0.0;
    }

}
