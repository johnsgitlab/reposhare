package com.jpw.raptor.scrape.yahoostock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpw.raptor.scrape.yahoostock.fields.ValueObj;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by john on 10/21/18.
 */

@Getter
@Setter
public class FinancialData {

    private boolean  found;
    private ValueObj ebitdaMargins;
    private ValueObj profitMargins;
    private ValueObj grossMargins;
    private ValueObj operatingCashflow;
    private ValueObj revenueGrowth;
    private ValueObj operatingMargins;
    private ValueObj ebitda;
    private ValueObj freeCashflow;
    private ValueObj earningsGrowth;
    private ValueObj currentRatio;
    private ValueObj returnOnAssets;
    private ValueObj debtToEquity;
    private ValueObj returnOnEquity;
    private ValueObj totalCash;
    private ValueObj totalDebt;
    private ValueObj totalRevenue;
    private ValueObj totalCashPerShare;

    // Constructor
    public FinancialData () {
        found               = false;
        ebitdaMargins       = null;
        profitMargins       = null;
        grossMargins        = null;
        operatingCashflow   = null;
        revenueGrowth       = null;
        operatingMargins    = null;
        ebitda              = null;
        freeCashflow        = null;
        earningsGrowth      = null;
        currentRatio        = null;
        returnOnAssets      = null;
        debtToEquity        = null;
        returnOnEquity      = null;
        totalCash           = null;
        totalDebt           = null;
        totalRevenue        = null;
        totalCashPerShare   = null;
    }


    public void parse(String symbol, String src) {

        // start "defaultKeyStatistics" end "}}"
        // start "financialData" end "}},"
        // for json parsing string must be enclosed in {} and parse object
        FinancialData rec = null;

        int start = src.indexOf("\"financialData\"");
        int end   = src.indexOf("}}", start);

        if ( start < 0 ) {
            return;
        }

        String raw = src.substring(start+16, end+2);

        try {
            Gson gson = new GsonBuilder().setLenient().create();
            rec       = gson.fromJson(raw,FinancialData.class);
        } catch (com.google.gson.JsonSyntaxException ex ) {

            System.out.println("### FinancialData JSON PARSE failed : " + symbol );
            System.out.println(ex.getMessage());
            return;
        }

        if (rec.ebitdaMargins == null) rec.ebitdaMargins = new ValueObj();
        ebitdaMargins = rec.ebitdaMargins;

        if (rec.profitMargins == null) rec.profitMargins = new ValueObj();
        profitMargins = rec.profitMargins;

        if (rec.grossMargins == null) rec.grossMargins = new ValueObj();
        grossMargins = rec.grossMargins;

        if (rec.operatingCashflow == null) rec.operatingCashflow = new ValueObj();
        operatingCashflow = rec.operatingCashflow;

        if (rec.revenueGrowth == null) rec.revenueGrowth = new ValueObj();
        revenueGrowth = rec.revenueGrowth;

        if (rec.operatingMargins == null) rec.operatingMargins = new ValueObj();
        operatingMargins = rec.operatingMargins;

        if (rec.ebitda == null) rec.ebitda = new ValueObj();
        ebitda = rec.ebitda;

        if (rec.freeCashflow == null) rec.freeCashflow = new ValueObj();
        freeCashflow = rec.freeCashflow;

        if (rec.earningsGrowth == null) rec.earningsGrowth = new ValueObj();
        earningsGrowth = rec.earningsGrowth;

        if (rec.currentRatio == null) rec.currentRatio = new ValueObj();
        currentRatio = rec.currentRatio;

        if (rec.returnOnAssets == null) rec.returnOnAssets = new ValueObj();
        returnOnAssets = rec.returnOnAssets;

        if (rec.debtToEquity == null) rec.debtToEquity = new ValueObj();
        debtToEquity = rec.debtToEquity;

        if (rec.returnOnEquity == null) rec.returnOnEquity = new ValueObj();
        returnOnEquity = rec.returnOnEquity;

        if (rec.totalCash == null) rec.totalCash = new ValueObj();
        totalCash = rec.totalCash;

        if (rec.totalDebt == null) rec.totalDebt = new ValueObj();
        totalDebt = rec.totalDebt;

        if (rec.totalRevenue == null) rec.totalRevenue = new ValueObj();
        totalRevenue = rec.totalRevenue;

        if (rec.totalCashPerShare == null) rec.totalCashPerShare = new ValueObj();
        totalCashPerShare = rec.totalCashPerShare;

        found = true;
    }
}
