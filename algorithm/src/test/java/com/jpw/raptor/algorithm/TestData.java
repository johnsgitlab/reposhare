package com.jpw.raptor.algorithm;
import com.jpw.raptor.model.Quote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Created by john on 9/6/18.
 */
public class TestData {

    public List<Quote> get() throws ParseException {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Quote q01 = new Quote( "IVV", fmt.parse("2018-07-27"), 283.71, 283.82, 280.38, 281.42, 76783100);
        Quote q02 = new Quote( "IVV", fmt.parse("2018-07-30"), 281.51, 281.69, 279.36, 279.95, 63742500);
        Quote q03 = new Quote( "IVV", fmt.parse("2018-07-31"), 280.81, 282.02, 280.38, 281.33, 68570400);
        Quote q04 = new Quote( "IVV", fmt.parse("2018-08-01"), 281.56, 282.13, 280.13, 280.86, 53853300);
        Quote q05 = new Quote( "IVV", fmt.parse("2018-08-02"), 279.39, 282.58, 279.16, 282.39, 63426300);
        Quote q06 = new Quote( "IVV", fmt.parse("2018-08-03"), 282.53, 283.66, 282.33, 283.6, 53935300);
        Quote q07 = new Quote( "IVV", fmt.parse("2018-08-06"), 283.64, 284.99, 283.2, 284.64, 39400800);
        Quote q08 = new Quote( "IVV", fmt.parse("2018-08-07"), 285.39, 286.01, 285.24, 285.58, 43196600);
        Quote q09 = new Quote( "IVV", fmt.parse("2018-08-08"), 285.39, 285.91, 284.94, 285.46, 42114500);
        Quote q10 = new Quote( "IVV", fmt.parse("2018-08-09"), 285.53, 285.97, 284.92, 285.07, 35716900);
        Quote q11 = new Quote( "IVV", fmt.parse("2018-08-10"), 283.45, 284.06, 282.36, 283.16, 77076000);
        Quote q12 = new Quote( "IVV", fmt.parse("2018-08-13"), 283.47, 284.16, 281.77, 282.1, 65522000);
        Quote q13 = new Quote( "IVV", fmt.parse("2018-08-14"), 282.92, 284.17, 282.48, 283.9, 43827100);
        Quote q14 = new Quote( "IVV", fmt.parse("2018-08-15"), 282.38, 282.54, 280.16, 281.78, 102925300);
        Quote q15 = new Quote( "IVV", fmt.parse("2018-08-16"), 283.4, 285.04, 283.36, 284.06, 69967900);
        Quote q16 = new Quote( "IVV", fmt.parse("2018-08-17"), 283.83, 285.56, 283.37, 285.06, 65618400);
        Quote q17 = new Quote( "IVV", fmt.parse("2018-08-20"), 285.57, 285.97, 285.06, 285.67, 39807400);
        Quote q18 = new Quote( "IVV", fmt.parse("2018-08-21"), 286.25, 287.31, 285.71, 286.34, 67271900);
        Quote q19 = new Quote( "IVV", fmt.parse("2018-08-22"), 285.88, 286.76, 285.58, 286.17, 44993300);
        Quote q20 = new Quote( "IVV", fmt.parse("2018-08-23"), 285.97, 286.94, 285.43, 285.79, 49188200);

        Quote[] dataArray = {q01, q02, q03, q04, q05, q06, q07, q08, q09, q10, q11,
                q12, q13, q14,  q15, q16, q17, q18, q19, q20};

        List<Quote> data = Arrays.asList(dataArray);

        return data;
    }

    public List<Quote> getDesc() throws ParseException {

        return Arrays.asList( getArray() );
    }

    public List<Quote> getAsc() throws ParseException {

        List<Quote> data = Arrays.asList( getArray() );
        Collections.reverse ( data );

        return data;
    }


    public List<Quote> getData() throws ParseException {

        return Arrays.asList( getArray() );
    }

    protected Quote[] getArray() throws ParseException {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Quote[] dataArray = {
                new Quote ( "IVV", fmt.parse("2019-01-09"), 259.09, 260.48, 257.72, 259.49, 4096900),
                new Quote ( "IVV", fmt.parse("2019-01-08"), 258.38, 258.82, 255.52, 258.29, 5340600),
                new Quote ( "IVV", fmt.parse("2019-01-07"), 254.24, 257.46, 253.21, 255.77, 5961300),
                new Quote ( "IVV", fmt.parse("2019-01-04"), 249.06, 254.59, 248.64, 254.06, 6430100),
                new Quote ( "IVV", fmt.parse("2019-01-03"), 249.79, 250.03, 245.08, 245.43, 5828300),
                new Quote ( "IVV", fmt.parse("2019-01-02"), 247.54, 252.7,  247.42, 251.72, 5978400),
                new Quote ( "IVV", fmt.parse("2018-12-31"), 251.04, 251.63, 248.99, 251.61, 10117800),
                new Quote ( "IVV", fmt.parse("2018-12-28"), 251.03, 252.88, 247.95, 249.33, 10856300),
                new Quote ( "IVV", fmt.parse("2018-12-27"), 244.33, 250.07, 240.67, 250.06, 12693600),
                new Quote ( "IVV", fmt.parse("2018-12-26"), 237.73, 247.89, 235.46, 247.67, 13154100),
                new Quote ( "IVV", fmt.parse("2018-12-24"), 240.8,  242,    236.04, 236.09, 6418900),
                new Quote ( "IVV", fmt.parse("2018-12-21"), 248.55, 251.41, 241.72, 242.35, 14262600),
                new Quote ( "IVV", fmt.parse("2018-12-20"), 250.22, 251.94, 244.98, 247.43, 14012700),
                new Quote ( "IVV", fmt.parse("2018-12-19"), 255.59, 259.83, 249.73, 251.74, 13166400),
                new Quote ( "IVV", fmt.parse("2018-12-18"), 257.67, 258.35, 253.71, 255.62, 10267600),
                new Quote ( "IVV", fmt.parse("2018-12-17"), 259.98, 261.11, 253.94, 255.65, 9320600),
                new Quote ( "IVV", fmt.parse("2018-12-14"), 264.9,  265.97, 261.75, 262.4,  6080500),
                new Quote ( "IVV", fmt.parse("2018-12-13"), 268.44, 269.46, 266.07, 267.24, 5215700),
                new Quote ( "IVV", fmt.parse("2018-12-12"), 269.48, 270.94, 267.35, 267.49, 3879300),
                new Quote ( "IVV", fmt.parse("2018-12-11"), 269.61, 269.82, 264.41, 266.05, 4521800),
                new Quote ( "IVV", fmt.parse("2018-12-10"), 265.34, 267.12, 260.51, 266,    8522700),
                new Quote ( "IVV", fmt.parse("2018-12-07"), 271.45, 273.27, 264.59, 265.6,  6089300),
                new Quote ( "IVV", fmt.parse("2018-12-06"), 267.92, 271.97, 264.36, 271.82, 8536300),
                new Quote ( "IVV", fmt.parse("2018-12-04"), 280.49, 280.93, 271.94, 272.53, 6727800),
                new Quote ( "IVV", fmt.parse("2018-12-03"), 282.43, 282.58, 279.59, 281.37, 5885400),
                new Quote ( "IVV", fmt.parse("2018-11-30"), 275.86, 278.38, 275.5,  278,    7603500),
                new Quote ( "IVV", fmt.parse("2018-11-29"), 275.78, 277.63, 274.49, 276.07, 4901300),
                new Quote ( "IVV", fmt.parse("2018-11-28"), 271.61, 276.63, 270.52, 276.63, 5484600),
                new Quote ( "IVV", fmt.parse("2018-11-27"), 268.45, 270.35, 267.62, 270.32, 4565100),
                new Quote ( "IVV", fmt.parse("2018-11-26"), 267.76, 269.53, 267.31, 269.47, 3187100),
                new Quote ( "IVV", fmt.parse("2018-11-23"), 265.1,  266.76, 265.02, 265.31, 1660800),
                new Quote ( "IVV", fmt.parse("2018-11-21"), 267.8,  269.09, 266.96, 266.96, 4247100),
                new Quote ( "IVV", fmt.parse("2018-11-20"), 267.31, 268.96, 265.09, 266.1,  6274100),
                new Quote ( "IVV", fmt.parse("2018-11-19"), 275.11, 275.37, 270.08, 271.13, 3555600),
                new Quote ( "IVV", fmt.parse("2018-11-16"), 273.83, 276.73, 273.29, 275.74, 3803800),
                new Quote ( "IVV", fmt.parse("2018-11-15"), 270.81, 275.57, 268.96, 274.96, 6163700),
                new Quote ( "IVV", fmt.parse("2018-11-14"), 276.2,  276.65, 270.43, 272.21, 4614900),
                new Quote ( "IVV", fmt.parse("2018-11-13"), 275.11, 277.37, 273.26, 274.11, 5396700),
                new Quote ( "IVV", fmt.parse("2018-11-12"), 279.25, 279.51, 274.01, 274.51, 3787000),
                new Quote ( "IVV", fmt.parse("2018-11-09"), 281.15, 281.34, 278.25, 279.96, 5327300),
                new Quote ( "IVV", fmt.parse("2018-11-08"), 282.3,  283.33, 281.35, 282.52, 7659500),
                new Quote ( "IVV", fmt.parse("2018-11-07"), 279.63, 283.23, 279.18, 283.13, 7675900),
                new Quote ( "IVV", fmt.parse("2018-11-06"), 275.4,  277.34, 275.31, 277.16, 2430800),
                new Quote ( "IVV", fmt.parse("2018-11-05"), 274.58, 276.04, 273.39, 275.57, 5606100),
                new Quote ( "IVV", fmt.parse("2018-11-02"), 276.75, 277.25, 271.61, 273.89, 3391800),
                new Quote ( "IVV", fmt.parse("2018-11-01"), 273.66, 275.76, 272.41, 275.42, 3428600),
                new Quote ( "IVV", fmt.parse("2018-10-31"), 272.79, 275.24, 272.18, 272.76, 7039800),
                new Quote ( "IVV", fmt.parse("2018-10-30"), 265.64, 270.12, 265.08, 269.86, 9522200),
                new Quote ( "IVV", fmt.parse("2018-10-29"), 270.84, 272.25, 261.85, 265.61, 6301800),
                new Quote ( "IVV", fmt.parse("2018-10-26"), 267.87, 270.73, 264.24, 267.1,  7153700),
                new Quote ( "IVV", fmt.parse("2018-10-25"), 269.37, 273.83, 268.2,  272.02, 4590400),
                new Quote ( "IVV", fmt.parse("2018-10-24"), 275.39, 275.79, 266.64, 267.14, 5209100),
                new Quote ( "IVV", fmt.parse("2018-10-23"), 272.96, 276.89, 270.61, 275.57, 5541000),
                new Quote ( "IVV", fmt.parse("2018-10-22"), 279.06, 279.41, 276.44, 277.22, 3852800),
                new Quote ( "IVV", fmt.parse("2018-10-19"), 279.26, 281.37, 277.59, 278.27, 3043300),
                new Quote ( "IVV", fmt.parse("2018-10-18"), 281.47, 282.13, 277.02, 278.51, 4418400),
                new Quote ( "IVV", fmt.parse("2018-10-17"), 282.55, 283.23, 279.63, 282.56, 5944800),
                new Quote ( "IVV", fmt.parse("2018-10-16"), 278.65, 282.96, 278.13, 282.62, 5129300),
                new Quote ( "IVV", fmt.parse("2018-10-15"), 277.59, 279.09, 276.37, 276.53, 3517700),
                new Quote ( "IVV", fmt.parse("2018-10-12"), 278.83, 279.14, 274.39, 278.03, 7286800),
                new Quote ( "IVV", fmt.parse("2018-10-11"), 279.23, 280.97, 272.4,  274.33, 10570000),
                new Quote ( "IVV", fmt.parse("2018-10-10"), 288.94, 288.99, 279.94, 280.25, 8209400),
                new Quote ( "IVV", fmt.parse("2018-10-09"), 289.55, 291.01, 288.93, 289.59, 5886800),
                new Quote ( "IVV", fmt.parse("2018-10-08"), 289.17, 290.36, 287.6,  289.94, 5377300),
                new Quote ( "IVV", fmt.parse("2018-10-05"), 291.87, 292.44, 288.36, 289.93, 4367900),
                new Quote ( "IVV", fmt.parse("2018-10-04"), 293.39, 293.42, 289.82, 291.74, 3836600),
                new Quote ( "IVV", fmt.parse("2018-10-03"), 294.92, 295.4,  293.51, 293.95, 3473800),
                new Quote ( "IVV", fmt.parse("2018-10-02"), 293.73, 294.53, 293.32, 293.75, 2927400),
                new Quote ( "IVV", fmt.parse("2018-10-01"), 294.3,  295.1,  293.16, 293.87, 3455400),
                new Quote ( "IVV", fmt.parse("2018-09-28"), 292.15, 293.44, 292.1,  292.73, 5172100),
                new Quote ( "IVV", fmt.parse("2018-09-27"), 292.57, 294.07, 291.93, 292.82, 2628200),
                new Quote ( "IVV", fmt.parse("2018-09-26"), 293.04, 294.41, 291.58, 292.02, 4082500),
                new Quote ( "IVV", fmt.parse("2018-09-25"), 294.97, 295.07, 293.92, 294.16, 3341800),
                new Quote ( "IVV", fmt.parse("2018-09-24"), 294.79, 294.94, 293.8,  294.47, 3412700),
                new Quote ( "IVV", fmt.parse("2018-09-21"), 296.57, 296.69, 295.26, 295.36, 3918900),
                new Quote ( "IVV", fmt.parse("2018-09-20"), 294.74, 296.07, 294.63, 295.76, 3985900),
                new Quote ( "IVV", fmt.parse("2018-09-19"), 293.03, 293.8,  292.95, 293.28, 2354100),
                new Quote ( "IVV", fmt.parse("2018-09-18"), 291.66, 293.69, 291.66, 293.07, 2520100),
                new Quote ( "IVV", fmt.parse("2018-09-17"), 292.91, 292.94, 291.12, 291.44, 2465800),
                new Quote ( "IVV", fmt.parse("2018-09-14"), 293.16, 293.36, 292.1,  292.91, 2616800),
                new Quote ( "IVV", fmt.parse("2018-09-13"), 292.39, 293.12, 292.09, 292.92, 1818000),
                new Quote ( "IVV", fmt.parse("2018-09-12"), 291.13, 291.86, 290.3,  291.24, 2634400),
                new Quote ( "IVV", fmt.parse("2018-09-11"), 289.4,  291.62, 289.03, 291.22, 3015600),
                new Quote ( "IVV", fmt.parse("2018-09-10"), 290.82, 291.11, 289.94, 290.25, 2767700),
                new Quote ( "IVV", fmt.parse("2018-09-07"), 289,    290.77, 288.77, 289.6,  5450900),
                new Quote ( "IVV", fmt.parse("2018-09-06"), 291.19, 291.57, 289.05, 290.31, 3839000),
                new Quote ( "IVV", fmt.parse("2018-09-05"), 291.5,  291.7,  289.95, 291.16, 7530300),
                new Quote ( "IVV", fmt.parse("2018-09-04"), 291.94, 292.29, 290.76, 291.95, 3480500),
                new Quote ( "IVV", fmt.parse("2018-08-31"), 291.93, 292.9,  291.37, 292.44, 4983100),
                new Quote ( "IVV", fmt.parse("2018-08-30"), 293,    293.46, 291.7,  292.29, 4669400),
                new Quote ( "IVV", fmt.parse("2018-08-29"), 292.25, 293.83, 291.96, 293.54, 2806100),
                new Quote ( "IVV", fmt.parse("2018-08-28"), 292.39, 292.49, 291.47, 291.89, 3556400),
                new Quote ( "IVV", fmt.parse("2018-08-27"), 290.9,  291.97, 290.73, 291.81, 3759700),
                new Quote ( "IVV", fmt.parse("2018-08-24"), 288.48, 289.72, 288.42, 289.52, 2451900),
                new Quote ( "IVV", fmt.parse("2018-08-23"), 287.97, 288.97, 287.47, 287.73, 3325300),
                new Quote ( "IVV", fmt.parse("2018-08-22"), 287.91, 288.79, 287.6,  288.22, 2124500),
                new Quote ( "IVV", fmt.parse("2018-08-21"), 288.31, 289.36, 288.13, 288.23, 2667400),
                new Quote ( "IVV", fmt.parse("2018-08-20"), 287.6,  288.01, 287.07, 287.74, 1853300),
                new Quote ( "IVV", fmt.parse("2018-08-17"), 285.87, 287.59, 285.4,  287.04, 2975800),
                new Quote ( "IVV", fmt.parse("2018-08-16"), 285.37, 287.07, 285.37, 286.1,  3367900),
                new Quote ( "IVV", fmt.parse("2018-08-15"), 284.37, 284.47, 282.15, 283.65, 3849100),
                new Quote ( "IVV", fmt.parse("2018-08-14"), 284.93, 286.16, 284.5,  285.93, 3217600),
                new Quote ( "IVV", fmt.parse("2018-08-13"), 285.47, 286.17, 283.77, 284.11, 3349700),
                new Quote ( "IVV", fmt.parse("2018-08-10"), 285.43, 286.04, 284.36, 285.07, 3290400),
                new Quote ( "IVV", fmt.parse("2018-08-09"), 287.51, 287.99, 286.94, 287.11, 2385400),
                new Quote ( "IVV", fmt.parse("2018-08-08"), 287.41, 287.94, 286.97, 287.37, 3483800),
                new Quote ( "IVV", fmt.parse("2018-08-07"), 287.43, 288.02, 287.25, 287.47, 5084600),
                new Quote ( "IVV", fmt.parse("2018-08-06"), 285.55, 286.99, 285.2,  286.65, 2988600),
                new Quote ( "IVV", fmt.parse("2018-08-03"), 284.53, 285.65, 284.32, 285.55, 2257800),
                new Quote ( "IVV", fmt.parse("2018-08-02"), 281.35, 284.56, 281.12, 284.23, 2327500),
                new Quote ( "IVV", fmt.parse("2018-08-01"), 283.46, 284.1,  282.11, 282.88, 2679900),
                new Quote ( "IVV", fmt.parse("2018-07-31"), 282.83, 283.99, 282.35, 283.28, 3588900),
                new Quote ( "IVV", fmt.parse("2018-07-30"), 283.47, 283.65, 281.31, 281.77, 4929700),
                new Quote ( "IVV", fmt.parse("2018-07-27"), 285.7,  285.8,  282.36, 283.36, 2798100),
                new Quote ( "IVV", fmt.parse("2018-07-26"), 285.18, 286.11, 285.08, 285.19, 4087300),
                new Quote ( "IVV", fmt.parse("2018-07-25"), 283.35, 286.38, 283.27, 286.18, 3310800),
                new Quote ( "IVV", fmt.parse("2018-07-24"), 283.72, 284.53, 282.61, 283.55, 2253000),
                new Quote ( "IVV", fmt.parse("2018-07-23"), 281.4,  282.4,  280.98, 282.14, 1879900),
                new Quote ( "IVV", fmt.parse("2018-07-20"), 281.71, 282.43, 281.45, 281.62, 4868500),
                new Quote ( "IVV", fmt.parse("2018-07-19"), 282.27, 282.69, 281.42, 281.92, 3220900),
                new Quote ( "IVV", fmt.parse("2018-07-18"), 282.55, 283.15, 282.01, 283.09, 3603900),
                new Quote ( "IVV", fmt.parse("2018-07-17"), 280.4,  282.87, 280.36, 282.47, 2322500),
                new Quote ( "IVV", fmt.parse("2018-07-16"), 281.6,  281.74, 280.8,  281.33, 2224000),
                new Quote ( "IVV", fmt.parse("2018-07-13"), 281.12, 281.89, 280.6,  281.43, 3122700),
                new Quote ( "IVV", fmt.parse("2018-07-12"), 280.18, 281.36, 279.54, 281.14, 3444800),
                new Quote ( "IVV", fmt.parse("2018-07-11"), 279.07, 279.97, 278.45, 278.74, 3781100),
                new Quote ( "IVV", fmt.parse("2018-07-10"), 280.2,  280.95, 280.01, 280.83, 3426700),
                new Quote ( "IVV", fmt.parse("2018-07-09"), 278.45, 279.86, 278.42, 279.81, 4775300),
                new Quote ( "IVV", fmt.parse("2018-07-06"), 275.03, 277.74, 274.62, 277.21, 2942100),
                new Quote ( "IVV", fmt.parse("2018-07-05"), 274.05, 275.07, 272.83, 275.07, 2605600),
                new Quote ( "IVV", fmt.parse("2018-07-03"), 274.75, 274.86, 272.29, 272.73, 1367800),
                new Quote ( "IVV", fmt.parse("2018-07-02"), 271.36, 273.94, 271.1,  273.82, 4579700),
                new Quote ( "IVV", fmt.parse("2018-06-29"), 273.99, 275.54, 273.05, 273.05, 7693800),
                new Quote ( "IVV", fmt.parse("2018-06-28"), 271.08, 273.61, 270.32, 272.82, 5285800),
                new Quote ( "IVV", fmt.parse("2018-06-27"), 274.08, 275.71, 271.03, 271.12, 6335300),
                new Quote ( "IVV", fmt.parse("2018-06-26"), 273.5,  274.4,  272.64, 273.47, 2690800),
                new Quote ( "IVV", fmt.parse("2018-06-25"), 276.6,  276.76, 272.23, 274.1,  5407400),
                new Quote ( "IVV", fmt.parse("2018-06-22"), 278.89, 278.96, 277.66, 277.89, 2297900),
                new Quote ( "IVV", fmt.parse("2018-06-21"), 279.15, 279.15, 276.84, 277.32, 2569200),
                new Quote ( "IVV", fmt.parse("2018-06-20"), 279.48, 279.91, 278.78, 279.25, 2562800),
                new Quote ( "IVV", fmt.parse("2018-06-19"), 277.19, 278.93, 276.69, 278.67, 4216400),
                new Quote ( "IVV", fmt.parse("2018-06-18"), 278.68, 279.91, 278.14, 279.7,  3112600),
                new Quote ( "IVV", fmt.parse("2018-06-15"), 279.84, 280.7,  278.55, 280.31, 5039000),
                new Quote ( "IVV", fmt.parse("2018-06-14"), 280.91, 281.28, 280.03, 280.71, 3629000),
                new Quote ( "IVV", fmt.parse("2018-06-13"), 281.13, 281.44, 279.76, 279.92, 3091200),
                new Quote ( "IVV", fmt.parse("2018-06-12"), 281.03, 281.29, 280.15, 280.89, 3515400),
                new Quote ( "IVV", fmt.parse("2018-06-11"), 280.34, 281.33, 280.25, 280.51, 2486000),
                new Quote ( "IVV", fmt.parse("2018-06-08"), 278.78, 280.18, 278.6,  280.04, 4139000),
                new Quote ( "IVV", fmt.parse("2018-06-07"), 279.9,  280.24, 278.27, 279.26, 3081100),
                new Quote ( "IVV", fmt.parse("2018-06-06"), 277.73, 279.47, 277.01, 279.44, 4836800),
                new Quote ( "IVV", fmt.parse("2018-06-05"), 276.93, 277.45, 276.1,  277.06, 1810300),
                new Quote ( "IVV", fmt.parse("2018-06-04"), 276.4,  277.1,  276.19, 276.81, 3088500),
                new Quote ( "IVV", fmt.parse("2018-06-01"), 274.32, 275.85, 274.27, 275.53, 3247300),
                new Quote ( "IVV", fmt.parse("2018-05-31"), 274.09, 274.35, 272.14, 272.73, 4633500),
                new Quote ( "IVV", fmt.parse("2018-05-30"), 272.45, 274.99, 272.3,  274.55, 3904600),
                new Quote ( "IVV", fmt.parse("2018-05-29"), 272.16, 273.03, 269.62, 270.93, 4176500),
                new Quote ( "IVV", fmt.parse("2018-05-25"), 274.05, 274.76, 273.46, 273.98, 3327100),
                new Quote ( "IVV", fmt.parse("2018-05-24"), 274.83, 275.12, 272.67, 274.75, 3192500),
                new Quote ( "IVV", fmt.parse("2018-05-23"), 273.05, 275.3,  272.92, 275.25, 2278100),
                new Quote ( "IVV", fmt.parse("2018-05-22"), 275.86, 276.17, 274.13, 274.47, 1919200),
                new Quote ( "IVV", fmt.parse("2018-05-21"), 274.86, 275.87, 274.46, 275.21, 1796300),
                new Quote ( "IVV", fmt.parse("2018-05-18"), 273.49, 273.92, 272.81, 273.21, 2262800),
                new Quote ( "IVV", fmt.parse("2018-05-17"), 273.82, 275.12, 272.98, 273.88, 2289800),
                new Quote ( "IVV", fmt.parse("2018-05-16"), 272.98, 274.62, 272.98, 274.1,  2068500),
                new Quote ( "IVV", fmt.parse("2018-05-15"), 273.45, 273.5,  271.91, 272.81, 3820400),
                new Quote ( "IVV", fmt.parse("2018-05-14"), 275.22, 275.94, 274.28, 274.82, 1876400),
                new Quote ( "IVV", fmt.parse("2018-05-11"), 274.04, 275.04, 273.48, 274.47, 2639000),
                new Quote ( "IVV", fmt.parse("2018-05-10"), 272.24, 274.27, 272.07, 273.9,  3494400),
                new Quote ( "IVV", fmt.parse("2018-05-09"), 269.51, 271.72, 268.95, 271.44, 3335400),
                new Quote ( "IVV", fmt.parse("2018-05-08"), 268.33, 269.13, 266.97, 268.68, 3261000),
                new Quote ( "IVV", fmt.parse("2018-05-07"), 268.75, 269.84, 267.95, 268.79, 2185300),
                new Quote ( "IVV", fmt.parse("2018-05-04"), 263.31, 268.6,  262.94, 267.82, 2378400),
                new Quote ( "IVV", fmt.parse("2018-05-03"), 264,    265.15, 260.81, 264.4,  3409200),
                new Quote ( "IVV", fmt.parse("2018-05-02"), 266.58, 267.48, 264.53, 264.97, 3090200),
                new Quote ( "IVV", fmt.parse("2018-05-01"), 265.78, 266.92, 263.9,  266.83, 2973500),
                new Quote ( "IVV", fmt.parse("2018-04-30"), 269.07, 269.7,  266.2,  266.31, 3839400),
                new Quote ( "IVV", fmt.parse("2018-04-27"), 268.79, 269.18, 267.3,  268.39, 3579800),
                new Quote ( "IVV", fmt.parse("2018-04-26"), 266.6,  269.06, 266.1,  268.15, 2564200),
                new Quote ( "IVV", fmt.parse("2018-04-25"), 264.7,  265.92, 262.67, 265.42, 3592400),
                new Quote ( "IVV", fmt.parse("2018-04-24"), 269.55, 269.8,  263.05, 264.89, 4818100),
                new Quote ( "IVV", fmt.parse("2018-04-23"), 269.16, 269.72, 267.19, 268.44, 3000000),
                new Quote ( "IVV", fmt.parse("2018-04-20"), 270.65, 270.89, 267.45, 268.44, 2883800),
                new Quote ( "IVV", fmt.parse("2018-04-19"), 271.48, 271.71, 269.55, 270.72, 2675300),
                new Quote ( "IVV", fmt.parse("2018-04-18"), 272.54, 273.14, 271.76, 272.19, 2766900),
                new Quote ( "IVV", fmt.parse("2018-04-17"), 271.18, 272.73, 270.58, 272.07, 2794800),
                new Quote ( "IVV", fmt.parse("2018-04-16"), 268.8,  270.03, 267.9,  269.19, 3311500),
                new Quote ( "IVV", fmt.parse("2018-04-13"), 269.3,  269.36, 265.8,  266.97, 4026400),
                new Quote ( "IVV", fmt.parse("2018-04-12"), 267.09, 268.82, 266.87, 267.79, 2994000),
                new Quote ( "IVV", fmt.parse("2018-04-11"), 265.27, 267.43, 265.18, 265.52, 2997600),
                new Quote ( "IVV", fmt.parse("2018-04-10"), 266.11, 267.86, 264.79, 266.97, 4190300),
                new Quote ( "IVV", fmt.parse("2018-04-09"), 263.23, 266.64, 262.31, 262.7,  3360400),
                new Quote ( "IVV", fmt.parse("2018-04-06"), 265.2,  266.94, 259.76, 261.57, 5700100),
                new Quote ( "IVV", fmt.parse("2018-04-05"), 267.35, 268.42, 266.14, 267.48, 3065900),
                new Quote ( "IVV", fmt.parse("2018-04-04"), 258.52, 266.17, 258.35, 265.63, 3770000),
                new Quote ( "IVV", fmt.parse("2018-04-03"), 260.63, 263.04, 258.6,  262.58, 6587300),
                new Quote ( "IVV", fmt.parse("2018-04-02"), 264.37, 264.93, 256.43, 259.21, 6102500),
                new Quote ( "IVV", fmt.parse("2018-03-29"), 262.86, 267.05, 262.07, 265.37, 5556100),
                new Quote ( "IVV", fmt.parse("2018-03-28"), 262.52, 264.44, 260.36, 261.62, 5095300),
                new Quote ( "IVV", fmt.parse("2018-03-27"), 267.98, 268.59, 260.61, 262.32, 5710900),
                new Quote ( "IVV", fmt.parse("2018-03-26"), 264.07, 267.24, 261.19, 267,    6544700),
                new Quote ( "IVV", fmt.parse("2018-03-23"), 266,    266.83, 259.61, 259.83, 5833600),
                new Quote ( "IVV", fmt.parse("2018-03-22"), 269.67, 270.73, 265.21, 265.47, 4727500),
                new Quote ( "IVV", fmt.parse("2018-03-21"), 273.98, 276.4,  273.3,  273.56, 4234400),
                new Quote ( "IVV", fmt.parse("2018-03-20"), 274.01, 274.76, 273.3,  274.06, 5915200),
                new Quote ( "IVV", fmt.parse("2018-03-19"), 276.49, 276.5,  271.72, 273.58, 4573200),
                new Quote ( "IVV", fmt.parse("2018-03-16"), 277.66, 278.53, 277.3,  277.3,  9106600),
                new Quote ( "IVV", fmt.parse("2018-03-15"), 277.96, 278.66, 276.47, 277.05, 3443500),
                new Quote ( "IVV", fmt.parse("2018-03-14"), 279.9,  280.1,  276.74, 277.34, 4153200),
                new Quote ( "IVV", fmt.parse("2018-03-13"), 281.93, 282.5,  278.11, 278.77, 5284300),
                new Quote ( "IVV", fmt.parse("2018-03-12"), 281.32, 282,    280.16, 280.59, 3277500),
                new Quote ( "IVV", fmt.parse("2018-03-09"), 277.77, 280.92, 277.39, 280.82, 5534400),
                new Quote ( "IVV", fmt.parse("2018-03-08"), 275.57, 276.26, 274.45, 276.13, 3448800),
                new Quote ( "IVV", fmt.parse("2018-03-07"), 272.43, 275.19, 272.23, 274.85, 4153800),
                new Quote ( "IVV", fmt.parse("2018-03-06"), 275.29, 275.42, 273.19, 274.95, 4385100),
                new Quote ( "IVV", fmt.parse("2018-03-05"), 269.71, 274.91, 269.59, 274.2,  4124400),
                new Quote ( "IVV", fmt.parse("2018-03-02"), 267.69, 271.71, 266.77, 271.2,  5459300),
                new Quote ( "IVV", fmt.parse("2018-03-01"), 273.46, 275.17, 267.95, 269.7,  7706000),
                new Quote ( "IVV", fmt.parse("2018-02-28"), 277.74, 278.23, 273.33, 273.34, 4951700),
                new Quote ( "IVV", fmt.parse("2018-02-27"), 280.2,  281.03, 276.41, 276.48, 6107300),
                new Quote ( "IVV", fmt.parse("2018-02-26"), 277.96, 280.06, 277.33, 279.96, 3803400),
                new Quote ( "IVV", fmt.parse("2018-02-23"), 273.85, 276.74, 273.29, 276.72, 6619800),
                new Quote ( "IVV", fmt.parse("2018-02-22"), 273.13, 275.07, 271.63, 272.37, 5544500),
                new Quote ( "IVV", fmt.parse("2018-02-21"), 273.9,  276.73, 271.98, 272,    8166800),
                new Quote ( "IVV", fmt.parse("2018-02-20"), 274.04, 275.69, 272.52, 273.41, 5578400),
                new Quote ( "IVV", fmt.parse("2018-02-16"), 274.36, 277.36, 274.23, 275.09, 4740400),
                new Quote ( "IVV", fmt.parse("2018-02-15"), 273.52, 275.03, 270.78, 275.02, 5461600),
                new Quote ( "IVV", fmt.parse("2018-02-14"), 266.36, 271.98, 266.22, 271.65, 6380300),
                new Quote ( "IVV", fmt.parse("2018-02-13"), 265.9,  268.56, 265.24, 267.96, 4210500),
                new Quote ( "IVV", fmt.parse("2018-02-12"), 265.67, 268.93, 263.82, 267.18, 6801300),
                new Quote ( "IVV", fmt.parse("2018-02-09"), 262.77, 265.54, 254.77, 263.67, 15080600),
                new Quote ( "IVV", fmt.parse("2018-02-08"), 269.94, 270.12, 259.46, 259.62, 9555800),
                new Quote ( "IVV", fmt.parse("2018-02-07"), 270.46, 274.36, 269.59, 269.59, 9318900),
                new Quote ( "IVV", fmt.parse("2018-02-06"), 261.88, 271.64, 260.57, 271.26, 16088300),
                new Quote ( "IVV", fmt.parse("2018-02-05"), 275.45, 277.86, 265.17, 266.05, 14698400),
                new Quote ( "IVV", fmt.parse("2018-02-02"), 282.1,  282.29, 277.44, 277.53, 6854000),
                new Quote ( "IVV", fmt.parse("2018-02-01"), 283.16, 285.16, 282.77, 283.79, 4554800),
                new Quote ( "IVV", fmt.parse("2018-01-31"), 284.8,  285.4,  282.76, 284.15, 6992100),
                new Quote ( "IVV", fmt.parse("2018-01-30"), 284.64, 285.38, 283.3,  283.65, 7424400),
                new Quote ( "IVV", fmt.parse("2018-01-29"), 288.11, 288.55, 286.62, 286.75, 4896200),
                new Quote ( "IVV", fmt.parse("2018-01-26"), 286.35, 288.69, 286.06, 288.66, 3379600),
                new Quote ( "IVV", fmt.parse("2018-01-25"), 286.22, 286.35, 284.5,  285.35, 4014500),
                new Quote ( "IVV", fmt.parse("2018-01-24"), 286.13, 286.8,  283.93, 285.24, 5149600),
                new Quote ( "IVV", fmt.parse("2018-01-23"), 284.82, 285.7,  284.48, 285.39, 3531100),
                new Quote ( "IVV", fmt.parse("2018-01-22"), 282.21, 284.76, 282.19, 284.73, 4499600),
                new Quote ( "IVV", fmt.parse("2018-01-19"), 281.89, 282.49, 281.21, 282.49, 4276100),
                new Quote ( "IVV", fmt.parse("2018-01-18"), 281.57, 282.01, 280.64, 281.26, 3463900),
                new Quote ( "IVV", fmt.parse("2018-01-17"), 280.05, 282.11, 279.24, 281.68, 4703400),
                new Quote ( "IVV", fmt.parse("2018-01-16"), 281.4,  282.15, 278.2,  278.9,  6537700),
                new Quote ( "IVV", fmt.parse("2018-01-12"), 278.48, 280.15, 278.34, 279.91, 4499600),
                new Quote ( "IVV", fmt.parse("2018-01-11"), 276.78, 278.13, 276.59, 278.09, 3142100),
                new Quote ( "IVV", fmt.parse("2018-01-10"), 275.72, 276.44, 274.94, 276.13, 3939800),
                new Quote ( "IVV", fmt.parse("2018-01-09"), 276.44, 277.26, 276.12, 276.57, 3150100),
                new Quote ( "IVV", fmt.parse("2018-01-08"), 275.37, 276.11, 274.99, 275.94, 2881800),
                new Quote ( "IVV", fmt.parse("2018-01-05"), 274.55, 275.56, 273.98, 275.33, 4150500),
                new Quote ( "IVV", fmt.parse("2018-01-04"), 273.23, 274.17, 273.11, 273.6,  3394700),
                new Quote ( "IVV", fmt.parse("2018-01-03"), 271.05, 272.63, 270.95, 272.42, 3768400),
                new Quote ( "IVV", fmt.parse("2018-01-02"), 269.84, 270.85, 269.37, 270.85, 8545600),
                new Quote ( "IVV", fmt.parse("2017-12-29"), 270.5,  270.5,  268.59, 268.85, 3368500),
                new Quote ( "IVV", fmt.parse("2017-12-28"), 269.8,  269.88, 269.41, 269.79, 2614200),
                new Quote ( "IVV", fmt.parse("2017-12-27"), 269.3,  269.68, 268.97, 269.29, 3319400),
                new Quote ( "IVV", fmt.parse("2017-12-26"), 269.04, 269.38, 268.85, 269.12, 2800200),
                new Quote ( "IVV", fmt.parse("2017-12-22"), 269.55, 269.6,  268.86, 269.46, 4577200),
                new Quote ( "IVV", fmt.parse("2017-12-21"), 269.68, 270.34, 269.27, 269.53, 3886200),
                new Quote ( "IVV", fmt.parse("2017-12-20"), 270.21, 270.3,  268.67, 268.99, 3016100)

        };

        return dataArray;
    }
}