package com.jpw.raptor.model;

import java.util.Date;

/**
 * Created by john on 2/19/18.
 */
public class Average {

    public enum AverageType {
        SMA,
        EMA
    }

    private String 	    symbol;
    private Date        date;
    private AverageType avgType;
    private int         days;
    private double 	    val;


    protected void init() {
        // Provide default values
        symbol     = null;
        date       = null;
        avgType    = null;
        days       = 0;
        val        = 0.0;
    }

    // Constructor
    public Average () {
        init();
    }

    // Constructor
    public Average (String aSymbol, Date aDate, AverageType aType, int aDay, double aVal ) {
        init();
        setSymbol(aSymbol);
        setDate(aDate);
        setAvgType(aType);
        setDays(aDay);
        setVal(aVal);
    }

    public String 	   getSymbol() 			     { return symbol; }
    public void 	   setSymbol(String v) 		 { symbol = v; }

    public Date 	   getDate() 				 { return date; }
    public void 	   setDate(Date v) 			 { date = v; }

    public AverageType getAvgType() 			 { return avgType; }
    public void 	   setAvgType(AverageType v) { avgType = v; }

    public int 	       getDays() 			     { return days; }
    public void 	   setDays(int v) 	         { days = v; }

    public double 	   getVal() 			     { return val; }
    public void 	   setVal(double v) 	     { val = v; }

}
