package com.jpw.raptor.scrape.yahoostock.fields;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


/**
 * Created by john on 11/6/18.
 */
public class ValueObjTest {

    @Test
    public void testit() {

        System.out.println("DefaultKeyStatisticsTest ");
        String val =  "-0.068";

        ValueObj obj = new ValueObj();
        obj.setRaw(val);
        System.out.println(obj.getDouble() );
    }

    @Test
    public void test2() {

        System.out.println("most recent quarter test ");
        //"mostRecentQuarter":{"raw":1530316800,"fmt":"2018-06-30"},
        String yearEnd = "1506729600";
        String val     = "1530316800";

        long dateAsLong = Long.parseLong(val);

        SimpleDateFormat    formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date                recentQtr = new Date(dateAsLong * 1000);

        ValueObj obj = new ValueObj();
        obj.setRaw(val);
        System.out.println(formatter.format(recentQtr) );
    }

}
