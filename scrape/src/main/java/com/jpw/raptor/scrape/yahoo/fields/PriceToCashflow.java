package com.jpw.raptor.scrape.yahoo.fields;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 10/11/2017.
 */
public class PriceToCashflow {
    @SerializedName("raw")
    @Expose
    private Double raw;
    @SerializedName("fmt")
    @Expose
    private String fmt;

    public PriceToCashflow(){}

    public void     setRaw(double v) { raw = v; }
    public void     setFmt(String v) { fmt = v; }

    public double   getRaw ()        { return raw; }
    public String   getFmt ()        { return fmt; }
}
