package com.jpw.raptor.scrape.quandl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpw.raptor.model.Treasury;
import com.jpw.raptor.scrape.quandl.fields.Dataset;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 12/5/18.
 */
public class QuandlScrapperTest {


    @Test
    //
    // Risk Stats
    public void test01() {

        QuandlScrapper ls = new QuandlScrapper();

        System.out.println("test company get");

        Dataset rec = ls.getYieldCurveDataset("2018-12-01");
        System.out.println(rec.toString());

    }
    @Test
    //
    // Risk Stats
    public void test02() {

        // data returned from rest call
        String data01 =
                "{" +
                        "\"dataset\":" +
                        "{" +
                        "\"id\":98332," +
                        "\"dataset_code\":\"YIELD\"," +
                        "\"database_code\":\"USTREASURY\"," +
                        "\"name\":\"Treasury Yield Curve Rates\"," +
                        "\"description\":\"These rates are interpolated by the Treasury from the daily yield curve on the closing market bid yields\"," +
                        "\"refreshed_at\":\"2018-12-05T23:06:43.207Z\"," +
                        "\"newest_available_date\":\"2018-12-04\"," +
                        "\"oldest_available_date\":\"1990-01-02\"," +
                        "\"column_names\":[\"Date\",\"1 MO\",\"2 MO\",\"3 MO\",\"6 MO\",\"1 YR\",\"2 YR\",\"3 YR\",\"5 YR\",\"7 YR\",\"10 YR\",\"20 YR\",\"30 YR\"]," +
                        "\"frequency\":\"daily\"," +
                        "\"type\":\"Time Series\"," +
                        "\"premium\":false," +
                        "\"limit\":null," +
                        "\"transform\":null," +
                        "\"column_index\":null," +
                        "\"start_date\":\"1990-01-02\"," +
                        "\"end_date\":\"2018-12-04\"," +
                        "\"data\":[" +
                        "[\"2018-12-04\",2.37,2.42,2.45,2.58,2.71,2.8,2.81,2.79,2.84,2.91,3.05,3.16]," +
                        "[\"2018-12-03\",2.3,2.35,2.38,2.56,2.72,2.83,2.84,2.83,2.9,2.98,3.15,3.27]," +
                        "[\"2018-11-30\",2.31,2.33,2.37,2.52,2.7,2.8,2.83,2.84,2.92,3.01,3.19,3.3]" +
                        "]," +
                        "\"collapse\":null," +
                        "\"order\":null," +
                        "\"database_id\":70" +
                        "}" +
                        "}";

        Dataset rec = null;

        int     start   = 11;
        int     end     = data01.length()-1;
        String  v       = data01.substring(start, end);

        try {
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setLenient()
                    .create();
            rec       = gson.fromJson(v,Dataset.class);
        } catch (com.google.gson.JsonSyntaxException ex ) {

            System.out.println("### Company JSON PARSE failed : "  );
            System.out.println(ex.getMessage());
        }

        String[][] data = rec.getData();

        Treasury de = new Treasury(data[0]);
        System.out.println(de.getDate());
        assertEquals(de.getOneMonth(),2.37,0.001);
        assertEquals(de.getTwoMonths(),2.42,0.001);
        assertEquals(de.getThreeMonths(),2.45,0.001);
        assertEquals(de.getSixMonths(),2.58,0.001);
        assertEquals(de.getOneYear(),2.71,0.001);
        assertEquals(de.getTwoYears(),2.8,0.001);
        assertEquals(de.getThreeYears(),2.81,0.001);
        assertEquals(de.getFiveYears(),2.79,0.001);
        assertEquals(de.getSevenYears(),2.84,0.001);
        assertEquals(de.getTenYears(),2.91,0.001);
        assertEquals(de.getTwentyYears(),3.05,0.001);
        assertEquals(de.getThirtyYears(),3.16,0.001);

        QuandlScrapper qs = new QuandlScrapper();
        qs.getTreasuryFromDataset(rec);

        System.out.println("data records " + data.length);
        System.out.println("key          " + data[0][0]);
    }

}
