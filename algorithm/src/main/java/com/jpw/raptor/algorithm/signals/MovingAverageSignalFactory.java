package com.jpw.raptor.algorithm.signals;

import com.jpw.raptor.model.AveragesModel;

import java.text.DecimalFormat;

/**
 * Created by john on 7/4/18.
 */
public class MovingAverageSignalFactory {


    public MovingAverageSignalFactory() {}

    public double computeSlope(double current, double prior) {

        // We define slope as the percentage of price change
        double result = 999.0;
        if ( prior != 0.0 ) {
            // Round to 2 decimal places
            DecimalFormat df = new DecimalFormat("#.##");
            result = Double.valueOf(df.format( ((current - prior) / prior) * 100.0 ));
        }
        return result;
    }

    public String get_simple_ma_signal(AveragesModel rec) {

        String  signal = new String("");

        // Get averages and close
        double close  = rec.getClose();
        double avg200 = rec.getSimple200();
        double avg50  = rec.getSimple50();
        double avg20  = rec.getSimple20();
        double avg10  = rec.getSimple10();
        double avg5   = rec.getSimple5();

        if ( close >= avg200 ) {
            // Compute up trend signal
            if ( avg5 >= avg10 && avg10 >= avg20 && avg20 >= avg50 && avg50 >= avg200 ) {
                if  ( close >= avg5 ) {
                    signal = "95 Close above 5";
                } else if ( close >= avg10 ) {
                    signal = "90 Close above 10";
                } else if ( close >= avg20 ) {
                    signal = "80 Close above 20";
                } else if ( close >= avg50 ) {
                    signal = "65 Close above 50";
                } else {
                    signal = "45 Close above 200";
                }
                signal += "; Trend 5 > 10 > 20 > 50 > 200 ";
            }

            else if ( avg10 >= avg20 && avg20 >= avg50 && avg50 >= avg200 ) {
                if ( close >= avg10 ) {
                    signal = "85 Close above 10";
                } else if ( close >= avg20 ) {
                    signal = "75 Close above 20";
                } else if ( close >= avg50 ) {
                    signal = "60 Close above 50";
                } else {
                    signal = "40 Close above 200";
                }
                signal += "; Trend 10 > 20 > 50 > 200 ";
            }

            else if ( avg20 >= avg50 && avg50 >= avg200 ) {
                if ( close >= avg20 ) {
                    signal = "70 Close above 20";
                } else if ( close >= avg50 ) {
                    signal = "55 Close above 50";
                } else {
                    signal = "35 Close above 200";
                }
                signal += "; Trend 20 > 50 > 200";
            }
            else if ( avg50 >= avg200 ) {
                if ( close >= avg50 ) {
                    signal = "50 Close above 50";
                } else {
                    signal = "30 Close above 200";
                }
                signal += "; Trend 50 > 200";
            }
            else
            {
                signal = "28 Close above 200";
            }
        }
        else {
            // Compute down trend signal
            if ( avg200 >= avg50 && avg50 >= avg20 && avg20 >= avg10 && avg10 >=  avg5 ) {
                signal = "05 Close below 200; Trend 200 > 50 > 20 > 10 > 5";
            }

            else if ( avg200 >= avg50 && avg50 >= avg20 && avg20 >= avg10 ) {
                signal = "10 Close below 200; Trend 200 > 50 > 20 > 10";
            }

            else if ( avg200 >= avg50 && avg50 >= avg20 ) {
                signal = "15 Close below 200; Trend 200 > 50 > 20 ";
            }

            else if ( avg200 >= avg50 ) {
                signal = "20 Close below 200; Trend 200 > 50";
            }
            else
            {
                signal = "25 Close below 200";
            }
        }

        return signal;
    }

}
