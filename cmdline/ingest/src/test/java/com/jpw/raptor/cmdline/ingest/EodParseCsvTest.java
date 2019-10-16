package com.jpw.raptor.cmdline.ingest;

import com.jpw.raptor.model.Quote;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by john on 3/29/18.
 */
public class EodParseCsvTest {

    @Test
    public void test01() {
        System.out.println("test 01 started");

        SimpleDateFormat simpleDateFormat    = new SimpleDateFormat("yyyyMMdd");
        Date             validDate           = new Date(-1);
        try {
            validDate = simpleDateFormat.parse("20160212");
        } catch ( java.text.ParseException ex) {
            System.out.println ("Quote delete date format exception");
        }

        String str = "abc,20160212,1.0,2.0,3.0,4.0,5";

        EodParser parser = new EodParser();

        Quote q = parser.parseCsvLine(str);

        assertTrue(q.getSymbol().equalsIgnoreCase("abc"));
        assertEquals(0, q.getDate().compareTo(validDate));
        assertEquals(1.0, q.getOpen(), 0.01);
        assertEquals(2.0, q.getHigh(), 0.01);
        assertEquals(3.0, q.getLow(), 0.01);
        assertEquals(4.0, q.getClose(), 0.01);
        assertEquals(5, q.getVolume());

    }
}
