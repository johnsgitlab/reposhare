package com.jpw.raptor.scrape.yahoostock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpw.raptor.scrape.yahoostock.fields.UpgradeDowngradeObj;

/**
 * Created by john on 10/31/18.
 */
public class UpgradeDowngrade {


    public UpgradeDowngradeObj[] parse(String symbol, String src) {

        // start "upgradeDowngradeHistory":{"history":[" end "]"
        // for json parsing string must be enclosed in {} and parse object

        int start = src.indexOf("\"upgradeDowngradeHistory\"");
        int end   = src.indexOf(']', start);

        String raw = src.substring(start+37, end+1);

        UpgradeDowngradeObj[] array = null;
        try {
            Gson gson = new GsonBuilder().setLenient().create();
            array = gson.fromJson(raw, UpgradeDowngradeObj[].class);
        } catch (com.google.gson.JsonSyntaxException ex ) {

            System.out.println("### UpgradeDowngrade JSON PARSE failed : " + symbol );
            System.out.println(ex.getMessage());
        }

        return array;
    }
}
