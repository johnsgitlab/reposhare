package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Adx;
import com.jpw.raptor.model.Quote;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Created by john on 6/12/18.
 */
public class AvgDirMovementIndicatorTest {


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
            new Quote("IVV", fmt.parse("2018-08-16"), 283.4, 285.04, 283.36, 284.06, 0),
            new Quote("IVV", fmt.parse("2018-08-15"), 282.38, 282.54, 280.16, 281.78, 0),
            new Quote("IVV", fmt.parse("2018-08-14"), 282.92, 284.17, 282.48, 283.9, 0),
            new Quote("IVV", fmt.parse("2018-08-13"), 283.47, 284.16, 281.77, 282.1, 0),
            new Quote("IVV", fmt.parse("2018-08-10"), 283.45, 284.06, 282.36, 283.16, 0),
            new Quote("IVV", fmt.parse("2018-08-09"), 285.53, 285.97, 284.92, 285.07, 0),
            new Quote("IVV", fmt.parse("2018-08-08"), 285.39, 285.91, 284.94, 285.46, 0),
            new Quote("IVV", fmt.parse("2018-08-07"), 285.39, 286.01, 285.24, 285.58, 0),
            new Quote("IVV", fmt.parse("2018-08-06"), 283.64, 284.99, 283.2, 284.64, 0),
            new Quote("IVV", fmt.parse("2018-08-03"), 282.53, 283.66, 282.33, 283.6, 0),
            new Quote("IVV", fmt.parse("2018-08-02"), 279.39, 282.58, 279.16, 282.39, 0),
            new Quote("IVV", fmt.parse("2018-08-01"), 281.56, 282.13, 280.13, 280.86, 0),
            new Quote("IVV", fmt.parse("2018-07-31"), 280.81, 282.02, 280.38, 281.33, 0),
            new Quote("IVV", fmt.parse("2018-07-30"), 281.51, 281.69, 279.36, 279.95, 0),
            new Quote("IVV", fmt.parse("2018-07-27"), 283.71, 283.82, 280.38, 281.42, 0)
        };

        List<Quote>               data    = Arrays.asList(dataArray);
        AvgDirMovementIndicator   so      = new AvgDirMovementIndicator();

        List<Adx> list = so.generateAdx(data, 4);

        assertEquals(58.95,  list.get(0).getAverageDx(),0.01);
        assertEquals(55.59, list.get(1).getAverageDx(),0.01);
        assertEquals(54.14, list.get(2).getAverageDx(),0.01);
        assertEquals(43.13, list.get(3).getAverageDx(),0.01);
        assertEquals(43.94, list.get(4).getAverageDx(),0.01);
        assertEquals(53.43, list.get(5).getAverageDx(),0.01);
        assertEquals(78.56, list.get(6).getAverageDx(),0.01);
        assertEquals(69.38, list.get(7).getAverageDx(),0.01);
        assertEquals(66.44, list.get(8).getAverageDx(),0.01);
        assertEquals(59.92, list.get(9).getAverageDx(),0.01);
        assertEquals(53.87, list.get(10).getAverageDx(),0.01);
        assertEquals(55.71, list.get(11).getAverageDx(),0.01);
        assertEquals(61.37, list.get(12).getAverageDx(),0.01);
        assertEquals(50.27, list.get(13).getAverageDx(),0.001);
        assertEquals(42.44,  list.get(14).getAverageDx(),0.01);
        assertEquals(58.77,  list.get(15).getAverageDx(),0.01);
        assertEquals(42.60,  list.get(16).getAverageDx(),0.01);
        assertEquals(36.65,  list.get(17).getAverageDx(),0.01);
        assertEquals(40.0,  list.get(18).getAverageDx(),0.01);
        assertEquals(0.0,  list.get(19).getAverageDx(),0.01);

        System.out.println(" test finished");
    }
}
