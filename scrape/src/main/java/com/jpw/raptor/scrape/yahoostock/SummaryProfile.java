package com.jpw.raptor.scrape.yahoostock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by john on 10/21/18.
 */

@Getter
@Setter
public class SummaryProfile {

    private boolean found;
    private String  sector;
    private String  industry;
    private String  longBusinessSummary;

    // Constructor
    public SummaryProfile () {
        found                   = false;
        sector                  = "";
        industry                = "";
        longBusinessSummary     = "";
    }


    public void parse(String symbol, String src) {

        // start "summaryDetail" end "}}"
        // for json parsing string must be enclosed in {} and parse object
        SummaryProfile rec = null;

        int start = src.indexOf("\"summaryProfile\"");
        int end   = src.indexOf("}", start);

        if ( start < 0 ) {
            return;
        }

        String raw = src.substring(start+17, end+1);

        try {
            Gson gson = new GsonBuilder().setLenient().create();
            rec       = gson.fromJson(raw,SummaryProfile.class);
        } catch (com.google.gson.JsonSyntaxException ex ) {

            System.out.println("### SummaryProfile JSON PARSE failed : " + symbol );
            System.out.println(ex.getMessage());
            return;
        }

        sector                  = rec.getSector();
        industry                = rec.getIndustry();
        longBusinessSummary     = rec.getLongBusinessSummary()
                                    .replace("\"", "")
                                    .replace("'","");
        found                   = true;
    }
}
