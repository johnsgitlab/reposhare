package com.jpw.raptor.testing;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.Stock;
import com.jpw.raptor.scrape.yahoostock.YahooStockPageContents;
import com.jpw.raptor.scrape.yahoostock.YahooStockPageScrapper;

import java.util.HashMap;

/**
 * Created by john on 11/6/18.
 */
public class StockScrapper {

    public void doit(StockDAO stockTbl, String symbol) {

        YahooStockPageScrapper yps = new YahooStockPageScrapper();

        Stock stock = stockTbl.get(symbol);

        YahooStockPageContents ypc = yps.readPage(symbol);




        ypc.updateStockFromYahoo(stock);

        stockTbl.update(stock);
    }
}
