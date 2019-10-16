package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Obv;
import com.jpw.raptor.model.Quote;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 7/16/18.
 */
public class OnBalanceVolumeTest {

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
                new Quote("IVV", fmt.parse("2018-12-31"), 251.04, 251.63, 248.99, 251.61, 1011),
                new Quote("IVV", fmt.parse("2018-12-28"), 251.03, 252.88, 247.95, 249.33, 1085)
        };

        List<Quote> data = Arrays.asList( dataArray );

        long [] volumeChange = {4096, 5340, -5961, 6430, -5828, 5978,  1011, 1085 };

        long [] obvValue = {12151, 8055, 2715, 8676, 2246, 8074, 2096, 1085};

        long [] obvSma = {7640, 6482, 4545, 6332, 4138, 3751, 1590, 1085};

        OnBalanceVolume obv = new OnBalanceVolume();
        List<Obv> list = obv.generateObv(data, 3);

        for ( int i=0; i<list.size(); i++) {
            assertEquals(obvValue[i], list.get(i).getVal());
            assertEquals(obvSma[i], list.get(i).getSma());
        }

    }

}
