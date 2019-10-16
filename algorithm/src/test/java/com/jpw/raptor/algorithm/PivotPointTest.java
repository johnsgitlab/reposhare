package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Pp;
import com.jpw.raptor.model.Quote;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PivotPointTest {

    @Test
    public void test01() throws ParseException {

        System.out.println("test 01 started");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Quote[] testData = {
                new Quote("IVV", fmt.parse("2019-01-09"), 259.09, 260.48, 257.72, 259.49, 4096900),
                new Quote("IVV", fmt.parse("2019-01-08"), 258.38, 258.82, 255.52, 258.29, 5340600),
                new Quote("IVV", fmt.parse("2019-01-07"), 254.24, 257.46, 253.21, 255.77, 5961300),
        };

        List<Quote> data = Arrays.asList(testData);

        DecimalFormat df      = new DecimalFormat("#.##");

        double pivotPoint_0  = Double.valueOf(df.format( (258.82 + 255.52 + 258.29)/3.0 ) );

        double support0_1    = Double.valueOf(df.format( (pivotPoint_0 * 2.0) - 258.82 ) );
        double support0_2    = Double.valueOf(df.format( pivotPoint_0 - ( 258.82 - 255.52 ) ) );
        double support0_3    = Double.valueOf(df.format( pivotPoint_0 - 2 * ( ( 258.82 - 255.52 ) ) ) );

        double resistance0_1 = Double.valueOf(df.format( (pivotPoint_0 * 2.0) - 255.52 ) );
        double resistance0_2 = Double.valueOf(df.format( pivotPoint_0 + ( 258.82 - 255.52 ) ) );
        double resistance0_3 = Double.valueOf(df.format( pivotPoint_0 + 2 * ( ( 258.82 - 255.52 ) ) ) );


        double pivotPoint_1  = Double.valueOf(df.format( (257.46 + 253.21 + 255.77)/3.0 ) );

        double support1_1    = Double.valueOf(df.format( (pivotPoint_1 * 2.0) - 257.46 ) );
        double support1_2    = Double.valueOf(df.format( pivotPoint_1 - ( 257.46 - 253.21 ) ) );
        double support1_3    = Double.valueOf(df.format( pivotPoint_1 - 2 * ( ( 257.46 - 253.21 ) ) ) );

        double resistance1_1 = Double.valueOf(df.format( (pivotPoint_1 * 2.0) - 253.21 ) );
        double resistance1_2 = Double.valueOf(df.format( pivotPoint_1 + ( 257.46 - 253.21 ) ) );
        double resistance1_3 = Double.valueOf(df.format( pivotPoint_1 + 2 * ( ( 257.46 - 253.21 ) ) ) );

        PivotPoint factory = new PivotPoint();

        List<Pp> list = factory.generatePivotPoints(data);

        assertEquals ( pivotPoint_0,    list.get(0).getPivotPoint(),    0.001);
        assertEquals ( support0_1,      list.get(0).getSupport1(),    0.001);
        assertEquals ( support0_2,      list.get(0).getSupport2(),    0.001);
        assertEquals ( support0_3,      list.get(0).getSupport3(),    0.001);
        assertEquals ( resistance0_1,   list.get(0).getResistance1(),    0.001);
        assertEquals ( resistance0_2,   list.get(0).getResistance2(),    0.001);
        assertEquals ( resistance0_3,   list.get(0).getResistance3(),    0.001);

        assertEquals ( pivotPoint_1,    list.get(1).getPivotPoint(),    0.001);
        assertEquals ( support1_1,      list.get(1).getSupport1(),    0.001);
        assertEquals ( support1_2,      list.get(1).getSupport2(),    0.001);
        assertEquals ( support1_3,      list.get(1).getSupport3(),    0.001);
        assertEquals ( resistance1_1,   list.get(1).getResistance1(),    0.001);
        assertEquals ( resistance1_2,   list.get(1).getResistance2(),    0.001);
        assertEquals ( resistance1_3,   list.get(1).getResistance3(),    0.001);

        assertEquals ( 0.0,    list.get(2).getPivotPoint(),    0.001);
        assertEquals ( 0.0,    list.get(2).getSupport1(),    0.001);
        assertEquals ( 0.0,    list.get(2).getSupport2(),    0.001);
        assertEquals ( 0.0,    list.get(2).getSupport3(),    0.001);
        assertEquals ( 0.0,    list.get(2).getResistance1(),    0.001);
        assertEquals ( 0.0,    list.get(2).getResistance2(),    0.001);
        assertEquals ( 0.0,    list.get(2).getResistance3(),    0.001);


    }

}
