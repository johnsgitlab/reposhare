package com.jpw.raptor.cmdline.add;

import com.beust.jcommander.Parameter;

/**
 * Created by john on 5/3/18.
 */
public class AppParameters {

    @Parameter(names = "-etf",
            description = "ETF to add",
            required = false)
    private String etf;

    public String getEtf() {
        return etf;
    }

    @Parameter(names = "-fund",
            description = "Mutual fund to add",
            required = false)
    private String fund;

    public String getFund() {
        return fund;
    }

    @Parameter(names = "-index",
            description = "Index fund to add",
            required = false)
    private String index;

    public String getIndex() {
        return index;
    }

    @Parameter(names = "-stock",
            description = "Stock fund to add",
            required = false)
    private String stock;

    public String getStock() {
        return stock;
    }
}
