package com.jpw.raptor.model;

import java.text.DecimalFormat;


/**
 * Created by john on 6/9/18.
 */
public class Adx extends Quote {

    private double 	        dmPlus;
    private double 	        averageDmPlus;

    private double 	        dmMinus;
    private double 	        averageDmMinus;

    private double 	        trueRange;
    private double 	        averageTrueRange;

    private double 	        diPlus;
    private double 	        diMinus;

    private double 	        dx;
    private double 	        averageDx;



    protected void init() {
        // Provide default values
        dmPlus              = 0.0;
        averageDmPlus       = 0.0;

        dmMinus             = 0.0;
        averageDmMinus      = 0.0;

        diPlus              = 0.0;
        diMinus             = 0.0;

        trueRange           = 0.0;
        averageTrueRange    = 0.0;

        dx                  = 0.0;
        averageDx           = 0.0;

    }

    // Constructor
    public Adx () {
        super();
        init();
    }

    public Adx(Quote v) {
        super();
        init();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setOpen(v.getOpen());
        setHigh(v.getHigh());
        setLow(v.getLow());
        setClose(v.getClose());
        setVolume(v.getVolume());
    }

    public void print() {
        DecimalFormat df = new DecimalFormat("#.##");

        double diDiff  = Double.valueOf(Math.abs( diPlus - diMinus ));
        double diSum   = Double.valueOf(diPlus + diMinus);

        System.out.println(

                //"hi   " + getHigh()    + "  " +
                //"low  " + getLow()     + "  " +
                //"cls  " + getClose()   + "  " +

                "dmp  " + dmPlus    + "  " +
                "dmm  " + dmMinus   + "  " +
                "tr   " + trueRange + "  " +

                "atr  " + averageTrueRange + "  " +
                "admp " + averageDmPlus    + "  " +
                "admm " + averageDmMinus   + "  " +

                "dip  " + diPlus           + "  " +
                "dim  " + diMinus          + "  " +

                //"did  " + diDiff           + "  " +
                //"dis  " + diSum            + "  " +

                "dx   " + dx               + "  " +
                "adx  " + averageDx
        );
    }

    public double 	   getDmPlus() 			                { return dmPlus; }
    public void 	   setDmPlus(double v){
        DecimalFormat df = new DecimalFormat("#.##");
        dmPlus = Double.valueOf(df.format(v));
    }

    public double 	   getAverageDmPlus() 			        { return averageDmPlus; }
    public void 	   setAverageDmPlus(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        averageDmPlus = Double.valueOf(df.format(v));
    }


    public double 	   getDmMinus() 		                { return dmMinus; }
    public void 	   setDmMinus(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        dmMinus = Double.valueOf(df.format(v));
    }

    public double 	   getAverageDmMinus() 		            { return averageDmMinus; }
    public void 	   setAverageDmMinus(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        averageDmMinus = Double.valueOf(df.format(v));
    }


    public double 	   getDiPlus() 			                { return diPlus; }
    public void 	   setDiPlus(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        diPlus = Double.valueOf(df.format(v));
    }

    public double 	   getDiMinus() 		                { return diMinus; }
    public void 	   setDiMinus(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        diMinus = Double.valueOf(df.format(v));
    }


    public double 	   getTrueRange() 			            { return trueRange; }
    public void 	   setTrueRange(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        trueRange = Double.valueOf(df.format(v));
    }

    public double 	   getAverageTrueRange() 			    { return averageTrueRange; }
    public void 	   setAverageTrueRange(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        averageTrueRange = Double.valueOf(df.format(v));
    }


    public double 	   getDx() 			                    { return dx; }
    public void 	   setDx(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        dx = Double.valueOf(df.format(v));
    }

    public double 	   getAverageDx()			            { return averageDx; }
    public void 	   setAverageDx(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        averageDx = Double.valueOf(df.format(v));
    }
}
