package com.jpw.raptor.model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;

/**
 * Created by john on 2/19/18.
 */
public class AverageTest {



    @Test
    public void test01() {
        System.out.println("test Average object");

        Date                date    = new Date();
        Average.AverageType avgType = Average.AverageType.SMA;
        Average             a       = new Average();

        a.setSymbol("sym");
        a.setDate(date);
        a.setAvgType(Average.AverageType.SMA);
        a.setDays(5);
        a.setVal(12.34);

        assertTrue(a.getSymbol().equalsIgnoreCase("sym"));
        assertTrue(a.getDate().equals(date));
        assertEquals(a.getAvgType(),avgType);
        assertEquals(a.getDays(),5);
        assertEquals(a.getVal(), 12.34, 0.001);
    }

}
