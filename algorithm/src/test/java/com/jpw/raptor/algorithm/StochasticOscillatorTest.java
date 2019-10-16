package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.Stochastic;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 3/9/18.
 */
public class StochasticOscillatorTest {

    @Test
    public void test() throws ParseException {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Quote[] dataArray = {
                new Quote("IVV", fmt.parse("2019-01-09"), 259.09, 260.48, 257.72, 259.49, 4096900),
                new Quote("IVV", fmt.parse("2019-01-08"), 258.38, 258.82, 255.52, 258.29, 5340600),
                new Quote("IVV", fmt.parse("2019-01-07"), 254.24, 257.46, 253.21, 255.77, 5961300),
                new Quote("IVV", fmt.parse("2019-01-04"), 249.06, 254.59, 248.64, 254.06, 6430100),
                new Quote("IVV", fmt.parse("2019-01-03"), 249.79, 250.03, 245.08, 245.43, 5828300),
                new Quote("IVV", fmt.parse("2019-01-02"), 247.54, 252.7, 247.42, 251.72, 5978400),
                new Quote("IVV", fmt.parse("2018-12-31"), 251.04, 251.63, 248.99, 251.61, 10117800),
                new Quote("IVV", fmt.parse("2018-12-28"), 251.03, 252.88, 247.95, 249.33, 10856300),
                new Quote("IVV", fmt.parse("2018-12-27"), 244.33, 250.07, 240.67, 250.06, 12693600),
                new Quote("IVV", fmt.parse("2018-12-26"), 237.73, 247.89, 235.46, 247.67, 13154100)
        };
        List<Quote> data = Arrays.asList(dataArray);

        // period 1
        // high 260.48; low 248.64; close 259.49
        // fastK 91.64;

        // period 2
        // high 258.82; low 245.08; close 258.29
        // fastK 96.14;

        // period 3
        // high 257.46; low 245.08; close 255.77
        // fastK 86.35;

        // period 4
        // high 254.59; low 245.08; close 254.06
        // fastK 94.43;

        // period 5
        // high 252.88; low 245.08; close 245.43
        // fastK 4.49;

        // period 6
        // high 252.88; low 240.67; close 251.72
        // fastK 90.50;

        // period 7
        // high 252.88; low 235.46; close 251.61
        // fastK 92.71;

        StochasticOscillator so         = new StochasticOscillator();
        List<Stochastic>     stoList    = so.generateStochastic(data, 4);

        /*
        System.out.println("");
        for ( Stochastic s : stoList ) {
            //System.out.println("K " + s.getKFast() + " D " + s.getDFast() + " : SK " + s.getKSlow() + " SD " + s.getDSlow());
            //System.out.println("Kfast " + s.getKFast());
            //System.out.println("Dfast " + s.getDFast() );
            //System.out.println("Kslow " + s.getKSlow());
            //System.out.println("Dslow " + s.getDSlow());
        }
        */

        assertEquals(91.64, stoList.get(0).getKFast(),0.001);
        assertEquals(91.38, stoList.get(0).getDFast(),0.001);
        assertEquals(91.38, stoList.get(0).getKSlow(),0.001);
        assertEquals(81.82, stoList.get(0).getDSlow(),0.001);

        assertEquals(96.14, stoList.get(1).getKFast(),0.001);
        assertEquals(92.31, stoList.get(1).getDFast(),0.001);
        assertEquals(92.31, stoList.get(1).getKSlow(),0.001);
        assertEquals(72.4, stoList.get(1).getDSlow(),0.001);

        assertEquals(86.35, stoList.get(2).getKFast(),0.001);
        assertEquals(61.76, stoList.get(2).getDFast(),0.001);
        assertEquals(61.76, stoList.get(2).getKSlow(),0.001);
        assertEquals(62.49, stoList.get(2).getDSlow(),0.001);

        assertEquals(94.43, stoList.get(3).getKFast(),0.001);
        assertEquals(63.14, stoList.get(3).getDFast(),0.001);
        assertEquals(63.14, stoList.get(3).getKSlow(),0.001);
        assertEquals(62.26, stoList.get(3).getDSlow(),0.001);

        assertEquals(4.49, stoList.get(4).getKFast(),0.001);
        assertEquals(62.57, stoList.get(4).getDFast(),0.001);
        assertEquals(62.57, stoList.get(4).getKSlow(),0.001);
        assertEquals(51.51, stoList.get(4).getDSlow(),0.001);

        assertEquals(90.5, stoList.get(5).getKFast(),0.001);
        assertEquals(61.07, stoList.get(5).getDFast(),0.001);
        assertEquals(61.07, stoList.get(5).getKSlow(),0.001);
        assertEquals(30.66, stoList.get(5).getDSlow(),0.001);

        assertEquals(92.71, stoList.get(6).getKFast(),0.001);
        assertEquals(30.9, stoList.get(6).getDFast(),0.001);
        assertEquals(30.9, stoList.get(6).getKSlow(),0.001);
        assertEquals(10.3, stoList.get(6).getDSlow(),0.001);

        assertEquals(0.0, stoList.get(7).getKFast(),0.001);
        assertEquals(0.0, stoList.get(7).getDFast(),0.001);
        assertEquals(0.0, stoList.get(7).getKSlow(),0.001);
        assertEquals(0.0, stoList.get(7).getDSlow(),0.001);

        assertEquals(0.0, stoList.get(8).getKFast(),0.001);
        assertEquals(0.0, stoList.get(8).getDFast(),0.001);
        assertEquals(0.0, stoList.get(8).getKSlow(),0.001);
        assertEquals(0.0, stoList.get(8).getDSlow(),0.001);

        assertEquals(0.0, stoList.get(9).getKFast(),0.001);
        assertEquals(0.0, stoList.get(9).getDFast(),0.001);
        assertEquals(0.0, stoList.get(9).getKSlow(),0.001);
        assertEquals(0.0, stoList.get(9).getDSlow(),0.001);

    }
}
