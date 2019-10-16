package com.jpw.raptor.scrape.marketwatch;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by john on 3/24/18.
 */
public class MarketWatchPageContentsTest {

    @Test
    public void test01() {
        MarketWatchPageContents mpc = new MarketWatchPageContents();

        mpc.setConsistentReturn(1);
        mpc.setExpense(2);
        mpc.setFound(true);
        mpc.setPreservation(3);
        mpc.setTaxEfficiency(4);
        mpc.setTotalReturn(5);

        assertEquals(1, mpc.getConsistentReturn());
        assertEquals(2, mpc.getExpense());
        assertTrue(mpc.isFound());
        assertEquals(3, mpc.getPreservation());
        assertEquals(4, mpc.getTaxEfficiency());
        assertEquals(5, mpc.getTotalReturn());

    }



}
