package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.model.EtfFundBase;
import com.jpw.raptor.model.Summary;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by john on 11/21/18.
 */
public class SummaryServiceTest {


    protected static Date          date1;
    protected static Date          date2;
    protected static EtfFundBase   er1 ;
    protected static EtfFundBase   er2;
    protected static EtfFundBase   er3;

    @BeforeClass
    public static void setup() {
        System.out.println("setup\n");

        er1 = new EtfFundBase();
        er2 = new EtfFundBase();
        er3 = new EtfFundBase();

        date1 = new Date();
        date2 = new Date(-1);

        er1.setDate(date1);
        er1.setAssetClass("a");
        er1.setFundType("b");
        er1.setFundSubType("c");
        er1.setFactor("d");
        er1.setYtd(1.0);
        er1.setOneDay(2.0);
        er1.setOneWeek(3.0);
        er1.setTwoWeeks(4.0);
        er1.setFourWeeks(5.0);
        er1.setThreeMonths(6.0);
        er1.setOneYear(7.0);

        er2.setDate(date1);
        er2.setAssetClass("a");
        er2.setFundType("b");
        er2.setFundSubType("c");
        er2.setFactor("d");
        er2.setYtd(2.0);
        er2.setOneDay(3.0);
        er2.setOneWeek(4.0);
        er2.setTwoWeeks(5.0);
        er2.setFourWeeks(6.0);
        er2.setThreeMonths(7.0);
        er2.setOneYear(8.0);

        er3.setDate(date2);
        er3.setAssetClass("e");
        er3.setFundType("f");
        er3.setFundSubType("g");
        er3.setFactor("h");
        er3.setYtd(3.0);
        er3.setOneDay(4.0);
        er3.setOneWeek(5.0);
        er3.setTwoWeeks(6.0);
        er3.setFourWeeks(7.0);
        er3.setThreeMonths(8.0);
        er3.setOneYear(9.0);
    }

    @Test
    public void test01() {
        /*
        System.out.println("test01");
        SummaryFactory sf = new SummaryFactory();

        assertTrue(sf.generateKey("", "", "", "").equalsIgnoreCase(";;;"));
        assertTrue(sf.generateKey("a", "b", "c", "d").equalsIgnoreCase("a;b;c;d"));
        assertTrue(sf.generateKey("e", "f", "g", "").equalsIgnoreCase("e;f;g;"));
        assertTrue(sf.generateKey("h", "i", "", "").equalsIgnoreCase("h;i;;"));
        assertTrue(sf.generateKey("j", "", "", "").equalsIgnoreCase("j;;;"));

        assertTrue(sf.generateKey("k", "l", "", "m").equalsIgnoreCase("k;l;;m"));
        assertTrue(sf.generateKey("n", "", "", "o").equalsIgnoreCase("n;;;o"));
        */
    }

    @Test
    public void test02() {

        /*
        System.out.println("test02\n");
        List<EtfFundBase> recs = Arrays.asList(er1, er2, er3);

        SummaryFactory sf = new SummaryFactory();
        List<Summary> data = sf.getSummaryData(recs, "etf");

        Summary r1 = data.get(0);

        assertEquals(2,r1.getCount());

        assertTrue(r1.getAssetClass().equalsIgnoreCase("a"));
        assertTrue(r1.getFundType().equalsIgnoreCase("b"));
        assertTrue(r1.getFundSubType().equalsIgnoreCase("c"));
        assertTrue(r1.getFactor().equalsIgnoreCase("d"));

        assertEquals(r1.getYtd(),(er1.getYtd()+er2.getYtd())/2.0, 0.001);
        assertEquals(r1.getOneDay(),(er1.getOneDay()+er2.getOneDay())/2.0, 0.001);
        assertEquals(r1.getOneWeek(),(er1.getOneWeek()+er2.getOneWeek())/2.0, 0.001);
        assertEquals(r1.getTwoWeeks(),(er1.getTwoWeeks()+er2.getTwoWeeks())/2.0, 0.001);
        assertEquals(r1.getFourWeeks(),(er1.getFourWeeks()+er2.getFourWeeks())/2.0, 0.001);
        assertEquals(r1.getThreeMonths(),(er1.getThreeMonths()+er2.getThreeMonths())/2.0, 0.001);
        assertEquals(r1.getOneYear(),(er1.getOneYear()+er2.getOneYear())/2.0, 0.001);

        Summary r2 = data.get(1);
        assertEquals(1,r2.getCount());

        assertTrue(r2.getAssetClass().equalsIgnoreCase("e"));
        assertTrue(r2.getFundType().equalsIgnoreCase("f"));
        assertTrue(r2.getFundSubType().equalsIgnoreCase("g"));
        assertTrue(r2.getFactor().equalsIgnoreCase("h"));

        assertEquals(r2.getYtd(),er3.getYtd(), 0.001);
        assertEquals(r2.getOneDay(),er3.getOneDay(), 0.001);
        assertEquals(r2.getOneWeek(),er3.getOneWeek(), 0.001);
        assertEquals(r2.getTwoWeeks(),er3.getTwoWeeks(), 0.001);
        assertEquals(r2.getFourWeeks(),er3.getFourWeeks(), 0.001);
        assertEquals(r2.getThreeMonths(),er3.getThreeMonths(), 0.001);
        assertEquals(r2.getOneYear(),er3.getOneYear(), 0.001);
        */
    }

}
