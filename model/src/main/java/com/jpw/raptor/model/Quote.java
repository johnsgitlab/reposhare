package com.jpw.raptor.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.IdClass;
import javax.persistence.EmbeddedId;

import java.util.Date;

/**
 * Created by john on 4/2/17.
 */
@Data

@Entity
@IdClass(QuoteId.class)
@Table(name = "quote_tbl")
public class Quote  {

    @EmbeddedId
    protected QuoteId id;

    @Column(name = "open", columnDefinition="")
    protected double  open;

    @Column(name = "high", columnDefinition="")
    protected double  high;

    @Column(name = "low", columnDefinition="")
    protected double  low;

    @Column(name = "close", columnDefinition="")
    protected double  close;

    @Column(name = "volume", columnDefinition="")
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
