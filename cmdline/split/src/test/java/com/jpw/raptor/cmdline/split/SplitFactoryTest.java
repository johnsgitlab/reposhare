package com.jpw.raptor.cmdline.split;

import com.jpw.raptor.model.EtfFundBase;
import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.Summary;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
/**
 * Created by john on 3/31/18.
 */
public class SplitFactoryTest {


    @Test
    public void test01() {
        System.out.println("start test01");

        Quote  q1 = new Quote ("q1", new Date(), 10, 20, 5, 12, 1000);
        Quote  q2 = new Quote ("q2", new Date(), 15, 22, 10, 17, 1005);
        Quote  q3 = new Quote ("q3", new Date(), 10, 20, 5, 12, 1000);
        Quote  q4 = new Quote ("q4", new Date(), 15, 22, 10, 17, 1005);

        SplitFactory sf = new SplitFactory();

        List<Quote> list2to1 = new ArrayList<Quote>();
        list2to1.add(q1);
        list2to1.add(q2);
        sf.adjust(list2to1, 2.0);
        assertEquals(5.0, list2to1.get(0).getOpen(), 0.001);
        assertEquals(10.0, list2to1.get(0).getHigh(), 0.001);
        assertEquals(2.5, list2to1.get(0).getLow(), 0.001);
        assertEquals(6.0, list2to1.get(0).getClose(), 0.001);
        assertEquals(2000, list2to1.get(0).getVolume());

        List<Quote> list1to2 = new ArrayList<Quote>();
        list1to2.add(q3);
        list1to2.add(q4);
        sf.adjust(list1to2, 0.5);
        assertEquals(20.0, list1to2.get(0).getOpen(), 0.001);
        assertEquals(40.0, list1to2.get(0).getHigh(), 0.001);
        assertEquals(10.0, list1to2.get(0).getLow(), 0.001);
        assertEquals(24.0, list1to2.get(0).getClose(), 0.001);
        assertEquals(500, list1to2.get(0).getVolume());

        System.out.println("end test01");
    }

}
