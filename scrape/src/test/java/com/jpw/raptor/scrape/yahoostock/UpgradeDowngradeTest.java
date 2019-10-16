package com.jpw.raptor.scrape.yahoostock;

import com.jpw.raptor.scrape.yahoostock.fields.UpgradeDowngradeObj;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by john on 10/31/18.
 */
public class UpgradeDowngradeTest {

    @Test
    public void testit() {

        System.out.println("UpgradeDowngradeTest " );

        TestData         td  = new TestData();
        UpgradeDowngrade rec = new UpgradeDowngrade();

        /*
        */
        UpgradeDowngradeObj[] array = rec.parse(" ", td.upgradeDowngradeHistoryRaw);

        assertEquals(123, array.length);

        /*

        UpgradeDowngradeObj[] array = rec.parse(td.upgradeDowngradeHistoryRaw);
        for (  UpgradeDowngradeObj r  : array ) {
            System.out.println(r.getFirm() + " | " + r.getToGrade() + " | " + r.getFromGrade() + " | " + r.getAction());
        }
        System.out.println("size " + array.length );
        */
    }
}
