package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/14/2017.
 */
public class BondPosition {
    private double raw;
    private String fmt;

    public BondPosition(){}

    public void     setRaw(double v) { raw = v; }
    public void     setFmt(String v) { fmt = v; }

    public double   getRaw ()        { return raw; }
    public String   getFmt ()        { return fmt; }
}
