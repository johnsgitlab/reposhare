package com.jpw.raptor.model;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HiYieldSpreadTest {

    protected Date date;
    protected double spread;


    @Test
    public void test01() throws ParseException {

        String[] a1 = "DATE,BAMLH0A0HYM2".split(",");
        String[] a2 = "2014-07-29,3.8".split(",");
        String[] a3 = "2014-12-25,".split(",");

        HiYieldSpread v1 = new HiYieldSpread(a1);
        HiYieldSpread v2 = new HiYieldSpread(a2);
        HiYieldSpread v3 = new HiYieldSpread(a3);

        assertEquals(-9999.0, v1.getSpread(), 0.01);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(3.8, v2.getSpread(), 0.01);
        assertEquals(sdf.parse("2014-07-29"), v2.getDate());

        assertEquals(-9999.0, v3.getSpread(), 0.01);
    }

}
