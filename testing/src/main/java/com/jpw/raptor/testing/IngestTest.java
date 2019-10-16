package com.jpw.raptor.testing;

import com.jpw.raptor.algorithm.EquityPerformance;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.Performance;
import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.Stock;

import java.util.List;

public class IngestTest {

    public List<Stock> stockList;

    public void doit(QuoteDAO quoteTbl, EtfDAO etfTbl, FundDAO fundTbl, StockDAO stockTbl) {

        stockList = stockTbl.getAll();

        // Worker to compute performance
        EquityPerformance ep = new EquityPerformance();

        for ( Stock equity : stockList) {
            System.out.println("Stock performance " + equity.getSymbol());
            List<Quote> quotes = quoteTbl.getYearsWorthDesc(equity.getSymbol());
            Performance rec = ep.computePerformance(equity.getSymbol(), quotes);
            stockTbl.update(equity);
        }
    }

}
