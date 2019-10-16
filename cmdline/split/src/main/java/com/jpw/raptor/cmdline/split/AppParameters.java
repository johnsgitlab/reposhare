package com.jpw.raptor.cmdline.split;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.beust.jcommander.Parameter;

/**
 * Created by john on 3/30/18.
 */
public class AppParameters {

    @Parameter(names = "-date",
            description = "Date split occured",
            validateWith = DateValidator.class,
            required = true)
    private String date;

    public String getDate() {
        return date;
    }


    @Parameter(names = "-symbol",
            description = "Equity that split",
            required = true)
    private String symbol;

    public String getSymbol() {
        return symbol;
    }


    @Parameter(names = "-split",
            description = "Number of new shares for each old share",
            validateWith = SplitValidator.class,
            required = true)
    private String split;

    public String getSplit() {
        return split;
    }


    @Parameter(names = "-type",
            description = "Equity type etf, fund or stock",
            validateWith = TypeValidator.class,
            required = true)
    private String type;

    public String getType() {
        return type;
    }

}
