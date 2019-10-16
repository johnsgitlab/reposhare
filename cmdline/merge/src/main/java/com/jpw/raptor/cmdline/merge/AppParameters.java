package com.jpw.raptor.cmdline.merge;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.beust.jcommander.Parameter;

/**
 * Created by john on 3/30/18.
 */
public class AppParameters {

    @Parameter(names = "-old",
            description = "Old symbol",
            required = true)
    private String oldSymbol;

    public String getOldSymbol() {
        return oldSymbol;
    }


    @Parameter(names = "-new",
            description = "New symbol",
            required = true)
    private String newSymbol;

    public String getNewSymbol() {
        return newSymbol;
    }


    @Parameter(names = "-name",
            description = "Equity name",
            required = true)
    private String name;

    public String getName() {
        return name;
    }

}
