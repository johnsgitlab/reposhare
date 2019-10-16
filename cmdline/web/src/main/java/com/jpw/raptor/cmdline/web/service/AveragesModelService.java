package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.algorithm.FractalMovingAverage;
import com.jpw.raptor.algorithm.KaufmanMovingAverage;
import com.jpw.raptor.algorithm.SimpleMovingAverage;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.model.AveragesModel;
import com.jpw.raptor.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AveragesModelService {

    private static final int PERIODS            = 10;
    private static final int FAST_PERIODS       = 2;
    private static final int SLOW_PERIODS       = 30;

    private static final int FRACTAL_PERIODS    = 10;

    private static final int DAYS_5             = 5;
    private static final int DAYS_10            = 10;
    private static final int DAYS_20            = 20;
    private static final int DAYS_50            = 50;
    private static final int DAYS_100           = 100;
    private static final int DAYS_200           = 200;


    @Autowired
    QuoteDAO quoteTbl;

    public List<AveragesModel> generateList(String symbol) {

        // get the quotes
        List<Quote> quotes  = quoteTbl.getAllDesc(symbol.toUpperCase());

        // worker objects
        KaufmanMovingAverage kaufman = new KaufmanMovingAverage();
        FractalMovingAverage fractal = new FractalMovingAverage();
        SimpleMovingAverage  simple  = new SimpleMovingAverage();

        // Allocate space for the list
        List<AveragesModel> averages = new ArrayList<>(quotes.size());

        // create the list
        for ( int i=0; i<quotes.size(); i++ ) {

            // create default averages object
            AveragesModel avg = new AveragesModel(quotes.get(i));

            // add to the list
            averages.add(avg);
        }

        // Since dates are descending start at the end of the array
        // and process to the front
        int start = quotes.size() - 1;

        // create averages for each quote
        // Note the first entry values have has already been set
        for ( int i=start-1; i>=0; i-- ) {
            AveragesModel avg = averages.get(i);

            avg.setKaufman(
                kaufman.kaufmanMovingAverageDesc(
                        quotes, i, PERIODS, FAST_PERIODS, SLOW_PERIODS, averages.get(i+1).getKaufman()
                )
            );

            avg.setFractal(
                    fractal.fractalMovingAverage(quotes, i, FRACTAL_PERIODS, averages.get(i+1).getFractal())
            );

            avg.setSimple5  ( simple.smaQuote(quotes, i, DAYS_5) );
            avg.setSimple10 ( simple.smaQuote(quotes, i, DAYS_10) );
            avg.setSimple20 ( simple.smaQuote(quotes, i, DAYS_20) );
            avg.setSimple50 ( simple.smaQuote(quotes, i, DAYS_50) );
            avg.setSimple100( simple.smaQuote(quotes, i, DAYS_100) );
            avg.setSimple200( simple.smaQuote(quotes, i, DAYS_200) );

        }

        return averages;

    }

}
