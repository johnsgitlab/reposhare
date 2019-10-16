package com.jpw.raptor.scrape.quandl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.jpw.raptor.model.Treasury;
import com.jpw.raptor.scrape.quandl.fields.Dataset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by john on 12/5/18.
 */
public class QuandlScrapper {

    String apiKey = "j8cdE5j4GsVHF6gwUWuP";

    // Treasury data yield cure rates
    // https://www.quandl.com/api/v3/datasets/USTREASURY/YIELD.json?api_key=j8cdE5j4GsVHF6gwUWuP&start_date=2018-11-01

    public SortedMap<String, Treasury> readPage(String startDate) {

        Dataset                     dataset = getYieldCurveDataset(startDate);
        SortedMap<String, Treasury> map     = getTreasuryFromDataset(dataset);

        return map;
    }


    public SortedMap<String, Treasury> getTreasuryFromDataset(Dataset dataset) {

        // get raw data
        String[][] data = dataset.getData();
        int        size = data.length;

        // allocate space for map
        TreeMap<String, Treasury> map = new TreeMap<>();

        // generate treasuries and add to map
        for ( int i=0; i<size; i++) {
            map.put( data[i][0], new Treasury(data[i]) );
        }

        // print the results
        //for (Map.Entry<String, Treasury> entry : map.entrySet()) {
        //    System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().toString());
        //}

        return map;
    }


    public Dataset getYieldCurveDataset(String startDate) {

        StringBuilder sb   = new StringBuilder();
        String        uri  = "https://www.quandl.com/api/v3/datasets/USTREASURY/YIELD.json?api_key=j8cdE5j4GsVHF6gwUWuP&start_date=" + startDate;
        String        line = null;

        try {

            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println(uri + " Failed : HTTP error code : " + conn.getResponseCode());
                return null;
            } else {
                //System.out.println("HTTP response code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

        //System.out.println(" get yield " + uri);
        //System.out.println(sb.toString());

        Dataset rec = null;

        int     start   = 11;
        int     end     = sb.toString().length()-1;
        String  buf     = sb.toString().substring(start, end);

        try {
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setLenient()
                    .create();
            rec = gson.fromJson(buf,Dataset.class);
        } catch (com.google.gson.JsonSyntaxException ex ) {

            System.out.println("### Company JSON PARSE failed : "  );
            System.out.println(ex.getMessage());
        }

        return rec;
    }
}
