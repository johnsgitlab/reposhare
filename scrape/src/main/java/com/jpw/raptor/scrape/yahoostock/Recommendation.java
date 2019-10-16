package com.jpw.raptor.scrape.yahoostock;

import lombok.Getter;
import lombok.Setter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by john on 10/30/18.
 */
@Getter
@Setter
public class Recommendation {

    private boolean found;
    private int     recommendations;
    private int     strongBuy;
    private int     buy;
    private int     hold;
    private int     sell;
    private int     strongSell;

    // Constructor
    public Recommendation () {
        found               = false;
        recommendations     = 0;
        strongBuy           = 0;
        buy                 = 0;
        hold                = 0;
        sell                = 0;
        strongSell          = 0;;
    }

    public void parse(String symbol, String src) {

        // start "period":"0m" end "},"
        // for json parsing string must be enclosed in {} and parse Recommendation object
        Recommendation rec = null;

        int start = src.indexOf("\"period\":\"0m\"");
        int end   = src.indexOf("},", start);

        if ( start < 0 ) {
            return;
        }

        String raw = "{" + src.substring(start, end+1);

        try {
            Gson gson = new GsonBuilder().setLenient().create();
            rec       = gson.fromJson(raw,Recommendation.class);
        } catch (com.google.gson.JsonSyntaxException ex ) {

            System.out.println("### Recommendation JSON PARSE failed : " + symbol );
            System.out.println(ex.getMessage());
            return;
        }

        strongBuy       = rec.getStrongBuy();
        buy             = rec.getBuy();
        hold            = rec.getHold();
        sell            = rec.getSell();
        strongSell      = rec.getStrongSell();
        recommendations = strongBuy + buy + hold + sell + strongSell;
        found           = true;
    }
}
