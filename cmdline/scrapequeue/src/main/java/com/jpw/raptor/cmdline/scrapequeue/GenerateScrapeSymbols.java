package com.jpw.raptor.cmdline.scrapequeue;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Fund;
import com.jpw.raptor.model.Stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by john on 2/19/19.
 */
public class GenerateScrapeSymbols {

    public List<String> getEtfSymbols(EtfDAO tbl, String type) {

        List<Etf> equities;

        if        ( type.equalsIgnoreCase("all") ) {
            equities = tbl.getAll();
        } else if ( type.equalsIgnoreCase("relevant") ) {
            equities = tbl.getRelevant();
        } else if ( type.equalsIgnoreCase("tracked") ) {
            equities = tbl.getTracked();
        } else if ( type.equalsIgnoreCase("own") ) {
            equities = tbl.getOwned();
        } else  {
            equities = Collections.emptyList();
        }

        ArrayList<String> symbols = new ArrayList<String>(equities.size());

        for ( Etf equity : equities ) {
            symbols.add(equity.getSymbol());
        }

        return symbols;
    }


    public List<String> getOneEtfSymbol(EtfDAO tbl, String symbol) {

        List<String> symbols;

        Etf equity = tbl.get(symbol);

        if ( equity != null ) {
            symbols = new ArrayList<String>(1);
            symbols.add(equity.getSymbol());
        } else {
            symbols = Collections.emptyList();
        }

        return symbols;
    }


    public List<String> getFundSymbols(FundDAO tbl, String type) {

        List<Fund> equities;

        if        ( type.equalsIgnoreCase("all") ) {
            equities = tbl.getAll();
        } else if ( type.equalsIgnoreCase("relevant") ) {
            equities = tbl.getRelevant();
        } else if ( type.equalsIgnoreCase("tracked") ) {
            equities = tbl.getTracked();
        } else if ( type.equalsIgnoreCase("own") ) {
            equities = tbl.getOwned();
        } else  {
            equities = Collections.emptyList();
        }

        ArrayList<String> symbols = new ArrayList<String>(equities.size());


        for ( Fund equity : equities ) {
            symbols.add(equity.getSymbol());
        }

        return symbols;
    }


    public List<String> getOneFundSymbol(FundDAO tbl, String symbol) {

        List<String> symbols;

        Fund equity = tbl.get(symbol);

        if ( equity != null ) {
            symbols = new ArrayList<String>(1);
            symbols.add(equity.getSymbol());
        } else {
            symbols = Collections.emptyList();
        }

        return symbols;
    }


    public List<String> getStockSymbols(StockDAO tbl, String type) {

        List<Stock> equities;

        if        ( type.equalsIgnoreCase("all") ) {
            equities = tbl.getAll();
        } else if ( type.equalsIgnoreCase("relevant") ) {
            equities = tbl.getRelevant();
        } else if ( type.equalsIgnoreCase("tracked") ) {
            equities = tbl.getTracked();
        } else if ( type.equalsIgnoreCase("own") ) {
            equities = tbl.getOwned();
        } else  {
            equities = Collections.emptyList();
        }

        ArrayList<String> symbols = new ArrayList<String>(equities.size());

        for ( Stock equity : equities ) {
            symbols.add(equity.getSymbol());
        }

        return symbols;
    }


    public List<String> getOneStockSymbol(StockDAO tbl, String symbol) {

        List<String> symbols;

        Stock equity = tbl.get(symbol);

        if ( equity != null ) {
            symbols = new ArrayList<String>(1);
            symbols.add(equity.getSymbol());
        } else {
            symbols = Collections.emptyList();
        }

        return symbols;
    }
}

