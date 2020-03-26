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
@Getter
@Setter
@Entity
@IdClass(QuoteId.class)
@Table(name = "quote_tbl")
public class Quote  {

    @Id
    @Column(name = "symbol", columnDefinition="")
    protected String  symbol;

    @Id
    @Column(name = "date_tx", columnDefinition="")
    @Temporal(TemporalType.DATE)
    protected Date    date;

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

    public Quote() {}

    public Quote (String symbol, Date date) {
        this.symbol = symbol;
        this.date = date;
    }

    public Quote (String symbol, Date date, double open, double high, double low, double close, long volume) {
        this.symbol = symbol;
        this.date   = date;
        this.open   = open;
        this.high   = high;
        this.low    = low;
        this.close  = close;
        this.volume = volume;
    }

}
