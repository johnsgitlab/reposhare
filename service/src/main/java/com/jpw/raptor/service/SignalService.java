package com.jpw.raptor.service;

import com.jpw.raptor.algorithm.signals.MovingAverageSignalFactory;
import com.jpw.raptor.algorithm.SimpleMovingAverage;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;

import com.jpw.raptor.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is not tested and should have logic errors with
 * quotes not in the proper order
 */
@Service
public class SignalService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    QuoteDAO quoteTbl;

    //
    // Generate equity description
    protected String getCategory(EtfFundBase equity) {

        String category = equity.getAssetClass() + " " + equity.getFundType();

        if ( equity.getFundSubType() != null && !equity.getFundSubType().equalsIgnoreCase("null")) {
            category = category + " " + equity.getFundSubType();
        } else if ( equity.getFactor() != null && !equity.getFactor().equalsIgnoreCase("null")) {
            category = category + " " + equity.getFactor();
        }

        return category.toUpperCase();
    }


    public SignalModel getEtfMovingAverageSignal(Etf equity, List<Quote> quotes) {

        SimpleMovingAverage         sma = new SimpleMovingAverage();
        MovingAverageSignalFactory  mas = new MovingAverageSignalFactory();

        // Init Averages record
        AveragesModel avg = new AveragesModel();
        avg.setSymbol(equity.getSymbol());
        avg.setDate(quotes.get(0).getDate());
        avg.setOpen(quotes.get(0).getOpen());
        avg.setHigh(quotes.get(0).getHigh());
        avg.setLow(quotes.get(0).getLow());
        avg.setClose(quotes.get(0).getClose());
        avg.setVolume(quotes.get(0).getVolume());

        // Generate Simple moving averages
        avg.setSimple5(sma.simpleMovingAverageDesc(quotes, 0, 5));
        avg.setSimple10(sma.simpleMovingAverageDesc(quotes, 0, 10));
        avg.setSimple20(sma.simpleMovingAverageDesc(quotes, 0, 20));
        avg.setSimple50(sma.simpleMovingAverageDesc(quotes, 0, 50));
        avg.setSimple100(sma.simpleMovingAverageDesc(quotes, 0, 100));
        avg.setSimple200(sma.simpleMovingAverageDesc(quotes, 0, 200));

        // Generate Signal
        SignalModel model = new SignalModel();
        model.setSymbol(avg.getSymbol());
        model.setAssetClass(equity.getAssetClass());
        model.setFundType(equity.getFundType());
        model.setFundSubType(equity.getFundSubType());
        model.setFactor(equity.getFactor());
        model.setCategory(equity.getCategory());
        model.setStrategy("moving average");
        model.setSignal(mas.get_simple_ma_signal(avg));
        model.setDate(avg.getDate());

        return model;
    }


    public List<SignalModel> getEtfMovingAverageSignals(List<Etf> etfs) {

        List<SignalModel> signals = new ArrayList<>(etfs.size());

        for ( Etf equity : etfs ) {
            List<Quote> quotes = quoteTbl.getYearsWorthDesc(equity.getSymbol());
            signals.add(getEtfMovingAverageSignal(equity, quotes));
        }

        return signals;
    }



    public SignalModel getFundMovingAverageSignal(Fund equity, List<Quote> quotes) {

        SimpleMovingAverage         sma = new SimpleMovingAverage();
        MovingAverageSignalFactory  mas = new MovingAverageSignalFactory();

        // Init Averages record
        AveragesModel avg = new AveragesModel();
        avg.setSymbol(equity.getSymbol());
        avg.setDate(quotes.get(0).getDate());
        avg.setOpen(quotes.get(0).getOpen());
        avg.setHigh(quotes.get(0).getHigh());
        avg.setLow(quotes.get(0).getLow());
        avg.setClose(quotes.get(0).getClose());
        avg.setVolume(quotes.get(0).getVolume());

        // Generate Simple moving averages
        avg.setSimple5(sma.simpleMovingAverageDesc(quotes, 0, 5));
        avg.setSimple10(sma.simpleMovingAverageDesc(quotes, 0, 10));
        avg.setSimple20(sma.simpleMovingAverageDesc(quotes, 0, 20));
        avg.setSimple50(sma.simpleMovingAverageDesc(quotes, 0, 50));
        avg.setSimple100(sma.simpleMovingAverageDesc(quotes, 0, 100));
        avg.setSimple200(sma.simpleMovingAverageDesc(quotes, 0, 200));

        // Generate Signal
        SignalModel model = new SignalModel();
        model.setSymbol(avg.getSymbol());

        model.setAssetClass(equity.getAssetClass());
        model.setFundType(equity.getFundType());
        model.setFundSubType(equity.getFundSubType());
        model.setFactor(equity.getFactor());
        model.setCategory(equity.getCategory());
        model.setStrategy("moving average");
        model.setSignal(mas.get_simple_ma_signal(avg));
        model.setDate(avg.getDate());

        return model;
    }


    public List<SignalModel> getFundMovingAverageSignals(List<Fund> funds) {

        List<SignalModel> signals = new ArrayList<>(funds.size());

        for ( Fund equity : funds ) {
            // get quotes
            List<Quote> quotes = quoteTbl.getYearsWorthDesc(equity.getSymbol());
            signals.add(getFundMovingAverageSignal(equity, quotes));
        }

        return signals;
    }

}
