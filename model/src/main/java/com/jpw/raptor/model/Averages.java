package com.jpw.raptor.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 4/8/18.
 */
public class Averages {

    private String 	symbol;
    private Date    date;
    private double 	close;
    private double 	kaufman;
    private double 	fractal;
    private double 	simple5;
    private double 	simple10;
    private double 	simple20;
    private double 	simple50;
    private double 	simple100;
    private double 	simple200;
    private int     volume;

    protected void init() {
        // Provide default values
        symbol     = null;
        date       = null;
        close      = 0.0;
        kaufman    = 0.0;
        fractal    = 0.0;
        simple5    = 0.0;
        simple10   = 0.0;
        simple20   = 0.0;
        simple50   = 0.0;
        simple100  = 0.0;
        simple200  = 0.0;
        volume     = 0;
    }

    // Constructor
    public Averages () {
        init();
    }

    // Constructor
    public Averages (String aSymbol, Date aDate, double aClose, double aKaufman,
                        double afractal, double aSimple5, double aSimple10, double aSimple20, double aSimple50,
                        double aSimple100, double aSimple200, int aVolume ) {
        init();
        setSymbol(aSymbol);
        setDate(aDate);
        setClose(aClose);
        setKaufman(aKaufman);
        setFractal(afractal);
        setSimple5(aSimple5);
        setSimple10(aSimple10);
        setSimple20(aSimple20);
        setSimple50(aSimple50);
        setSimple100(aSimple100);
        setSimple200(aSimple200);
        setVolume(aVolume);
    }


    public String 	getSymbol() 				{ return symbol; }
    public void 	setSymbol(String v) 		{ symbol = v; }

    public Date 	getDate() 					{ return date; }
    public void 	setDate(Date v) 			{ date = v; }

    public double 	getClose() 					{ return close; }
    public void 	setClose(double v) 			{ close = v; }

    public double 	getKaufman() 				{ return kaufman; }
    public void 	setKaufman(double v) 		{ kaufman = v; }

    public double 	getFractal() 				{ return fractal; }
    public void 	setFractal(double v) 		{ fractal = v; }

    public double 	getSimple5() 				{ return simple5; }
    public void 	setSimple5(double v) 		{ simple5 = v; }

    public double 	getSimple10() 				{ return simple10; }
    public void 	setSimple10(double v) 		{ simple10 = v; }

    public double 	getSimple20() 				{ return simple20; }
    public void 	setSimple20(double v) 		{ simple20 = v; }

    public double 	getSimple50() 				{ return simple50; }
    public void 	setSimple50(double v) 		{ simple50 = v; }

    public double 	getSimple100() 				{ return simple100; }
    public void 	setSimple100(double v) 		{ simple100 = v; }

    public double 	getSimple200() 				{ return simple200; }
    public void 	setSimple200(double v) 		{ simple200 = v; }

    public int 	    getVolume() 				{ return volume; }
    public void 	setVolume(int v) 		    { volume = v; }

}
