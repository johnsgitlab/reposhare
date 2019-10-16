package com.jpw.raptor.scrape.yahoo.fields;

/**
 * Created by John on 10/15/2017.
 */
public class FundInceptionDate {
    private long   raw;
    private String fmt;

    public FundInceptionDate(){}

    public void     setRaw(long v)   { raw = v; }
    public void     setFmt(String v) { fmt = v; }

    public long     getRaw ()        { return raw; }
    public String   getFmt ()        { return fmt; }
}
