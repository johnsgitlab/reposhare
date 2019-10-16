package com.jpw.raptor.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 4/8/18.
 */
public class AveragesModel extends Quote {

    private String  datestr;
    private double 	kaufman;
    private double 	fractal;
    private double 	simple5;
    private double 	simple10;
    private double 	simple20;
    private double 	simple50;
    private double 	simple100;
    private double 	simple200;


    protected void init(double val) {
        // Provide default values
        datestr    = null;
        kaufman    = 0.0;
        fractal    = 0.0;
        simple5    = val;
        simple10   = val;
        simple20   = val;
        simple50   = val;
        simple100  = val;
        simple200  = val;
    }

    // Constructor
    public AveragesModel () {
        super();
        init(0.0);
    }

    public AveragesModel(Quote q) {
        super();
        init(q.getClose());
        setSymbol( q.getSymbol() );
        setDate( q.getDate() );
        setOpen( q.getOpen() );
        setHigh( q.getHigh() );
        setLow( q.getLow() );
        setClose( q.getClose() );
        setVolume( q.getVolume() );
        setDatestr( q.getDate() );
    }

    public String   getDatestr ()                  { return datestr; }
    public void     setDatestr (Date v) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        datestr              = sdf.format(v);
    }

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

}
