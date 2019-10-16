package com.jpw.raptor.algorithm.signals;

import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Created by john on 6/20/18.
 */
public class StochasticSignalFactoryTest {


    @Test
    public void test() {

        DecimalFormat myFormatter = new DecimalFormat("000");

        System.out.println("|" + myFormatter.format(2.111) + "|" );

    }
}