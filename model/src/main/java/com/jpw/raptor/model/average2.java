package com.jpw.raptor.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by john on 3/5/18.
 */
public class average2 {


    public enum AverageType {
        SMA5,
        SMA10,
        SMA20,
        SMA50,
        SMA100,
        SMA150,
        SMA200
    }

    private String 	                symbol;
    private Date                    date;
    private Map<String, Double>     map;


    protected void init() {
        // Provide default values
        symbol     = null;
        date       = null;
        map        = null;
    }

    // Constructor
    public average2 () {
        init();
    }

    // Constructor
    public average2 (String aSymbol, Date aDate, Map<String, Double> aMap ) {
        init();
        setSymbol(aSymbol);
        setDate(aDate);
        setMap(aMap);
    }

    public String 	   getSymbol() 			     { return symbol; }
    public void 	   setSymbol(String v) 		 { symbol = v; }

    public Date 	   getDate() 				 { return date; }
    public void 	   setDate(Date v) 			 { date = v; }

    public Map<String, Double> getMap() 			 { return map; }
    public void 	   setMap(Map<String, Double> v) { map = v; }

}
