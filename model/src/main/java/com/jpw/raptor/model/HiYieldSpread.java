package com.jpw.raptor.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import org.apache.commons.lang3.StringUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by john on 12/6/18.
 */
@Data
public class HiYieldSpread {

    protected Date   date;
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
