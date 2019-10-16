package com.jpw.raptor.model;

import org.junit.Test;

/**
 * Created by John on 11/10/2017.
 */
public class EquityToScrapeTest {


    @Test
    public void test01() {
        System.out.println("test 01 started");

        EquityToScrape etc = new EquityToScrape();
        etc.setSymbol("sym");
        etc.setType("typ");
        System.out.println(etc.getSymbol());
        System.out.println(etc.getType());
        System.out.println(etc.toString());
    }

}
