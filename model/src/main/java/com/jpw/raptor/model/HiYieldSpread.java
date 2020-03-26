package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import org.apache.commons.lang3.StringUtils;


import javax.persistence.*;
import javax.persistence.Index;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by john on 12/6/18.
 */
@Data
@Entity
@Table(
        name = "hi_yield_spread_tbl",
        indexes = {@Index(name = "idx_hi_yield_date", columnList = "date_tx")}
)
public class HiYieldSpread {

    @Id
    @Column(name = "date_tx", unique=true, nullable = false,  columnDefinition="")
    protected Date   date;

    @Column(name = "spread", columnDefinition="")
    protected double spread;

    // Constructor
    public HiYieldSpread () {
        init();
    }

    // Constructor
    public HiYieldSpread(String[] a) {
        init();
        parseArray(a);
    }

    protected void init() {
        date     = new Date(-1);
        spread   = -9999.0;
    }

    protected void parseArray(String[] a) {

        int dateIdx        = 0;
        int spreadIdx      = 1;

        if ( a == null || a.length < 2 )
            return;

        if ( !StringUtils.isEmpty(a[dateIdx]) ) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf.parse(a[dateIdx]);
            } catch (ParseException ex) { /* use default */ }
        }

        if ( !StringUtils.isEmpty(a[spreadIdx]) ) {
            try {
                spread = Double.parseDouble(a[spreadIdx]);
            } catch (NumberFormatException ex) { /* use default */ }
        }

    }

}
