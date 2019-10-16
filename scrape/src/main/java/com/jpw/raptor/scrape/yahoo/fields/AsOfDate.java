package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/13/2017.
 */
public class AsOfDate {
    private long   raw;
    private String fmt;

    public AsOfDate(){}

    public void     setRaw(long v)   { raw = v; }
    public void     setFmt(String v) { fmt = v; }

    public long     getRaw ()        { return raw; }
    public String   getFmt ()        { return fmt; }
}
