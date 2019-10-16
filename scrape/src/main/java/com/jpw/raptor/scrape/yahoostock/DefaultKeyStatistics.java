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
public class DefaultKeyStatistics {

    private boolean  found;
    private ValueObj trailingEps;
    private ValueObj enterpriseValue;
    private ValueObj pegRatio;
    private ValueObj priceToBook;
    private ValueObj enterpriseToRevenue;
    private ValueObj enterpriseToEbitda;
    private ValueObj mostRecentQuarter;


    // Constructor
    public DefaultKeyStatistics () {
        found               = false;
        trailingEps         = null;
        enterpriseValue     = null;
        pegRatio            = null;
        priceToBook         = null;
        enterpriseToRevenue = null;
        enterpriseToEbitda  = null;
        mostRecentQuarter   = null;
    }

    public void parse(String symbol, String src) {

        // start "defaultKeyStatistics" end "}}"
        // for json parsing string must be enclosed in {} and parse object
        DefaultKeyStatistics rec = null;

        int start = src.indexOf("\"defaultKeyStatistics\"");
        int end   = src.indexOf("}}", start);

        if ( start < 0 ) {
            return;
        }

        String raw = src.substring(start+23, end+2);

        try {
            Gson gson = new GsonBuilder().setLenient().create();
            rec       = gson.fromJson(raw,DefaultKeyStatistics.class);
        } catch (com.google.gson.JsonSyntaxException ex ) {

            System.out.println("### DefaultKeyStatistics JSON PARSE failed : " + symbol );
            System.out.println(ex.getMessage());
            return;
        }

        if ( rec.trailingEps == null ) rec.trailingEps = new ValueObj();
        trailingEps = rec.trailingEps;

        if ( rec.enterpriseValue == null ) rec.enterpriseValue = new ValueObj();
        enterpriseValue = rec.enterpriseValue;

        if ( rec.pegRatio == null ) rec.pegRatio = new ValueObj();
        pegRatio = rec.pegRatio;

        if ( rec.priceToBook == null ) rec.priceToBook = new ValueObj();
        priceToBook = rec.priceToBook;

        if ( rec.enterpriseToRevenue == null ) rec.enterpriseToRevenue = new ValueObj();
        enterpriseToRevenue = rec.enterpriseToRevenue;

        if ( rec.enterpriseToEbitda == null ) rec.enterpriseToEbitda = new ValueObj();
        enterpriseToEbitda      = rec.enterpriseToEbitda;

        if ( rec.mostRecentQuarter == null ) rec.mostRecentQuarter = new ValueObj();
        mostRecentQuarter      = rec.mostRecentQuarter;

        found = true;
    }
}
