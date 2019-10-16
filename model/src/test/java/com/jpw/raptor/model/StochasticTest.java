package com.jpw.raptor.model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;

/**
 * Created by john on 2/19/18.
 */
public class StochasticTest {



    @Test
    public void test01() {
        System.out.println("test Stochastic object");

        Date       date = new Date();
        Stochastic a    = new Stochastic();

        a.setSymbol("sym");
        a.setDate(date);
        a.setKSlow(12.34);
        a.setDSlow(56.78);

        a.setKFast(82.34);
        a.setDFast(96.78);

        assertTrue(a.getSymbol().equalsIgnoreCase("sym"));
        assertTrue(a.getDate().equals(date));

        assertEquals(a.getKSlow(), 12.34, 0.001);
        assertEquals(a.getDSlow(), 56.78, 0.001);

        assertEquals(a.getKFast(), 82.34, 0.001);
        assertEquals(a.getDFast(), 96.78, 0.001);
    }

}
