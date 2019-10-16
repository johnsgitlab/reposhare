package com.jpw.raptor.model;

import lombok.*;

import java.util.Date;

/**
 * Created by john on 4/2/17.
 */
@Data
public class Quote  {

    protected QuoteId id;
    protected double  open;
    protected double  high;
    protected double  low;
    protected double  close;
    protected long    volume;

    public Quote() {
        id = new QuoteId();
    }

    public Quote (String symbol, Date date) {
        QuoteId id = new QuoteId();
        id.setSymbol(symbol);
        id.setDate(date);
        this.id = id;
    }

    public Quote (String symbol, Date date, double open, double high, double low, double close, long volume) {
        QuoteId id  = new QuoteId();
        id.setSymbol(symbol);
        id.setDate(date);
        this.id     = id;
        this.open   = open;
        this.high   = high;
        this.low    = low;
        this.close  = close;
        this.volume = volume;
    }

    public String getSymbol()           { return id.getSymbol(); }
    public void   setSymbol(String v)   { id.setSymbol(v); }

    public Date   getDate()             { return id.getDate(); }
    public void   setDate(Date v)       { id.setDate(v); }
}
