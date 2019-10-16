package com.jpw.raptor.model;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by john on 9/10/18.
 */
@Getter
@Setter
public class Pp extends Quote {

    //
    // When the price of an asset is trading above the pivot point, it indicates the day is bullish or positive.
    // When the price of an asset is trading below the pivot point, it indicates the day is bearish or negative.
    //

    private String  datestr;

    private double pivotPoint;

    private double support1;
    private double resistance1;

    private double support2;
    private double resistance2;

    private double support3;
    private double resistance3;

    public Pp(Quote v) {

        super();
        setSymbol(v.getSymbol());
        setDate(v.getDate());
        setOpen(v.getOpen());
        setHigh(v.getHigh());
        setLow(v.getLow());
        setClose(v.getClose());
        setVolume(v.getVolume());

        pivotPoint  = 0.0;
        support1    = 0.0;
        resistance1 = 0.0;
        support2    = 0.0;
        resistance2 = 0.0;
        support3    = 0.0;
        resistance3 = 0.0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDatestr( sdf.format(v.getDate()) );
    }
}
