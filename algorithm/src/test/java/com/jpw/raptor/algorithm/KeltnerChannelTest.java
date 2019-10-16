package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Adx;
import com.jpw.raptor.model.Keltner;
import com.jpw.raptor.model.Quote;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KeltnerChannelTest {

    @Test
    public void test() throws ParseException {
        System.out.println(" test started");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Quote[] dataArray = {
                new Quote("IVV", fmt.parse("2018-08-23"), 285.97, 286.94, 285.43, 285.79, 0),
                new Quote("IVV", fmt.parse("2018-08-22"), 285.88, 286.76, 285.58, 286.17, 0),
                new Quote("IVV", fmt.parse("2018-08-21"), 286.25, 287.31, 285.71, 286.34, 0),
                new Quote("IVV", fmt.parse("2018-08-20"), 285.57, 285.97, 285.06, 285.67, 0),
                new Quote("IVV", fmt.parse("2018-08-17"), 283.83, 285.56, 283.37, 285.06, 0),
                new Quote("IVV", fmt.parse("2018-08-16"), 283.41, 285.04, 283.36, 284.06, 0),
                new Quote("IVV", fmt.parse("2018-08-15"), 282.38, 282.54, 280.16, 281.78, 0),
                new Quote("IVV", fmt.parse("2018-08-14"), 282.92, 284.17, 282.48, 283.91, 0),
                new Quote("IVV", fmt.parse("2018-08-13"), 283.47, 284.16, 281.77, 282.11, 0),
                new Quote("IVV", fmt.parse("2018-08-10"), 283.45, 284.06, 282.36, 283.16, 0),
                new Quote("IVV", fmt.parse("2018-08-09"), 285.53, 285.97, 284.92, 285.07, 0),
                new Quote("IVV", fmt.parse("2018-08-08"), 285.39, 285.91, 284.94, 285.46, 0),
                new Quote("IVV", fmt.parse("2018-08-07"), 285.39, 286.01, 285.24, 285.58, 0),
                new Quote("IVV", fmt.parse("2018-08-06"), 283.64, 284.99, 283.21, 284.64, 0),
                new Quote("IVV", fmt.parse("2018-08-03"), 282.53, 283.66, 282.33, 283.61, 0),
                new Quote("IVV", fmt.parse("2018-08-02"), 279.39, 282.58, 279.16, 282.39, 0),
                new Quote("IVV", fmt.parse("2018-08-01"), 281.56, 282.13, 280.13, 280.86, 0),
                new Quote("IVV", fmt.parse("2018-07-31"), 280.81, 282.02, 280.38, 281.33, 0),
                new Quote("IVV", fmt.parse("2018-07-30"), 281.51, 281.69, 279.36, 279.95, 0),
                new Quote("IVV", fmt.parse("2018-07-27"), 283.71, 283.82, 280.38, 281.42, 0),
                new Quote("IVV", fmt.parse("2018-07-26"), 285.97, 286.94, 285.43, 285.79, 0),
                new Quote("IVV", fmt.parse("2018-07-25"), 285.88, 286.76, 285.58, 286.17, 0),
                new Quote("IVV", fmt.parse("2018-07-24"), 286.25, 287.31, 285.71, 286.34, 0),
                new Quote("IVV", fmt.parse("2018-07-23"), 285.57, 285.97, 285.06, 285.67, 0),
                new Quote("IVV", fmt.parse("2018-07-20"), 283.83, 285.56, 283.37, 285.06, 0),

        };

        List<Quote>     data = Arrays.asList(dataArray);
        KeltnerChannel  kc   = new KeltnerChannel();

        List<Keltner>   list = kc.generateKeltner(data);

        assertEquals(286.13,  list.get(0).getMiddle(),0.01);
        assertEquals(289.96,  list.get(0).getUpper(),0.01);
        assertEquals(282.31,  list.get(0).getLower(),0.01);

        assertEquals(286.32,  list.get(1).getMiddle(),0.01);
        assertEquals(290.12,  list.get(1).getUpper(),0.01);
        assertEquals(282.52,  list.get(1).getLower(),0.01);

        assertEquals(285.74,  list.get(2).getMiddle(),0.01);
        assertEquals(289.86,  list.get(2).getUpper(),0.01);
        assertEquals(281.62,  list.get(2).getLower(),0.01);

        assertEquals(285.12,  list.get(3).getMiddle(),0.01);
        assertEquals(289.29,  list.get(3).getUpper(),0.01);
        assertEquals(280.95,  list.get(3).getLower(),0.01);

        assertEquals(284.16,  list.get(4).getMiddle(),0.01);
        assertEquals(288.50,  list.get(4).getUpper(),0.01);
        assertEquals(279.82,  list.get(4).getLower(),0.01);

        assertEquals(282.01,  list.get(5).getMiddle(),0.01);        // 282
        assertEquals(286.36,  list.get(5).getUpper(),0.01);
        assertEquals(277.65,  list.get(5).getLower(),0.01);         // 277.64

        System.out.println(" test finished");
    }
}
