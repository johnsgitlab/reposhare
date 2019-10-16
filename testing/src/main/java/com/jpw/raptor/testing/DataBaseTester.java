package com.jpw.raptor.testing;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.ListModel;
import com.jpw.raptor.model.Quote;
import com.jpw.raptor.model.Stock;
import com.jpw.raptor.scrape.yahoostock.YahooStockPageContents;
import com.jpw.raptor.scrape.yahoostock.YahooStockPageScrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 11/12/18.
 */
public class DataBaseTester {

    @Autowired
    public QuoteDAO quoteTbl;

    @Autowired
    public EtfDAO etfTbl;

    @Autowired
    public FundDAO fundTbl;

    @Autowired
    public IndexDAO indexTbl;

    @Autowired
    public StockDAO stockTbl;

    public void doit(QuoteDAO quoteTbl, EtfDAO etfTbl, FundDAO fundTbl, StockDAO stockTbl) {


        SimpleDateFormat simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse("2018-01-08");
        } catch ( ParseException ex ) {}


        Quote quote = quoteTbl.get("VIG", date );

        System.out.println("Quote read " + quote.getSymbol());

        quoteTbl.add(quote);

    }
}
