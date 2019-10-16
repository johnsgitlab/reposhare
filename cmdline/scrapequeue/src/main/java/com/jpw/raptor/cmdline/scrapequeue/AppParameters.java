package com.jpw.raptor.cmdline.scrapequeue;

import com.beust.jcommander.Parameter;

/**
 * Created by john on 3/30/18.
 */
public class AppParameters {

    @Parameter(names = "-type",
            description = "Equity type stock, etf or fund",
            validateWith = TypeValidator.class,
            required = true)
    private String type;

    public String getType() {
        return type;
    }


    @Parameter(names = "-select",
            description = "Equity subset all, relevant or tracked",
            validateWith = SelectValidator.class,
            required = false)
    private String select;

    public String getSelect() {
        return select;
    }


    @Parameter(names = "-symbol",
            description = "Equity symbol",
            required = false)
    private String symbol;

    public String getSymbol() {
        return symbol;
    }
}
