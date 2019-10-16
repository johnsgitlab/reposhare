package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Performance;
import com.jpw.raptor.model.Quote;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by john on 7/8/18.
 */
public class EquityPerformanceTest {

    public ArrayList<Quote> list;

    @Test
    public void test01() {
        System.out.println("test 01 started");


        // -2.13 1 day return ( 258.05 - 263.67) / 263.67
        // -4.60 1 week return ( 258.05 - 274.2) / 274.2
        // -7.35 2 week return ( 258.05 - 278.87) / 278.87
        // -7.14 4 week return ( 258.05 - 270.4) / 270.4
        // -3.54 3 month return ( 258.05 - 267.58) / 267.58
        // -3.3  ytd return ( 258.05 - 266.86) / 266.86
        //  9.56 1 year return ( 258.05 - 233.01) / 233.01

        EquityPerformance  pf  = new EquityPerformance();
        Performance        rec = new Performance();

        rec = pf.computePerformance("symbol", list);
        assertEquals(-2.13, rec.getOneDay(), 0.001);
        assertEquals(-5.89, rec.getOneWeek(), 0.001);
        assertEquals(-7.47, rec.getTwoWeeks(), 0.001);
        assertEquals(-4.57, rec.getFourWeeks(), 0.001);
        assertEquals(-3.56, rec.getThreeMonths(), 0.001);
        assertEquals(-3.30, rec.getYtd(), 0.001);

        System.out.println("test 01 ended");

    }


    @Before
    public void createData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        list = new ArrayList<Quote>(255);

        list.add( new Quote("SPY",sdf.parse("20180323"),0.0,0.0,0.0, 258.05, 0) );	// 1
        list.add( new Quote("SPY",sdf.parse("20180322"),0.0,0.0,0.0, 263.67, 0) );	// 2 one day
        list.add( new Quote("SPY",sdf.parse("20180321"),0.0,0.0,0.0, 270.43, 0) );	// 3
        list.add( new Quote("SPY",sdf.parse("20180320"),0.0,0.0,0.0, 270.95, 0) );	// 4
        list.add( new Quote("SPY",sdf.parse("20180319"),0.0,0.0,0.0, 270.49, 0) );	// 5
        list.add( new Quote("SPY",sdf.parse("20180316"),0.0,0.0,0.0, 274.2, 0) );	// 6 one week
        list.add( new Quote("SPY",sdf.parse("20180315"),0.0,0.0,0.0, 275, 0) );	    // 7
        list.add( new Quote("SPY",sdf.parse("20180314"),0.0,0.0,0.0, 275.3, 0) );	// 8
        list.add( new Quote("SPY",sdf.parse("20180313"),0.0,0.0,0.0, 276.72, 0) );	// 9
        list.add( new Quote("SPY",sdf.parse("20180312"),0.0,0.0,0.0, 278.52, 0) );	// 10
        list.add( new Quote("SPY",sdf.parse("20180309"),0.0,0.0,0.0, 278.87, 0) );	// 11 two weeks
        list.add( new Quote("SPY",sdf.parse("20180308"),0.0,0.0,0.0, 274.1, 0) );	// 12
        list.add( new Quote("SPY",sdf.parse("20180307"),0.0,0.0,0.0, 272.78, 0) );	// 13
        list.add( new Quote("SPY",sdf.parse("20180306"),0.0,0.0,0.0, 272.88, 0) );	// 14
        list.add( new Quote("SPY",sdf.parse("20180305"),0.0,0.0,0.0, 272.19, 0) );	// 15
        list.add( new Quote("SPY",sdf.parse("20180302"),0.0,0.0,0.0, 269.08, 0) );	// 16 three weeks
        list.add( new Quote("SPY",sdf.parse("20180301"),0.0,0.0,0.0, 267.7, 0) );	// 17
        list.add( new Quote("SPY",sdf.parse("20180228"),0.0,0.0,0.0, 271.65, 0) );	// 18
        list.add( new Quote("SPY",sdf.parse("20180227"),0.0,0.0,0.0, 274.43, 0) );	// 19
        list.add( new Quote("SPY",sdf.parse("20180226"),0.0,0.0,0.0, 277.9, 0) );	// 20
        list.add( new Quote("SPY",sdf.parse("20180223"),0.0,0.0,0.0, 274.71, 0) );	// 21
        list.add( new Quote("SPY",sdf.parse("20180222"),0.0,0.0,0.0, 270.4, 0) );	// 22 four weeks
        list.add( new Quote("SPY",sdf.parse("20180221"),0.0,0.0,0.0, 270.05, 0) );	// 23
        list.add( new Quote("SPY",sdf.parse("20180220"),0.0,0.0,0.0, 271.4, 0) );	// 24
        list.add( new Quote("SPY",sdf.parse("20180216"),0.0,0.0,0.0, 273.11, 0) );	// 25
        list.add( new Quote("SPY",sdf.parse("20180215"),0.0,0.0,0.0, 273.03, 0) );	// 26
        list.add( new Quote("SPY",sdf.parse("20180214"),0.0,0.0,0.0, 269.5, 0) );	// 27
        list.add( new Quote("SPY",sdf.parse("20180213"),0.0,0.0,0.0, 266, 0) );	    // 28
        list.add( new Quote("SPY",sdf.parse("20180212"),0.0,0.0,0.0, 265.34, 0) );	// 29
        list.add( new Quote("SPY",sdf.parse("20180209"),0.0,0.0,0.0, 261.5, 0) );	// 30
        list.add( new Quote("SPY",sdf.parse("20180208"),0.0,0.0,0.0, 257.63, 0) );	// 31
        list.add( new Quote("SPY",sdf.parse("20180207"),0.0,0.0,0.0, 267.67, 0) );	// 32
        list.add( new Quote("SPY",sdf.parse("20180206"),0.0,0.0,0.0, 269.13, 0) );	// 33
        list.add( new Quote("SPY",sdf.parse("20180205"),0.0,0.0,0.0, 263.93, 0) );	// 34
        list.add( new Quote("SPY",sdf.parse("20180202"),0.0,0.0,0.0, 275.45, 0) );	// 35
        list.add( new Quote("SPY",sdf.parse("20180201"),0.0,0.0,0.0, 281.58, 0) );	// 36
        list.add( new Quote("SPY",sdf.parse("20180131"),0.0,0.0,0.0, 281.9, 0) );	// 37
        list.add( new Quote("SPY",sdf.parse("20180130"),0.0,0.0,0.0, 281.76, 0) );	// 38
        list.add( new Quote("SPY",sdf.parse("20180129"),0.0,0.0,0.0, 284.68, 0) );	// 39
        list.add( new Quote("SPY",sdf.parse("20180126"),0.0,0.0,0.0, 286.58, 0) );	// 40
        list.add( new Quote("SPY",sdf.parse("20180125"),0.0,0.0,0.0, 283.3, 0) );	// 41
        list.add( new Quote("SPY",sdf.parse("20180124"),0.0,0.0,0.0, 283.18, 0) );	// 42
        list.add( new Quote("SPY",sdf.parse("20180123"),0.0,0.0,0.0, 283.29, 0) );	// 43 two months
        list.add( new Quote("SPY",sdf.parse("20180122"),0.0,0.0,0.0, 282.69, 0) );	// 44
        list.add( new Quote("SPY",sdf.parse("20180119"),0.0,0.0,0.0, 280.41, 0) );	// 45
        list.add( new Quote("SPY",sdf.parse("20180118"),0.0,0.0,0.0, 279.14, 0) );	// 46
        list.add( new Quote("SPY",sdf.parse("20180117"),0.0,0.0,0.0, 279.61, 0) );	// 47
        list.add( new Quote("SPY",sdf.parse("20180116"),0.0,0.0,0.0, 276.97, 0) );	// 48
        list.add( new Quote("SPY",sdf.parse("20180112"),0.0,0.0,0.0, 277.92, 0) );	// 49
        list.add( new Quote("SPY",sdf.parse("20180111"),0.0,0.0,0.0, 276.12, 0) );	// 50
        list.add( new Quote("SPY",sdf.parse("20180110"),0.0,0.0,0.0, 274.12, 0) );	// 51
        list.add( new Quote("SPY",sdf.parse("20180109"),0.0,0.0,0.0, 274.54, 0) );	// 52
        list.add( new Quote("SPY",sdf.parse("20180108"),0.0,0.0,0.0, 273.92, 0) );	// 53
        list.add( new Quote("SPY",sdf.parse("20180105"),0.0,0.0,0.0, 273.42, 0) );	// 54
        list.add( new Quote("SPY",sdf.parse("20180104"),0.0,0.0,0.0, 271.61, 0) );	// 55
        list.add( new Quote("SPY",sdf.parse("20180103"),0.0,0.0,0.0, 270.47, 0) );	// 56
        list.add( new Quote("SPY",sdf.parse("20180102"),267.84,0.0,0.0, 268.77, 0) ); // 57
        list.add( new Quote("SPY",sdf.parse("20171229"),0.0,0.0,0.0, 266.86, 0) );    // 58
        list.add( new Quote("SPY",sdf.parse("20171228"),0.0,0.0,0.0, 267.87, 0) );    // 59
        list.add( new Quote("SPY",sdf.parse("20171227"),0.0,0.0,0.0, 267.32, 0) );    // 60
        list.add( new Quote("SPY",sdf.parse("20171226"),0.0,0.0,0.0, 267.19, 0) );    // 61
        list.add( new Quote("SPY",sdf.parse("20171225"),0.0,0.0,0.0, 267.54, 0) );    // 62
        list.add( new Quote("SPY",sdf.parse("20171222"),0.0,0.0,0.0, 267.51, 0) );    // 63
        list.add( new Quote("SPY",sdf.parse("20171221"),0.0,0.0,0.0, 267.58, 0) );    // 64 three months
        list.add( new Quote("SPY",sdf.parse("20171220"),0.0,0.0,0.0, 267.03, 0) );    // 65
        list.add( new Quote("SPY",sdf.parse("20171219"),0.0,0.0,0.0, 267.17, 0) );    // 66
        list.add( new Quote("SPY",sdf.parse("20171218"),0.0,0.0,0.0, 268.2, 0) );    // 67
        list.add( new Quote("SPY",sdf.parse("20171215"),0.0,0.0,0.0, 266.51, 0) );    // 68
        list.add( new Quote("SPY",sdf.parse("20171214"),0.0,0.0,0.0, 265.66, 0) );    // 69
        list.add( new Quote("SPY",sdf.parse("20171213"),0.0,0.0,0.0, 266.75, 0) );    // 70
        list.add( new Quote("SPY",sdf.parse("20171212"),0.0,0.0,0.0, 266.78, 0) );    // 71
        list.add( new Quote("SPY",sdf.parse("20171211"),0.0,0.0,0.0, 266.31, 0) );    // 72
        list.add( new Quote("SPY",sdf.parse("20171208"),0.0,0.0,0.0, 265.51, 0) );    // 73
        list.add( new Quote("SPY",sdf.parse("20171207"),0.0,0.0,0.0, 264.07, 0) );    // 74
        list.add( new Quote("SPY",sdf.parse("20171206"),0.0,0.0,0.0, 263.24, 0) );    // 75
        list.add( new Quote("SPY",sdf.parse("20171205"),0.0,0.0,0.0, 263.19, 0) );    // 76
        list.add( new Quote("SPY",sdf.parse("20171204"),0.0,0.0,0.0, 264.14, 0) );    // 77
        list.add( new Quote("SPY",sdf.parse("20171201"),0.0,0.0,0.0, 264.46, 0) );    // 78
        list.add( new Quote("SPY",sdf.parse("20171130"),0.0,0.0,0.0, 265.01, 0) );    // 79
        list.add( new Quote("SPY",sdf.parse("20171129"),0.0,0.0,0.0, 262.71, 0) );    // 80
        list.add( new Quote("SPY",sdf.parse("20171128"),0.0,0.0,0.0, 262.87, 0) );    // 81
        list.add( new Quote("SPY",sdf.parse("20171127"),0.0,0.0,0.0, 260.23, 0) );    // 82
        list.add( new Quote("SPY",sdf.parse("20171124"),0.0,0.0,0.0, 260.36, 0) );    // 83
        list.add( new Quote("SPY",sdf.parse("20171122"),0.0,0.0,0.0, 259.76, 0) );    // 84
        list.add( new Quote("SPY",sdf.parse("20171121"),0.0,0.0,0.0, 259.99, 0) );    // 85 four months
        list.add( new Quote("SPY",sdf.parse("20171120"),0.0,0.0,0.0, 258.3, 0) );    // 86
        list.add( new Quote("SPY",sdf.parse("20171117"),0.0,0.0,0.0, 257.86, 0) );    // 87
        list.add( new Quote("SPY",sdf.parse("20171116"),0.0,0.0,0.0, 258.62, 0) );    // 88
        list.add( new Quote("SPY",sdf.parse("20171115"),0.0,0.0,0.0, 256.44, 0) );    // 89
        list.add( new Quote("SPY",sdf.parse("20171114"),0.0,0.0,0.0, 257.73, 0) );    // 90
        list.add( new Quote("SPY",sdf.parse("20171113"),0.0,0.0,0.0, 258.33, 0) );    // 91
        list.add( new Quote("SPY",sdf.parse("20171110"),0.0,0.0,0.0, 258.09, 0) );    // 92
        list.add( new Quote("SPY",sdf.parse("20171109"),0.0,0.0,0.0, 258.17, 0) );    // 93
        list.add( new Quote("SPY",sdf.parse("20171108"),0.0,0.0,0.0, 259.11, 0) );    // 94
        list.add( new Quote("SPY",sdf.parse("20171107"),0.0,0.0,0.0, 258.67, 0) );    // 95
        list.add( new Quote("SPY",sdf.parse("20171106"),0.0,0.0,0.0, 258.85, 0) );    // 96
        list.add( new Quote("SPY",sdf.parse("20171103"),0.0,0.0,0.0, 258.45, 0) );    // 97
        list.add( new Quote("SPY",sdf.parse("20171102"),0.0,0.0,0.0, 257.59, 0) );    // 98
        list.add( new Quote("SPY",sdf.parse("20171101"),0.0,0.0,0.0, 257.49, 0) );    // 99
        list.add( new Quote("SPY",sdf.parse("20171031"),0.0,0.0,0.0, 257.15, 0) );    // 100
        list.add( new Quote("SPY",sdf.parse("20171030"),0.0,0.0,0.0, 256.75, 0) );    // 101
        list.add( new Quote("SPY",sdf.parse("20171027"),0.0,0.0,0.0, 257.71, 0) );    // 102
        list.add( new Quote("SPY",sdf.parse("20171026"),0.0,0.0,0.0, 255.62, 0) );    // 103
        list.add( new Quote("SPY",sdf.parse("20171025"),0.0,0.0,0.0, 255.29, 0) );    // 104
        list.add( new Quote("SPY",sdf.parse("20171024"),0.0,0.0,0.0, 256.56, 0) );    // 105
        list.add( new Quote("SPY",sdf.parse("20171023"),0.0,0.0,0.0, 256.11, 0) );    // 106 five months
        list.add( new Quote("SPY",sdf.parse("20171020"),0.0,0.0,0.0, 257.11, 0) );    // 107
        list.add( new Quote("SPY",sdf.parse("20171019"),0.0,0.0,0.0, 255.79, 0) );    // 108
        list.add( new Quote("SPY",sdf.parse("20171018"),0.0,0.0,0.0, 255.72, 0) );    // 109
        list.add( new Quote("SPY",sdf.parse("20171017"),0.0,0.0,0.0, 255.47, 0) );    // 110
        list.add( new Quote("SPY",sdf.parse("20171016"),0.0,0.0,0.0, 255.29, 0) );    // 111
        list.add( new Quote("SPY",sdf.parse("20171013"),0.0,0.0,0.0, 254.95, 0) );    // 112
        list.add( new Quote("SPY",sdf.parse("20171012"),0.0,0.0,0.0, 254.64, 0) );    // 113
        list.add( new Quote("SPY",sdf.parse("20171011"),0.0,0.0,0.0, 255.02, 0) );    // 114
        list.add( new Quote("SPY",sdf.parse("20171010"),0.0,0.0,0.0, 254.62, 0) );    // 115
        list.add( new Quote("SPY",sdf.parse("20171009"),0.0,0.0,0.0, 253.95, 0) );    // 116
        list.add( new Quote("SPY",sdf.parse("20171006"),0.0,0.0,0.0, 254.37, 0) );    // 117
        list.add( new Quote("SPY",sdf.parse("20171005"),0.0,0.0,0.0, 254.66, 0) );    // 118
        list.add( new Quote("SPY",sdf.parse("20171004"),0.0,0.0,0.0, 253.16, 0) );    // 119
        list.add( new Quote("SPY",sdf.parse("20171003"),0.0,0.0,0.0, 252.86, 0) );    // 120
        list.add( new Quote("SPY",sdf.parse("20171002"),0.0,0.0,0.0, 252.32, 0) );    // 121
        list.add( new Quote("SPY",sdf.parse("20170929"),0.0,0.0,0.0, 251.23, 0) );    // 122
        list.add( new Quote("SPY",sdf.parse("20170928"),0.0,0.0,0.0, 250.35, 0) );    // 123
        list.add( new Quote("SPY",sdf.parse("20170927"),0.0,0.0,0.0, 250.05, 0) );    // 124
        list.add( new Quote("SPY",sdf.parse("20170926"),0.0,0.0,0.0, 249.08, 0) );    // 125
        list.add( new Quote("SPY",sdf.parse("20170925"),0.0,0.0,0.0, 248.93, 0) );    // 126
        list.add( new Quote("SPY",sdf.parse("20170922"),0.0,0.0,0.0, 249.44, 0) );    // 127 six months
        list.add( new Quote("SPY",sdf.parse("20170921"),0.0,0.0,0.0, 249.39, 0) );    // 128
        list.add( new Quote("SPY",sdf.parse("20170920"),0.0,0.0,0.0, 250.06, 0) );    // 129
        list.add( new Quote("SPY",sdf.parse("20170919"),0.0,0.0,0.0, 249.97, 0) );    // 130
        list.add( new Quote("SPY",sdf.parse("20170918"),0.0,0.0,0.0, 249.72, 0) );    // 131
        list.add( new Quote("SPY",sdf.parse("20170915"),0.0,0.0,0.0, 249.19, 0) );    // 132
        list.add( new Quote("SPY",sdf.parse("20170914"),0.0,0.0,0.0, 250.09, 0) );    // 133
        list.add( new Quote("SPY",sdf.parse("20170913"),0.0,0.0,0.0, 250.17, 0) );    // 134
        list.add( new Quote("SPY",sdf.parse("20170912"),0.0,0.0,0.0, 250.05, 0) );    // 135
        list.add( new Quote("SPY",sdf.parse("20170911"),0.0,0.0,0.0, 249.21, 0) );    // 136
        list.add( new Quote("SPY",sdf.parse("20170908"),0.0,0.0,0.0, 246.58, 0) );    // 137
        list.add( new Quote("SPY",sdf.parse("20170907"),0.0,0.0,0.0, 246.87, 0) );    // 138
        list.add( new Quote("SPY",sdf.parse("20170906"),0.0,0.0,0.0, 246.9, 0) );    // 139
        list.add( new Quote("SPY",sdf.parse("20170905"),0.0,0.0,0.0, 246.06, 0) );    // 140
        list.add( new Quote("SPY",sdf.parse("20170901"),0.0,0.0,0.0, 247.84, 0) );    // 141
        list.add( new Quote("SPY",sdf.parse("20170831"),0.0,0.0,0.0, 247.49, 0) );    // 142
        list.add( new Quote("SPY",sdf.parse("20170830"),0.0,0.0,0.0, 246.01, 0) );    // 143
        list.add( new Quote("SPY",sdf.parse("20170829"),0.0,0.0,0.0, 244.85, 0) );    // 144
        list.add( new Quote("SPY",sdf.parse("20170828"),0.0,0.0,0.0, 244.57, 0) );    // 145
        list.add( new Quote("SPY",sdf.parse("20170825"),0.0,0.0,0.0, 244.56, 0) );    // 146
        list.add( new Quote("SPY",sdf.parse("20170824"),0.0,0.0,0.0, 243.99, 0) );    // 147
        list.add( new Quote("SPY",sdf.parse("20170823"),0.0,0.0,0.0, 244.56, 0) );    // 148
        list.add( new Quote("SPY",sdf.parse("20170822"),0.0,0.0,0.0, 245.44, 0) );    // 149
        list.add( new Quote("SPY",sdf.parse("20170821"),0.0,0.0,0.0, 242.9, 0) );    // 150
        list.add( new Quote("SPY",sdf.parse("20170818"),0.0,0.0,0.0, 242.71, 0) );    // 151
        list.add( new Quote("SPY",sdf.parse("20170817"),0.0,0.0,0.0, 243.09, 0) );    // 152
        list.add( new Quote("SPY",sdf.parse("20170816"),0.0,0.0,0.0, 246.94, 0) );    // 153
        list.add( new Quote("SPY",sdf.parse("20170815"),0.0,0.0,0.0, 246.51, 0) );    // 154
        list.add( new Quote("SPY",sdf.parse("20170814"),0.0,0.0,0.0, 246.54, 0) );    // 155
        list.add( new Quote("SPY",sdf.parse("20170811"),0.0,0.0,0.0, 244.12, 0) );    // 156
        list.add( new Quote("SPY",sdf.parse("20170810"),0.0,0.0,0.0, 243.76, 0) );    // 157
        list.add( new Quote("SPY",sdf.parse("20170809"),0.0,0.0,0.0, 247.25, 0) );    // 158
        list.add( new Quote("SPY",sdf.parse("20170808"),0.0,0.0,0.0, 247.26, 0) );    // 159
        list.add( new Quote("SPY",sdf.parse("20170807"),0.0,0.0,0.0, 247.87, 0) );    // 160
        list.add( new Quote("SPY",sdf.parse("20170804"),0.0,0.0,0.0, 247.41, 0) );    // 161
        list.add( new Quote("SPY",sdf.parse("20170803"),0.0,0.0,0.0, 246.96, 0) );    // 162
        list.add( new Quote("SPY",sdf.parse("20170802"),0.0,0.0,0.0, 247.44, 0) );    // 163
        list.add( new Quote("SPY",sdf.parse("20170801"),0.0,0.0,0.0, 247.32, 0) );    // 164
        list.add( new Quote("SPY",sdf.parse("20170731"),0.0,0.0,0.0, 246.77, 0) );    // 165
        list.add( new Quote("SPY",sdf.parse("20170728"),0.0,0.0,0.0, 246.91, 0) );    // 166
        list.add( new Quote("SPY",sdf.parse("20170727"),0.0,0.0,0.0, 247.2, 0) );    // 167
        list.add( new Quote("SPY",sdf.parse("20170726"),0.0,0.0,0.0, 247.43, 0) );    // 168
        list.add( new Quote("SPY",sdf.parse("20170725"),0.0,0.0,0.0, 247.42, 0) );    // 169
        list.add( new Quote("SPY",sdf.parse("20170724"),0.0,0.0,0.0, 246.82, 0) );    // 170
        list.add( new Quote("SPY",sdf.parse("20170721"),0.0,0.0,0.0, 246.88, 0) );    // 171
        list.add( new Quote("SPY",sdf.parse("20170720"),0.0,0.0,0.0, 247.1, 0) );    // 172
        list.add( new Quote("SPY",sdf.parse("20170719"),0.0,0.0,0.0, 246.99, 0) );    // 173
        list.add( new Quote("SPY",sdf.parse("20170718"),0.0,0.0,0.0, 245.66, 0) );    // 174
        list.add( new Quote("SPY",sdf.parse("20170717"),0.0,0.0,0.0, 245.53, 0) );    // 175
        list.add( new Quote("SPY",sdf.parse("20170714"),0.0,0.0,0.0, 245.56, 0) );    // 176
        list.add( new Quote("SPY",sdf.parse("20170713"),0.0,0.0,0.0, 244.42, 0) );    // 177
        list.add( new Quote("SPY",sdf.parse("20170712"),0.0,0.0,0.0, 244.01, 0) );    // 178
        list.add( new Quote("SPY",sdf.parse("20170710"),0.0,0.0,0.0, 242.37, 0) );    // 179
        list.add( new Quote("SPY",sdf.parse("20170707"),0.0,0.0,0.0, 242.11, 0) );    // 180
        list.add( new Quote("SPY",sdf.parse("20170706"),0.0,0.0,0.0, 240.55, 0) );    // 181
        list.add( new Quote("SPY",sdf.parse("20170705"),0.0,0.0,0.0, 242.77, 0) );    // 182
        list.add( new Quote("SPY",sdf.parse("20170703"),0.0,0.0,0.0, 242.21, 0) );    // 183
        list.add( new Quote("SPY",sdf.parse("20170630"),0.0,0.0,0.0, 241.8, 0) );    // 184
        list.add( new Quote("SPY",sdf.parse("20170629"),0.0,0.0,0.0, 241.35, 0) );    // 185
        list.add( new Quote("SPY",sdf.parse("20170628"),0.0,0.0,0.0, 243.49, 0) );    // 186
        list.add( new Quote("SPY",sdf.parse("20170627"),0.0,0.0,0.0, 241.33, 0) );    // 187
        list.add( new Quote("SPY",sdf.parse("20170626"),0.0,0.0,0.0, 243.29, 0) );    // 188
        list.add( new Quote("SPY",sdf.parse("20170623"),0.0,0.0,0.0, 243.13, 0) );    // 189
        list.add( new Quote("SPY",sdf.parse("20170622"),0.0,0.0,0.0, 242.84, 0) );    // 190
        list.add( new Quote("SPY",sdf.parse("20170621"),0.0,0.0,0.0, 242.95, 0) );    // 191
        list.add( new Quote("SPY",sdf.parse("20170620"),0.0,0.0,0.0, 243.01, 0) );    // 192
        list.add( new Quote("SPY",sdf.parse("20170619"),0.0,0.0,0.0, 244.66, 0) );    // 193
        list.add( new Quote("SPY",sdf.parse("20170616"),0.0,0.0,0.0, 242.64, 0) );    // 194
        list.add( new Quote("SPY",sdf.parse("20170615"),0.0,0.0,0.0, 243.77, 0) );    // 195
        list.add( new Quote("SPY",sdf.parse("20170614"),0.0,0.0,0.0, 244.24, 0) );    // 196
        list.add( new Quote("SPY",sdf.parse("20170613"),0.0,0.0,0.0, 244.55, 0) );    // 197
        list.add( new Quote("SPY",sdf.parse("20170612"),0.0,0.0,0.0, 243.36, 0) );    // 198
        list.add( new Quote("SPY",sdf.parse("20170609"),0.0,0.0,0.0, 243.41, 0) );    // 199
        list.add( new Quote("SPY",sdf.parse("20170608"),0.0,0.0,0.0, 243.78, 0) );    // 200
        list.add( new Quote("SPY",sdf.parse("20170607"),0.0,0.0,0.0, 243.66, 0) );    // 201
        list.add( new Quote("SPY",sdf.parse("20170606"),0.0,0.0,0.0, 243.21, 0) );    // 202
        list.add( new Quote("SPY",sdf.parse("20170605"),0.0,0.0,0.0, 243.99, 0) );    // 203
        list.add( new Quote("SPY",sdf.parse("20170602"),0.0,0.0,0.0, 244.17, 0) );    // 204
        list.add( new Quote("SPY",sdf.parse("20170601"),0.0,0.0,0.0, 243.36, 0) );    // 205
        list.add( new Quote("SPY",sdf.parse("20170531"),0.0,0.0,0.0, 241.44, 0) );    // 206
        list.add( new Quote("SPY",sdf.parse("20170530"),0.0,0.0,0.0, 241.5, 0) );    // 207
        list.add( new Quote("SPY",sdf.parse("20170529"),0.0,0.0,0.0, 241.71, 0) );    // 208
        list.add( new Quote("SPY",sdf.parse("20170526"),0.0,0.0,0.0, 241.71, 0) );    // 209
        list.add( new Quote("SPY",sdf.parse("20170525"),0.0,0.0,0.0, 241.76, 0) );    // 210
        list.add( new Quote("SPY",sdf.parse("20170524"),0.0,0.0,0.0, 240.61, 0) );    // 211
        list.add( new Quote("SPY",sdf.parse("20170523"),0.0,0.0,0.0, 240.05, 0) );    // 212
        list.add( new Quote("SPY",sdf.parse("20170522"),0.0,0.0,0.0, 239.52, 0) );    // 213
        list.add( new Quote("SPY",sdf.parse("20170519"),0.0,0.0,0.0, 238.31, 0) );    // 214
        list.add( new Quote("SPY",sdf.parse("20170518"),0.0,0.0,0.0, 236.77, 0) );    // 215
        list.add( new Quote("SPY",sdf.parse("20170517"),0.0,0.0,0.0, 235.82, 0) );    // 216
        list.add( new Quote("SPY",sdf.parse("20170516"),0.0,0.0,0.0, 240.08, 0) );    // 217
        list.add( new Quote("SPY",sdf.parse("20170515"),0.0,0.0,0.0, 240.3, 0) );    // 218
        list.add( new Quote("SPY",sdf.parse("20170512"),0.0,0.0,0.0, 238.98, 0) );    // 219
        list.add( new Quote("SPY",sdf.parse("20170511"),0.0,0.0,0.0, 239.38, 0) );    // 220
        list.add( new Quote("SPY",sdf.parse("20170510"),0.0,0.0,0.0, 239.87, 0) );    // 221
        list.add( new Quote("SPY",sdf.parse("20170509"),0.0,0.0,0.0, 239.44, 0) );    // 222
        list.add( new Quote("SPY",sdf.parse("20170508"),0.0,0.0,0.0, 239.66, 0) );    // 223
        list.add( new Quote("SPY",sdf.parse("20170505"),0.0,0.0,0.0, 239.7, 0) );    // 224
        list.add( new Quote("SPY",sdf.parse("20170504"),0.0,0.0,0.0, 238.76, 0) );    // 225
        list.add( new Quote("SPY",sdf.parse("20170503"),0.0,0.0,0.0, 238.48, 0) );    // 226
        list.add( new Quote("SPY",sdf.parse("20170502"),0.0,0.0,0.0, 238.77, 0) );    // 227
        list.add( new Quote("SPY",sdf.parse("20170501"),0.0,0.0,0.0, 238.68, 0) );    // 228
        list.add( new Quote("SPY",sdf.parse("20170428"),0.0,0.0,0.0, 238.08, 0) );    // 229
        list.add( new Quote("SPY",sdf.parse("20170427"),0.0,0.0,0.0, 238.6, 0) );    // 230
        list.add( new Quote("SPY",sdf.parse("20170426"),0.0,0.0,0.0, 238.4, 0) );    // 231
        list.add( new Quote("SPY",sdf.parse("20170425"),0.0,0.0,0.0, 238.55, 0) );    // 232
        list.add( new Quote("SPY",sdf.parse("20170424"),0.0,0.0,0.0, 237.17, 0) );    // 233
        list.add( new Quote("SPY",sdf.parse("20170421"),0.0,0.0,0.0, 234.59, 0) );    // 234
        list.add( new Quote("SPY",sdf.parse("20170420"),0.0,0.0,0.0, 235.34, 0) );    // 235
        list.add( new Quote("SPY",sdf.parse("20170419"),0.0,0.0,0.0, 233.44, 0) );    // 236
        list.add( new Quote("SPY",sdf.parse("20170418"),0.0,0.0,0.0, 233.87, 0) );    // 237
        list.add( new Quote("SPY",sdf.parse("20170417"),0.0,0.0,0.0, 234.57, 0) );    // 238
        list.add( new Quote("SPY",sdf.parse("20170414"),0.0,0.0,0.0, 232.51, 0) );    // 239
        list.add( new Quote("SPY",sdf.parse("20170413"),0.0,0.0,0.0, 232.51, 0) );    // 240
        list.add( new Quote("SPY",sdf.parse("20170412"),0.0,0.0,0.0, 234.03, 0) );    // 241
        list.add( new Quote("SPY",sdf.parse("20170411"),0.0,0.0,0.0, 235.06, 0) );    // 242
        list.add( new Quote("SPY",sdf.parse("20170410"),0.0,0.0,0.0, 235.34, 0) );    // 243
        list.add( new Quote("SPY",sdf.parse("20170407"),0.0,0.0,0.0, 235.2, 0) );    // 244
        list.add( new Quote("SPY",sdf.parse("20170406"),0.0,0.0,0.0, 235.44, 0) );    // 245
        list.add( new Quote("SPY",sdf.parse("20170405"),0.0,0.0,0.0, 234.78, 0) );    // 246
        list.add( new Quote("SPY",sdf.parse("20170404"),0.0,0.0,0.0, 235.48, 0) );    // 247
        list.add( new Quote("SPY",sdf.parse("20170403"),0.0,0.0,0.0, 235.33, 0) );    // 248
        list.add( new Quote("SPY",sdf.parse("20170331"),0.0,0.0,0.0, 235.74, 0) );    // 249
        list.add( new Quote("SPY",sdf.parse("20170330"),0.0,0.0,0.0, 236.29, 0) );    // 250
        list.add( new Quote("SPY",sdf.parse("20170329"),0.0,0.0,0.0, 235.54, 0) );    // 251
        list.add( new Quote("SPY",sdf.parse("20170328"),0.0,0.0,0.0, 235.32, 0) );    // 252
        list.add( new Quote("SPY",sdf.parse("20170327"),0.0,0.0,0.0, 233.62, 0) );    // 253
        list.add( new Quote("SPY",sdf.parse("20170326"),0.0,0.0,0.0, 233.01, 0) );    // 254 one year
    }


/************************************ cut line **************************************************/

    public void createDataAsc() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        list = new ArrayList<Quote>(253);

        list.add( new Quote("SPY",sdf.parse("20170327"),0.0,0.0,0.0, 233.62, 0) );
        list.add( new Quote("SPY",sdf.parse("20170328"),0.0,0.0,0.0, 235.32, 0) );
        list.add( new Quote("SPY",sdf.parse("20170329"),0.0,0.0,0.0, 235.54, 0) );	// 1 year return
        list.add( new Quote("SPY",sdf.parse("20170330"),0.0,0.0,0.0, 236.29, 0) );
        list.add( new Quote("SPY",sdf.parse("20170331"),0.0,0.0,0.0, 235.74, 0) );
        list.add( new Quote("SPY",sdf.parse("20170403"),0.0,0.0,0.0, 235.33, 0) );
        list.add( new Quote("SPY",sdf.parse("20170404"),0.0,0.0,0.0, 235.48, 0) );
        list.add( new Quote("SPY",sdf.parse("20170405"),0.0,0.0,0.0, 234.78, 0) );
        list.add( new Quote("SPY",sdf.parse("20170406"),0.0,0.0,0.0, 235.44, 0) );
        list.add( new Quote("SPY",sdf.parse("20170407"),0.0,0.0,0.0, 235.2, 0) );
        list.add( new Quote("SPY",sdf.parse("20170410"),0.0,0.0,0.0, 235.34, 0) );
        list.add( new Quote("SPY",sdf.parse("20170411"),0.0,0.0,0.0, 235.06, 0) );
        list.add( new Quote("SPY",sdf.parse("20170412"),0.0,0.0,0.0, 234.03, 0) );
        list.add( new Quote("SPY",sdf.parse("20170413"),0.0,0.0,0.0, 232.51, 0) );
        list.add( new Quote("SPY",sdf.parse("20170414"),0.0,0.0,0.0, 232.51, 0) );
        list.add( new Quote("SPY",sdf.parse("20170417"),0.0,0.0,0.0, 234.57, 0) );
        list.add( new Quote("SPY",sdf.parse("20170418"),0.0,0.0,0.0, 233.87, 0) );
        list.add( new Quote("SPY",sdf.parse("20170419"),0.0,0.0,0.0, 233.44, 0) );
        list.add( new Quote("SPY",sdf.parse("20170420"),0.0,0.0,0.0, 235.34, 0) );
        list.add( new Quote("SPY",sdf.parse("20170421"),0.0,0.0,0.0, 234.59, 0) );
        list.add( new Quote("SPY",sdf.parse("20170424"),0.0,0.0,0.0, 237.17, 0) );
        list.add( new Quote("SPY",sdf.parse("20170425"),0.0,0.0,0.0, 238.55, 0) );
        list.add( new Quote("SPY",sdf.parse("20170426"),0.0,0.0,0.0, 238.4, 0) );
        list.add( new Quote("SPY",sdf.parse("20170427"),0.0,0.0,0.0, 238.6, 0) );
        list.add( new Quote("SPY",sdf.parse("20170428"),0.0,0.0,0.0, 238.08, 0) );
        list.add( new Quote("SPY",sdf.parse("20170501"),0.0,0.0,0.0, 238.68, 0) );
        list.add( new Quote("SPY",sdf.parse("20170502"),0.0,0.0,0.0, 238.77, 0) );
        list.add( new Quote("SPY",sdf.parse("20170503"),0.0,0.0,0.0, 238.48, 0) );
        list.add( new Quote("SPY",sdf.parse("20170504"),0.0,0.0,0.0, 238.76, 0) );
        list.add( new Quote("SPY",sdf.parse("20170505"),0.0,0.0,0.0, 239.7, 0) );
        list.add( new Quote("SPY",sdf.parse("20170508"),0.0,0.0,0.0, 239.66, 0) );
        list.add( new Quote("SPY",sdf.parse("20170509"),0.0,0.0,0.0, 239.44, 0) );
        list.add( new Quote("SPY",sdf.parse("20170510"),0.0,0.0,0.0, 239.87, 0) );
        list.add( new Quote("SPY",sdf.parse("20170511"),0.0,0.0,0.0, 239.38, 0) );
        list.add( new Quote("SPY",sdf.parse("20170512"),0.0,0.0,0.0, 238.98, 0) );
        list.add( new Quote("SPY",sdf.parse("20170515"),0.0,0.0,0.0, 240.3, 0) );
        list.add( new Quote("SPY",sdf.parse("20170516"),0.0,0.0,0.0, 240.08, 0) );
        list.add( new Quote("SPY",sdf.parse("20170517"),0.0,0.0,0.0, 235.82, 0) );
        list.add( new Quote("SPY",sdf.parse("20170518"),0.0,0.0,0.0, 236.77, 0) );
        list.add( new Quote("SPY",sdf.parse("20170519"),0.0,0.0,0.0, 238.31, 0) );
        list.add( new Quote("SPY",sdf.parse("20170522"),0.0,0.0,0.0, 239.52, 0) );
        list.add( new Quote("SPY",sdf.parse("20170523"),0.0,0.0,0.0, 240.05, 0) );
        list.add( new Quote("SPY",sdf.parse("20170524"),0.0,0.0,0.0, 240.61, 0) );
        list.add( new Quote("SPY",sdf.parse("20170525"),0.0,0.0,0.0, 241.76, 0) );
        list.add( new Quote("SPY",sdf.parse("20170526"),0.0,0.0,0.0, 241.71, 0) );
        list.add( new Quote("SPY",sdf.parse("20170529"),0.0,0.0,0.0, 241.71, 0) );
        list.add( new Quote("SPY",sdf.parse("20170530"),0.0,0.0,0.0, 241.5, 0) );
        list.add( new Quote("SPY",sdf.parse("20170531"),0.0,0.0,0.0, 241.44, 0) );
        list.add( new Quote("SPY",sdf.parse("20170601"),0.0,0.0,0.0, 243.36, 0) );
        list.add( new Quote("SPY",sdf.parse("20170602"),0.0,0.0,0.0, 244.17, 0) );
        list.add( new Quote("SPY",sdf.parse("20170605"),0.0,0.0,0.0, 243.99, 0) );
        list.add( new Quote("SPY",sdf.parse("20170606"),0.0,0.0,0.0, 243.21, 0) );
        list.add( new Quote("SPY",sdf.parse("20170607"),0.0,0.0,0.0, 243.66, 0) );
        list.add( new Quote("SPY",sdf.parse("20170608"),0.0,0.0,0.0, 243.78, 0) );
        list.add( new Quote("SPY",sdf.parse("20170609"),0.0,0.0,0.0, 243.41, 0) );
        list.add( new Quote("SPY",sdf.parse("20170612"),0.0,0.0,0.0, 243.36, 0) );
        list.add( new Quote("SPY",sdf.parse("20170613"),0.0,0.0,0.0, 244.55, 0) );
        list.add( new Quote("SPY",sdf.parse("20170614"),0.0,0.0,0.0, 244.24, 0) );
        list.add( new Quote("SPY",sdf.parse("20170615"),0.0,0.0,0.0, 243.77, 0) );
        list.add( new Quote("SPY",sdf.parse("20170616"),0.0,0.0,0.0, 242.64, 0) );
        list.add( new Quote("SPY",sdf.parse("20170619"),0.0,0.0,0.0, 244.66, 0) );
        list.add( new Quote("SPY",sdf.parse("20170620"),0.0,0.0,0.0, 243.01, 0) );
        list.add( new Quote("SPY",sdf.parse("20170621"),0.0,0.0,0.0, 242.95, 0) );
        list.add( new Quote("SPY",sdf.parse("20170622"),0.0,0.0,0.0, 242.84, 0) );
        list.add( new Quote("SPY",sdf.parse("20170623"),0.0,0.0,0.0, 243.13, 0) );
        list.add( new Quote("SPY",sdf.parse("20170626"),0.0,0.0,0.0, 243.29, 0) );
        list.add( new Quote("SPY",sdf.parse("20170627"),0.0,0.0,0.0, 241.33, 0) );
        list.add( new Quote("SPY",sdf.parse("20170628"),0.0,0.0,0.0, 243.49, 0) );
        list.add( new Quote("SPY",sdf.parse("20170629"),0.0,0.0,0.0, 241.35, 0) );
        list.add( new Quote("SPY",sdf.parse("20170630"),0.0,0.0,0.0, 241.8, 0) );
        list.add( new Quote("SPY",sdf.parse("20170703"),0.0,0.0,0.0, 242.21, 0) );
        list.add( new Quote("SPY",sdf.parse("20170705"),0.0,0.0,0.0, 242.77, 0) );
        list.add( new Quote("SPY",sdf.parse("20170706"),0.0,0.0,0.0, 240.55, 0) );
        list.add( new Quote("SPY",sdf.parse("20170707"),0.0,0.0,0.0, 242.11, 0) );
        list.add( new Quote("SPY",sdf.parse("20170710"),0.0,0.0,0.0, 242.37, 0) );
        list.add( new Quote("SPY",sdf.parse("20170712"),0.0,0.0,0.0, 244.01, 0) );
        list.add( new Quote("SPY",sdf.parse("20170713"),0.0,0.0,0.0, 244.42, 0) );
        list.add( new Quote("SPY",sdf.parse("20170714"),0.0,0.0,0.0, 245.56, 0) );
        list.add( new Quote("SPY",sdf.parse("20170717"),0.0,0.0,0.0, 245.53, 0) );
        list.add( new Quote("SPY",sdf.parse("20170718"),0.0,0.0,0.0, 245.66, 0) );
        list.add( new Quote("SPY",sdf.parse("20170719"),0.0,0.0,0.0, 246.99, 0) );
        list.add( new Quote("SPY",sdf.parse("20170720"),0.0,0.0,0.0, 247.1, 0) );
        list.add( new Quote("SPY",sdf.parse("20170721"),0.0,0.0,0.0, 246.88, 0) );
        list.add( new Quote("SPY",sdf.parse("20170724"),0.0,0.0,0.0, 246.82, 0) );
        list.add( new Quote("SPY",sdf.parse("20170725"),0.0,0.0,0.0, 247.42, 0) );
        list.add( new Quote("SPY",sdf.parse("20170726"),0.0,0.0,0.0, 247.43, 0) );
        list.add( new Quote("SPY",sdf.parse("20170727"),0.0,0.0,0.0, 247.2, 0) );
        list.add( new Quote("SPY",sdf.parse("20170728"),0.0,0.0,0.0, 246.91, 0) );
        list.add( new Quote("SPY",sdf.parse("20170731"),0.0,0.0,0.0, 246.77, 0) );
        list.add( new Quote("SPY",sdf.parse("20170801"),0.0,0.0,0.0, 247.32, 0) );
        list.add( new Quote("SPY",sdf.parse("20170802"),0.0,0.0,0.0, 247.44, 0) );
        list.add( new Quote("SPY",sdf.parse("20170803"),0.0,0.0,0.0, 246.96, 0) );
        list.add( new Quote("SPY",sdf.parse("20170804"),0.0,0.0,0.0, 247.41, 0) );
        list.add( new Quote("SPY",sdf.parse("20170807"),0.0,0.0,0.0, 247.87, 0) );
        list.add( new Quote("SPY",sdf.parse("20170808"),0.0,0.0,0.0, 247.26, 0) );
        list.add( new Quote("SPY",sdf.parse("20170809"),0.0,0.0,0.0, 247.25, 0) );
        list.add( new Quote("SPY",sdf.parse("20170810"),0.0,0.0,0.0, 243.76, 0) );
        list.add( new Quote("SPY",sdf.parse("20170811"),0.0,0.0,0.0, 244.12, 0) );
        list.add( new Quote("SPY",sdf.parse("20170814"),0.0,0.0,0.0, 246.54, 0) );
        list.add( new Quote("SPY",sdf.parse("20170815"),0.0,0.0,0.0, 246.51, 0) );
        list.add( new Quote("SPY",sdf.parse("20170816"),0.0,0.0,0.0, 246.94, 0) );
        list.add( new Quote("SPY",sdf.parse("20170817"),0.0,0.0,0.0, 243.09, 0) );
        list.add( new Quote("SPY",sdf.parse("20170818"),0.0,0.0,0.0, 242.71, 0) );
        list.add( new Quote("SPY",sdf.parse("20170821"),0.0,0.0,0.0, 242.9, 0) );
        list.add( new Quote("SPY",sdf.parse("20170822"),0.0,0.0,0.0, 245.44, 0) );
        list.add( new Quote("SPY",sdf.parse("20170823"),0.0,0.0,0.0, 244.56, 0) );
        list.add( new Quote("SPY",sdf.parse("20170824"),0.0,0.0,0.0, 243.99, 0) );
        list.add( new Quote("SPY",sdf.parse("20170825"),0.0,0.0,0.0, 244.56, 0) );
        list.add( new Quote("SPY",sdf.parse("20170828"),0.0,0.0,0.0, 244.57, 0) );
        list.add( new Quote("SPY",sdf.parse("20170829"),0.0,0.0,0.0, 244.85, 0) );
        list.add( new Quote("SPY",sdf.parse("20170830"),0.0,0.0,0.0, 246.01, 0) );
        list.add( new Quote("SPY",sdf.parse("20170831"),0.0,0.0,0.0, 247.49, 0) );
        list.add( new Quote("SPY",sdf.parse("20170901"),0.0,0.0,0.0, 247.84, 0) );
        list.add( new Quote("SPY",sdf.parse("20170905"),0.0,0.0,0.0, 246.06, 0) );
        list.add( new Quote("SPY",sdf.parse("20170906"),0.0,0.0,0.0, 246.9, 0) );
        list.add( new Quote("SPY",sdf.parse("20170907"),0.0,0.0,0.0, 246.87, 0) );
        list.add( new Quote("SPY",sdf.parse("20170908"),0.0,0.0,0.0, 246.58, 0) );
        list.add( new Quote("SPY",sdf.parse("20170911"),0.0,0.0,0.0, 249.21, 0) );
        list.add( new Quote("SPY",sdf.parse("20170912"),0.0,0.0,0.0, 250.05, 0) );
        list.add( new Quote("SPY",sdf.parse("20170913"),0.0,0.0,0.0, 250.17, 0) );
        list.add( new Quote("SPY",sdf.parse("20170914"),0.0,0.0,0.0, 250.09, 0) );
        list.add( new Quote("SPY",sdf.parse("20170915"),0.0,0.0,0.0, 249.19, 0) );
        list.add( new Quote("SPY",sdf.parse("20170918"),0.0,0.0,0.0, 249.72, 0) );
        list.add( new Quote("SPY",sdf.parse("20170919"),0.0,0.0,0.0, 249.97, 0) );
        list.add( new Quote("SPY",sdf.parse("20170920"),0.0,0.0,0.0, 250.06, 0) );
        list.add( new Quote("SPY",sdf.parse("20170921"),0.0,0.0,0.0, 249.39, 0) );
        list.add( new Quote("SPY",sdf.parse("20170922"),0.0,0.0,0.0, 249.44, 0) );
        list.add( new Quote("SPY",sdf.parse("20170925"),0.0,0.0,0.0, 248.93, 0) );
        list.add( new Quote("SPY",sdf.parse("20170926"),0.0,0.0,0.0, 249.08, 0) );
        list.add( new Quote("SPY",sdf.parse("20170927"),0.0,0.0,0.0, 250.05, 0) );
        list.add( new Quote("SPY",sdf.parse("20170928"),0.0,0.0,0.0, 250.35, 0) );
        list.add( new Quote("SPY",sdf.parse("20170929"),0.0,0.0,0.0, 251.23, 0) );
        list.add( new Quote("SPY",sdf.parse("20171002"),0.0,0.0,0.0, 252.32, 0) );
        list.add( new Quote("SPY",sdf.parse("20171003"),0.0,0.0,0.0, 252.86, 0) );
        list.add( new Quote("SPY",sdf.parse("20171004"),0.0,0.0,0.0, 253.16, 0) );
        list.add( new Quote("SPY",sdf.parse("20171005"),0.0,0.0,0.0, 254.66, 0) );
        list.add( new Quote("SPY",sdf.parse("20171006"),0.0,0.0,0.0, 254.37, 0) );
        list.add( new Quote("SPY",sdf.parse("20171009"),0.0,0.0,0.0, 253.95, 0) );
        list.add( new Quote("SPY",sdf.parse("20171010"),0.0,0.0,0.0, 254.62, 0) );
        list.add( new Quote("SPY",sdf.parse("20171011"),0.0,0.0,0.0, 255.02, 0) );
        list.add( new Quote("SPY",sdf.parse("20171012"),0.0,0.0,0.0, 254.64, 0) );
        list.add( new Quote("SPY",sdf.parse("20171013"),0.0,0.0,0.0, 254.95, 0) );
        list.add( new Quote("SPY",sdf.parse("20171016"),0.0,0.0,0.0, 255.29, 0) );
        list.add( new Quote("SPY",sdf.parse("20171017"),0.0,0.0,0.0, 255.47, 0) );
        list.add( new Quote("SPY",sdf.parse("20171018"),0.0,0.0,0.0, 255.72, 0) );
        list.add( new Quote("SPY",sdf.parse("20171019"),0.0,0.0,0.0, 255.79, 0) );
        list.add( new Quote("SPY",sdf.parse("20171020"),0.0,0.0,0.0, 257.11, 0) );
        list.add( new Quote("SPY",sdf.parse("20171023"),0.0,0.0,0.0, 256.11, 0) );
        list.add( new Quote("SPY",sdf.parse("20171024"),0.0,0.0,0.0, 256.56, 0) );
        list.add( new Quote("SPY",sdf.parse("20171025"),0.0,0.0,0.0, 255.29, 0) );
        list.add( new Quote("SPY",sdf.parse("20171026"),0.0,0.0,0.0, 255.62, 0) );
        list.add( new Quote("SPY",sdf.parse("20171027"),0.0,0.0,0.0, 257.71, 0) );
        list.add( new Quote("SPY",sdf.parse("20171030"),0.0,0.0,0.0, 256.75, 0) );
        list.add( new Quote("SPY",sdf.parse("20171031"),0.0,0.0,0.0, 257.15, 0) );
        list.add( new Quote("SPY",sdf.parse("20171101"),0.0,0.0,0.0, 257.49, 0) );
        list.add( new Quote("SPY",sdf.parse("20171102"),0.0,0.0,0.0, 257.59, 0) );
        list.add( new Quote("SPY",sdf.parse("20171103"),0.0,0.0,0.0, 258.45, 0) );
        list.add( new Quote("SPY",sdf.parse("20171106"),0.0,0.0,0.0, 258.85, 0) );
        list.add( new Quote("SPY",sdf.parse("20171107"),0.0,0.0,0.0, 258.67, 0) );
        list.add( new Quote("SPY",sdf.parse("20171108"),0.0,0.0,0.0, 259.11, 0) );
        list.add( new Quote("SPY",sdf.parse("20171109"),0.0,0.0,0.0, 258.17, 0) );
        list.add( new Quote("SPY",sdf.parse("20171110"),0.0,0.0,0.0, 258.09, 0) );
        list.add( new Quote("SPY",sdf.parse("20171113"),0.0,0.0,0.0, 258.33, 0) );
        list.add( new Quote("SPY",sdf.parse("20171114"),0.0,0.0,0.0, 257.73, 0) );
        list.add( new Quote("SPY",sdf.parse("20171115"),0.0,0.0,0.0, 256.44, 0) );
        list.add( new Quote("SPY",sdf.parse("20171116"),0.0,0.0,0.0, 258.62, 0) );
        list.add( new Quote("SPY",sdf.parse("20171117"),0.0,0.0,0.0, 257.86, 0) );
        list.add( new Quote("SPY",sdf.parse("20171120"),0.0,0.0,0.0, 258.3, 0) );
        list.add( new Quote("SPY",sdf.parse("20171121"),0.0,0.0,0.0, 259.99, 0) );
        list.add( new Quote("SPY",sdf.parse("20171122"),0.0,0.0,0.0, 259.76, 0) );
        list.add( new Quote("SPY",sdf.parse("20171124"),0.0,0.0,0.0, 260.36, 0) );
        list.add( new Quote("SPY",sdf.parse("20171127"),0.0,0.0,0.0, 260.23, 0) );
        list.add( new Quote("SPY",sdf.parse("20171128"),0.0,0.0,0.0, 262.87, 0) );
        list.add( new Quote("SPY",sdf.parse("20171129"),0.0,0.0,0.0, 262.71, 0) );
        list.add( new Quote("SPY",sdf.parse("20171130"),0.0,0.0,0.0, 265.01, 0) );
        list.add( new Quote("SPY",sdf.parse("20171201"),0.0,0.0,0.0, 264.46, 0) );
        list.add( new Quote("SPY",sdf.parse("20171204"),0.0,0.0,0.0, 264.14, 0) );
        list.add( new Quote("SPY",sdf.parse("20171205"),0.0,0.0,0.0, 263.19, 0) );
        list.add( new Quote("SPY",sdf.parse("20171206"),0.0,0.0,0.0, 263.24, 0) );
        list.add( new Quote("SPY",sdf.parse("20171207"),0.0,0.0,0.0, 264.07, 0) );
        list.add( new Quote("SPY",sdf.parse("20171208"),0.0,0.0,0.0, 265.51, 0) );
        list.add( new Quote("SPY",sdf.parse("20171211"),0.0,0.0,0.0, 266.31, 0) );
        list.add( new Quote("SPY",sdf.parse("20171212"),0.0,0.0,0.0, 266.78, 0) );
        list.add( new Quote("SPY",sdf.parse("20171213"),0.0,0.0,0.0, 266.75, 0) );
        list.add( new Quote("SPY",sdf.parse("20171214"),0.0,0.0,0.0, 265.66, 0) );
        list.add( new Quote("SPY",sdf.parse("20171215"),0.0,0.0,0.0, 266.51, 0) );
        list.add( new Quote("SPY",sdf.parse("20171218"),0.0,0.0,0.0, 268.2, 0) );
        list.add( new Quote("SPY",sdf.parse("20171219"),0.0,0.0,0.0, 267.17, 0) );
        list.add( new Quote("SPY",sdf.parse("20171220"),0.0,0.0,0.0, 267.03, 0) );
        list.add( new Quote("SPY",sdf.parse("20171221"),0.0,0.0,0.0, 267.58, 0) );
        list.add( new Quote("SPY",sdf.parse("20171222"),0.0,0.0,0.0, 267.51, 0) );
        list.add( new Quote("SPY",sdf.parse("20171225"),0.0,0.0,0.0, 267.54, 0) );  // 3 month return
        list.add( new Quote("SPY",sdf.parse("20171226"),0.0,0.0,0.0, 267.19, 0) );
        list.add( new Quote("SPY",sdf.parse("20171227"),0.0,0.0,0.0, 267.32, 0) );
        list.add( new Quote("SPY",sdf.parse("20171228"),0.0,0.0,0.0, 267.87, 0) );
        list.add( new Quote("SPY",sdf.parse("20171229"),0.0,0.0,0.0, 266.86, 0) );
        list.add( new Quote("SPY",sdf.parse("20180102"),267.84,0.0,0.0, 268.77, 0) ); 	// start of year
        list.add( new Quote("SPY",sdf.parse("20180103"),0.0,0.0,0.0, 270.47, 0) );
        list.add( new Quote("SPY",sdf.parse("20180104"),0.0,0.0,0.0, 271.61, 0) );
        list.add( new Quote("SPY",sdf.parse("20180105"),0.0,0.0,0.0, 273.42, 0) );
        list.add( new Quote("SPY",sdf.parse("20180108"),0.0,0.0,0.0, 273.92, 0) );
        list.add( new Quote("SPY",sdf.parse("20180109"),0.0,0.0,0.0, 274.54, 0) );
        list.add( new Quote("SPY",sdf.parse("20180110"),0.0,0.0,0.0, 274.12, 0) );
        list.add( new Quote("SPY",sdf.parse("20180111"),0.0,0.0,0.0, 276.12, 0) );
        list.add( new Quote("SPY",sdf.parse("20180112"),0.0,0.0,0.0, 277.92, 0) );
        list.add( new Quote("SPY",sdf.parse("20180116"),0.0,0.0,0.0, 276.97, 0) );
        list.add( new Quote("SPY",sdf.parse("20180117"),0.0,0.0,0.0, 279.61, 0) );
        list.add( new Quote("SPY",sdf.parse("20180118"),0.0,0.0,0.0, 279.14, 0) );
        list.add( new Quote("SPY",sdf.parse("20180119"),0.0,0.0,0.0, 280.41, 0) );
        list.add( new Quote("SPY",sdf.parse("20180122"),0.0,0.0,0.0, 282.69, 0) );
        list.add( new Quote("SPY",sdf.parse("20180123"),0.0,0.0,0.0, 283.29, 0) );
        list.add( new Quote("SPY",sdf.parse("20180124"),0.0,0.0,0.0, 283.18, 0) );
        list.add( new Quote("SPY",sdf.parse("20180125"),0.0,0.0,0.0, 283.3, 0) );
        list.add( new Quote("SPY",sdf.parse("20180126"),0.0,0.0,0.0, 286.58, 0) );
        list.add( new Quote("SPY",sdf.parse("20180129"),0.0,0.0,0.0, 284.68, 0) );
        list.add( new Quote("SPY",sdf.parse("20180130"),0.0,0.0,0.0, 281.76, 0) );
        list.add( new Quote("SPY",sdf.parse("20180131"),0.0,0.0,0.0, 281.9, 0) );
        list.add( new Quote("SPY",sdf.parse("20180201"),0.0,0.0,0.0, 281.58, 0) );
        list.add( new Quote("SPY",sdf.parse("20180202"),0.0,0.0,0.0, 275.45, 0) );
        list.add( new Quote("SPY",sdf.parse("20180205"),0.0,0.0,0.0, 263.93, 0) );
        list.add( new Quote("SPY",sdf.parse("20180206"),0.0,0.0,0.0, 269.13, 0) );
        list.add( new Quote("SPY",sdf.parse("20180207"),0.0,0.0,0.0, 267.67, 0) );
        list.add( new Quote("SPY",sdf.parse("20180208"),0.0,0.0,0.0, 257.63, 0) );
        list.add( new Quote("SPY",sdf.parse("20180209"),0.0,0.0,0.0, 261.5, 0) );
        list.add( new Quote("SPY",sdf.parse("20180212"),0.0,0.0,0.0, 265.34, 0) );
        list.add( new Quote("SPY",sdf.parse("20180213"),0.0,0.0,0.0, 266, 0) );
        list.add( new Quote("SPY",sdf.parse("20180214"),0.0,0.0,0.0, 269.5, 0) );
        list.add( new Quote("SPY",sdf.parse("20180215"),0.0,0.0,0.0, 273.03, 0) );
        list.add( new Quote("SPY",sdf.parse("20180216"),0.0,0.0,0.0, 273.11, 0) );
        list.add( new Quote("SPY",sdf.parse("20180220"),0.0,0.0,0.0, 271.4, 0) );
        list.add( new Quote("SPY",sdf.parse("20180221"),0.0,0.0,0.0, 270.05, 0) );
        list.add( new Quote("SPY",sdf.parse("20180222"),0.0,0.0,0.0, 270.4, 0) );
        list.add( new Quote("SPY",sdf.parse("20180223"),0.0,0.0,0.0, 274.71, 0) );
        list.add( new Quote("SPY",sdf.parse("20180226"),0.0,0.0,0.0, 277.9, 0) );	// four week return
        list.add( new Quote("SPY",sdf.parse("20180227"),0.0,0.0,0.0, 274.43, 0) );
        list.add( new Quote("SPY",sdf.parse("20180228"),0.0,0.0,0.0, 271.65, 0) );
        list.add( new Quote("SPY",sdf.parse("20180301"),0.0,0.0,0.0, 267.7, 0) );
        list.add( new Quote("SPY",sdf.parse("20180302"),0.0,0.0,0.0, 269.08, 0) );
        list.add( new Quote("SPY",sdf.parse("20180305"),0.0,0.0,0.0, 272.19, 0) );
        list.add( new Quote("SPY",sdf.parse("20180306"),0.0,0.0,0.0, 272.88, 0) );
        list.add( new Quote("SPY",sdf.parse("20180307"),0.0,0.0,0.0, 272.78, 0) );
        list.add( new Quote("SPY",sdf.parse("20180308"),0.0,0.0,0.0, 274.1, 0) );
        list.add( new Quote("SPY",sdf.parse("20180309"),0.0,0.0,0.0, 278.87, 0) );
        list.add( new Quote("SPY",sdf.parse("20180312"),0.0,0.0,0.0, 278.52, 0) );	// 2 week return
        list.add( new Quote("SPY",sdf.parse("20180313"),0.0,0.0,0.0, 276.72, 0) );
        list.add( new Quote("SPY",sdf.parse("20180314"),0.0,0.0,0.0, 275.3, 0) );
        list.add( new Quote("SPY",sdf.parse("20180315"),0.0,0.0,0.0, 275, 0) );
        list.add( new Quote("SPY",sdf.parse("20180316"),0.0,0.0,0.0, 274.2, 0) );
        list.add( new Quote("SPY",sdf.parse("20180319"),0.0,0.0,0.0, 270.49, 0) );	// 1 week return
        list.add( new Quote("SPY",sdf.parse("20180320"),0.0,0.0,0.0, 270.95, 0) );
        list.add( new Quote("SPY",sdf.parse("20180321"),0.0,0.0,0.0, 270.43, 0) );
        list.add( new Quote("SPY",sdf.parse("20180322"),0.0,0.0,0.0, 263.67, 0) );	// 1 day return
        list.add( new Quote("SPY",sdf.parse("20180323"),0.0,0.0,0.0, 258.05, 0) );	// start

    }


}
