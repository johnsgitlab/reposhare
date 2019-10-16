package com.jpw.raptor.scrape.yahoostock;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by john on 10/31/18.
 */
public class SummaryProfileTest {


    @Test
    public void testit() {

        System.out.println("SummaryProfileTest " );

        TestData         td  = new TestData();
        SummaryProfile   rec = new SummaryProfile();

        /*
        */
        rec.parse(" ", td.summaryProfileRaw);
        assertEquals(true, rec.getSector().equalsIgnoreCase("Technology"));
        assertEquals(true, rec.getIndustry().equalsIgnoreCase("Consumer Electronics"));
        assertEquals(true, rec.getLongBusinessSummary().startsWith("Apple Inc. designs,"));

        /*
        rec.parse(td.summaryProfileRaw);
        System.out.println("sector " + rec.getSector());
        System.out.println("industry " + rec.getIndustry());
        System.out.println("longBusinessSummary " + rec.getLongBusinessSummary());

        */

    }

}
