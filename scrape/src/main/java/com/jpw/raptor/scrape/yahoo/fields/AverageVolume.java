package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/13/2017.
 */
public class AverageVolume {
    private int    raw;
    private String fmt;

    public AverageVolume(){}

    public void     setRaw(int v)    { raw = v; }
    public void     setFmt(String v) { fmt = v; }

    public int      getRaw ()        { return raw; }
    public String   getFmt ()        { return fmt; }
}
