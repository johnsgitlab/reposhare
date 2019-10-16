package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Obv;
import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.Roc;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 9/10/18.
 */
public class RateOfChangeTest {

    public ArrayList<Quote> list;

    @Test
    public void test01() throws ParseException {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Quote[] dataArray = {
                new Quote("IVV", fmt.parse("2019-01-09"), 259.09, 260.48, 257.72, 259.49, 4096),
                new Quote("IVV", fmt.parse("2019-01-08"), 258.38, 258.82, 255.52, 258.29, 5340),
                new Quote("IVV", fmt.parse("2019-01-07"), 254.24, 257.46, 253.21, 252.77, 5961),
                new Quote("IVV", fmt.parse("2019-01-04"), 249.06, 254.59, 248.64, 254.06, 6430),
                new Quote("IVV", fmt.parse("2019-01-03"), 249.79, 250.03, 245.08, 245.43, 5828),
                new Quote("IVV", fmt.parse("2019-01-02"), 247.54, 252.7, 247.42, 251.72, 5978),
                new Quote("IVV", fmt.parse("2018-12-31"), 251.04, 251.63, 248.99, 251.61, 1011)
        };
        List<Quote> data = Arrays.asList( dataArray );

        DecimalFormat df  = new DecimalFormat("#.##");

        double roc0 = Double.valueOf(df.format(((259.49 - 245.43) / 245.43) * 100.0));
        double roc1 = Double.valueOf(df.format(((258.29 - 251.72) / 251.72) * 100.0));
        double roc2 = Double.valueOf(df.format(((252.77 - 251.61) / 251.61) * 100.0));

        RateOfChange roc = new RateOfChange();
        List<Roc> list = roc.generateRocList(data, 4);

        assertEquals(roc0, list.get(0).getValue(), 0.001);
        assertEquals(roc1, list.get(1).getValue(), 0.001);
        assertEquals(roc2, list.get(2).getValue(), 0.001);
        assertEquals(0.0, list.get(3).getValue(), 0.001);
        assertEquals(0.0, list.get(4).getValue(), 0.001);
        assertEquals(0.0, list.get(5).getValue(), 0.001);
        assertEquals(0.0, list.get(6).getValue(), 0.001);


    }

}
