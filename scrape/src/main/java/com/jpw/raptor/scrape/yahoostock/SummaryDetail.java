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
public class SummaryDetail {

    private boolean  found;
    private ValueObj averageDailyVolume10Day;
    private ValueObj beta;
    private ValueObj trailingPE;
    private ValueObj forwardPE;
    private ValueObj marketCap;
    private ValueObj priceToSalesTrailing12Months;
    private ValueObj dividendRate;
    private ValueObj dividendYield;


    // Constructor
    public SummaryDetail () {
        found                           = false;
        averageDailyVolume10Day         = null;
        beta                            = null;
        trailingPE                      = null;
        forwardPE                       = null;
        marketCap                       = null;
        priceToSalesTrailing12Months    = null;
        dividendRate                    = null;
        dividendYield                   = null;
    }


    public void parse(String symbol, String src) {

        // start "summaryDetail" end "}}"
        // for json parsing string must be enclosed in {} and parse object
        SummaryDetail rec = null;

        int start = src.indexOf("\"summaryDetail\"");
        int end   = src.indexOf("}}", start);

        if ( start < 0 ) {
            return;
        }

        String raw = src.substring(start+16, end+2);

        try {
            Gson gson = new GsonBuilder().setLenient().create();
            rec       = gson.fromJson(raw,SummaryDetail.class);
        } catch (com.google.gson.JsonSyntaxException ex ) {

            System.out.println("### SummaryDetail JSON PARSE failed : " + symbol );
            System.out.println(ex.getMessage());
            return;
        }

        if (rec.averageDailyVolume10Day == null) rec.averageDailyVolume10Day = new ValueObj();
        averageDailyVolume10Day = rec.averageDailyVolume10Day;

        if (rec.beta == null) rec.beta = new ValueObj();
        beta = rec.beta;

        if (rec.trailingPE == null) rec.trailingPE = new ValueObj();
        trailingPE = rec.trailingPE;

        if (rec.forwardPE == null) rec.forwardPE = new ValueObj();
        forwardPE = rec.forwardPE;

        if (rec.marketCap == null) rec.marketCap = new ValueObj();
        marketCap = rec.marketCap;

        if (rec.priceToSalesTrailing12Months == null) rec.priceToSalesTrailing12Months = new ValueObj();
        priceToSalesTrailing12Months = rec.priceToSalesTrailing12Months;

        if (rec.dividendRate == null) rec.dividendRate = new ValueObj();
        dividendRate = rec.dividendRate;

        if (rec.dividendYield == null) rec.dividendYield = new ValueObj();
        dividendYield = rec.dividendYield;

        found = true;
    }
}
