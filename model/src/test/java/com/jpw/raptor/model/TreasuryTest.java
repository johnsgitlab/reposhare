package com.jpw.raptor.model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;
/**
 * Created by john on 12/6/18.
 */
public class TreasuryTest {


    @Test
    public void test01() {

        System.out.println("start");

        String [] array = {"2018-12-04","2.37","2.42","2.45","2.58","2.71","2.8","2.81","2.79","2.84","2.91","3.05","3.16"};

        Treasury de = new Treasury(array);
        System.out.println(de.getDate());
        assertEquals(de.getOneMonth(),2.37,0.001);
        assertEquals(de.getTwoMonths(),2.42,0.001);
        assertEquals(de.getThreeMonths(),2.45,0.001);
        assertEquals(de.getSixMonths(),2.58,0.001);
        assertEquals(de.getOneYear(),2.71,0.001);
        assertEquals(de.getTwoYears(),2.8,0.001);
        assertEquals(de.getThreeYears(),2.81,0.001);
        assertEquals(de.getFiveYears(),2.79,0.001);
        assertEquals(de.getSevenYears(),2.84,0.001);
        assertEquals(de.getTenYears(),2.91,0.001);
        assertEquals(de.getTwentyYears(),3.05,0.001);
        assertEquals(de.getThirtyYears(),3.16,0.001);

        System.out.println("end");

    }

}
