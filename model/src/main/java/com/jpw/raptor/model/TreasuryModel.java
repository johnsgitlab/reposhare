package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 12/9/18.
 */
@Getter
@Setter
public class TreasuryModel extends Treasury {

    protected String  datestr;

    // Constructor
    public TreasuryModel () {
        super();
        init();
    }

    // Constructor
    public TreasuryModel (String[] a) {
        super(a);
        updateDatestr();
    }

    // Constructor
    public TreasuryModel (Treasury t) {
        date        = t.date;
        oneMonth    = t.oneMonth;
        twoMonths   = t.twoMonths;
        threeMonths = t.threeMonths;
        sixMonths   = t.sixMonths;
        oneYear     = t.oneYear;
        twoYears    = t.twoYears;
        threeYears  = t.threeYears;
        fiveYears   = t.fiveYears;
        sevenYears  = t.sevenYears;
        tenYears    = t.tenYears;
        twentyYears = t.twentyYears;
        thirtyYears = t.thirtyYears;
        updateDatestr();
    }

    protected void init() {
        // Provide default values
        datestr = null;
    }

    public void     updateDatestr () {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        datestr              = sdf.format(this.date);
    }
}
