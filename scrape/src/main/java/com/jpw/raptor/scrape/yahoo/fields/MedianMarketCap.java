package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/11/2017.
 */
public class MedianMarketCap {
    private double raw;
    private String fmt;

    public MedianMarketCap(){}

    public void     setRaw(double v) { raw = v; }
    public void     setFmt(String v) { fmt = v; }

    public double   getRaw ()        { return raw; }
    public String   getFmt ()        { return fmt; }
}