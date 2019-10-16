package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.text.StringEscapeUtils;
import java.util.Date;

/**
 * Created by john on 11/14/18.
 */
@Getter
@Setter
public class StockSummaryModel {

    private Date    date;
    private String 	sector;
    private String 	industry;
    private double 	ytd;
    private double 	oneDay;
    private double 	oneWeek;
    private double 	twoWeeks;
    private double 	fourWeeks;
    private double 	threeMonths;
    private double 	oneYear;
    private int 	count;

    // Constructor
    public StockSummaryModel () {
        date               = null;
        sector             = null;
        industry           = null;
        ytd                = 0.0;
        oneDay             = 0.0;
        oneWeek            = 0.0;
        twoWeeks           = 0.0;
        fourWeeks          = 0.0;
        threeMonths        = 0.0;
        oneYear            = 0.0;
        count              = 0;
    }

}
