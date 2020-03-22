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
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by john on 4/2/17.
 */
@Data
@NoArgsConstructor

@Embeddable
public class QuoteId implements Serializable {

    @Column(name = "symbol", columnDefinition="")
    private String  symbol;

    @Column(name = "date_tx", columnDefinition="")
    @Temporal(TemporalType.DATE)
    private Date    date;

    public QuoteId(String symbol, Date date) {
        this.symbol = symbol;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuoteId that = (QuoteId) o;

        if (!symbol.equals(that.symbol)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

}
