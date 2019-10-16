package com.jpw.raptor.model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;

/**
 * Created by john on 4/8/18.
 */
public class AveragesModelTest {

    @Test
    public void test01() {
        System.out.println("test Average model object");

        Date                date    = new Date();
        Averages             a       = new Averages();

        a.setSymbol("sym");
        a.setDate(date);
        a.setClose(1.1);
        a.setFractal(2.2);
        a.setKaufman(3.3);
        a.setSimple10(4.4);
        a.setSimple20(5.5);
        a.setSimple50(6.6);
        a.setSimple200(7.7);
        a.setVolume(8);
        a.setSimple5(9.9);
        a.setSimple100(10.10);

        assertTrue(a.getSymbol().equalsIgnoreCase("sym"));
        assertTrue(a.getDate().equals(date));
        assertEquals(a.getClose(), 1.1, 0.01);
        assertEquals(a.getFractal(), 2.2, 0.01);
        assertEquals(a.getKaufman(), 3.3, 0.01);
        assertEquals(a.getSimple10(), 4.4, 0.01);
        assertEquals(a.getSimple20(), 5.5, 0.01);
        assertEquals(a.getSimple50(), 6.6, 0.01);
        assertEquals(a.getSimple200(), 7.7, 0.01);
        assertEquals(a.getVolume(), 8);
        assertEquals(a.getSimple5(), 9.9, 0.01);
        assertEquals(a.getSimple100(), 10.10, 0.01);
    }
}
