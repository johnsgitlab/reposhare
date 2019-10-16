package com.jpw.raptor.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DualMomentumModel {

    private String 	symbol;
    private String 	name;
    private double 	oneMonth;
    private double 	twoMonths;
    private double 	threeMonths;
    private double 	sixMonths;
    private double 	oneYear;

    // Constructor
    public DualMomentumModel (String aSymbol, String aName, double aOneMonth, double aTwoMonths,
                            double aThreeMonths,  double aSixMonths, double aOneYear) {
        symbol            = aSymbol;
        name              = aName;
        oneMonth          = aOneMonth;
        twoMonths         = aTwoMonths;
        threeMonths       = aThreeMonths;
        sixMonths         = aSixMonths;
        oneYear           = aOneYear;
    }

    // Constructor
    public DualMomentumModel() {
        init();
    }

    protected void init () {
        symbol      = null;
        name        = null;
        oneMonth    = 0.0;
        twoMonths   = 0.0;
        threeMonths = 0.0;
        sixMonths   = 0.0;
        oneYear     = 0.0;
    }

}
