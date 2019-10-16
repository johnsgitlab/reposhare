package com.jpw.raptor.scrape.yahoostock;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by john on 10/30/18.
 */
public class RecommendationTest {

    @Test
    public void testit() {

        System.out.println("RecommendationTest " );

        TestData       td  = new TestData();
        Recommendation rec = new Recommendation();

        rec.parse(" ", td.recommendationRaw);

        assertEquals(11, rec.getStrongBuy());
        assertEquals(21, rec.getBuy());
        assertEquals(6,  rec.getHold());
        assertEquals(0,  rec.getSell());
        assertEquals(0,  rec.getStrongSell());
        assertEquals(38, rec.getRecommendations());

        /*
        rec.parse(td.recommendationRaw);
        System.out.println("strongBuy  " + rec.getStrongBuy());
        System.out.println("Buy        " + rec.getBuy());
        System.out.println("hold       " + rec.getHold());
        System.out.println("sell       " + rec.getSell());
        System.out.println("strongSell " + rec.getStrongSell());
        */
    }
}
