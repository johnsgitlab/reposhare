package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.Rp;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RelativePerformanceTest {


    @Test
    public void test() throws ParseException {
        System.out.println(" test started");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Quote[] refArray = {
                new Quote("IVV", fmt.parse("2018-08-24"), 0, 0, 0, 285.79, 0),
                new Quote("IVV", fmt.parse("2018-08-22"), 0, 0, 0, 286.17, 0),
                new Quote("IVV", fmt.parse("2018-08-21"), 0, 0, 0, 286.34, 0),
                new Quote("IVV", fmt.parse("2018-08-20"), 0, 0, 0, 285.67, 0),
                new Quote("IVV", fmt.parse("2018-08-27"), 0, 0, 0, 285.06, 0),
                new Quote("IVV", fmt.parse("2018-08-16"), 0, 0, 0, 284.06, 0),
                new Quote("IVV", fmt.parse("2018-08-15"), 0, 0, 0, 281.78, 0),
                new Quote("IVV", fmt.parse("2018-08-14"), 0, 0, 0, 283.9, 0),
                new Quote("IVV", fmt.parse("2018-08-13"), 0, 0, 0, 282.1, 0),
                new Quote("IVV", fmt.parse("2018-08-10"), 0, 0, 0, 283.16, 0)
        };

        Quote[] equityArray = {
                new Quote("RSP", fmt.parse("2018-08-23"), 0, 0, 0, 106.07, 0),
                new Quote("RSP", fmt.parse("2018-08-22"), 0, 0, 0, 107.33, 0),
                new Quote("RSP", fmt.parse("2018-08-21"), 0, 0, 0, 108.56, 0),
                new Quote("RSP", fmt.parse("2018-08-20"), 0, 0, 0, 109.29, 0),
                new Quote("RSP", fmt.parse("2018-08-17"), 0, 0, 0, 105.79, 0),
                new Quote("RSP", fmt.parse("2018-08-16"), 0, 0, 0, 105.23, 0),
                new Quote("RSP", fmt.parse("2018-08-15"), 0, 0, 0, 104.4, 0),
                new Quote("RSP", fmt.parse("2018-08-14"), 0, 0, 0, 103.1, 0),
                new Quote("RSP", fmt.parse("2018-08-13"), 0, 0, 0, 102.22, 0),
        };

        List<Quote> refQuotes       = Arrays.asList(refArray);
        List<Quote> equityQuotes    = Arrays.asList(equityArray);

        RelativePerformance rp      = new RelativePerformance();
        List<Rp>            list    = rp.computeRelativePerformance(refQuotes, equityQuotes);


        DecimalFormat df     = new DecimalFormat("#.###");

        assertEquals( Double.parseDouble( df.format(107.33 / 286.17) ), list.get(0).getVal(), 0.0001 );
        assertEquals( Double.parseDouble( df.format(108.56 / 286.34) ), list.get(1).getVal(), 0.0001 );
        assertEquals( Double.parseDouble( df.format(109.29 / 285.67) ), list.get(2).getVal(), 0.0001 );
        assertEquals( Double.parseDouble( df.format(105.23 / 284.06) ), list.get(3).getVal(), 0.0001 );
        assertEquals( Double.parseDouble( df.format(104.4 / 281.78) ), list.get(4).getVal(), 0.0001 );
        assertEquals( Double.parseDouble( df.format(103.1 / 283.9) ), list.get(5).getVal(), 0.0001 );
        assertEquals( Double.parseDouble( df.format(102.22 / 282.1) ), list.get(6).getVal(), 0.0001 );
    }
}
