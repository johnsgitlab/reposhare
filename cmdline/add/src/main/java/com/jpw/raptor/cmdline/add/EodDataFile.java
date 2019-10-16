package com.jpw.raptor.cmdline.add;

import com.jpw.raptor.algorithm.EquityPerformance;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;

import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by john on 3/25/18.
 */
@Component
public class EodDataFile {

    @Autowired
    public IndexDAO                 indexTbl;

    @Autowired
    public EtfDAO                   etfTbl;

    @Autowired
    public FundDAO                  fundTbl;

    @Autowired
    public QuoteDAO                 quoteTbl;

    @Autowired
    public StockDAO                 stockTbl;


    public HashMap<String, String>  symbols;

    public List<Index>              indexList;

    public List<Etf>                etfList;

    public List<Fund>               fundList;

    public List<Stock>              stockList;

    // must be manually called
    // This method is only called once based on
    // parameters pased to the application
    public void postConstruct(String type, String symbol) {

        symbols = new HashMap<>();

        indexList = new ArrayList<>(1);
        if ( type.equalsIgnoreCase("index") ) {
            // Create dummy record and add to database
            Index newRec = new Index();
            newRec.setSymbol(symbol.toUpperCase());
            indexTbl.addEmpty(symbol.toUpperCase());

            indexList.add( newRec );
            symbols.put(symbol.toUpperCase(), "index");
        }

        etfList = new ArrayList<>(1);
        if ( type.equalsIgnoreCase("etf") ) {
            // Create dummy record and add to database
            Etf newRec = new Etf();
            newRec.setSymbol(symbol.toUpperCase());
            etfTbl.addEmpty(symbol.toUpperCase());

            etfList.add( newRec );
            symbols.put(symbol.toUpperCase(), "etf");
        }

        fundList = new ArrayList<>(1);
        if ( type.equalsIgnoreCase("fund") ) {
            // Create dummy record and add to database
            Fund newRec = new Fund();
            newRec.setSymbol(symbol.toUpperCase());
            fundTbl.addEmpty(symbol.toUpperCase());

            fundList.add( newRec );
            symbols.put(symbol.toUpperCase(), "fund");
        }

        stockList = new ArrayList<>(1);
        if ( type.equalsIgnoreCase("stock") ) {
            // Create dummy record and add to database
            Stock newRec = new Stock();
            newRec.setSymbol(symbol.toUpperCase());

            //
            // To prevent a conflict with merging assume that the stock
            // record is already added
            // stockTbl.addEmpty(symbol.toUpperCase(), "", "", "", "")

            stockList.add( newRec );
            symbols.put(symbol.toUpperCase(), "stock");
        }

    }

    public void testit() {

        System.out.println("***************************************************************************" );
        System.out.println("Symbols TO PROCESS " + symbols.size());
        System.out.println("***************************************************************************" );

    }


    public void processFile(FileQualified fileQualified) {

        // Used to read Eod data file
        BufferedReader br      = null;

        // Used to parse the EOD data file
        EodParser parser = new EodParser();

        try
        {
            String val = null;

            br  = new BufferedReader(new FileReader(fileQualified.getName()));

            // Read each record in the file
            while ((val = br.readLine()) != null)
            {
                Quote rec = parser.parseCsvLine (val);

                // parse data entry
                if ( rec != null ) {

                    // Only process known equities
                    String symbolType = symbols.get(rec.getSymbol());
                    if ( symbolType != null ) {

                        if ( symbolType.equalsIgnoreCase("fund")) {
                           if ( fileQualified.getType().equalsIgnoreCase("USMF") ) {
                               quoteTbl.add(rec);
                           }
                        }
                        else if ( symbolType.equalsIgnoreCase("etf")) {
                            if ( fileQualified.getType().equalsIgnoreCase("AMEX") ||
                                    fileQualified.getType().equalsIgnoreCase("NASDAQ") ) {
                                quoteTbl.add(rec);
                            }
                        } else if (symbolType.equalsIgnoreCase("stock")) {
                            if (fileQualified.getType().equalsIgnoreCase("AMEX") ||
                                fileQualified.getType().equalsIgnoreCase("NASDAQ") ||
                                fileQualified.getType().equalsIgnoreCase("NYSE")) {
                                quoteTbl.add(rec);
                            }
                        } else if ( symbolType.equalsIgnoreCase("index") ) {
                            if ( fileQualified.getType().equalsIgnoreCase("INDEX") ) {
                                quoteTbl.add(rec);
                            }
                        } else {
                            // dont process
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }


    public void updatePerformance() {

        // Worker to compute performance
        EquityPerformance ep = new EquityPerformance();

        // update etf performance
        for ( Etf equity : etfList) {
            List<Quote> quotes = quoteTbl.getYearsWorthDesc(equity.getSymbol());
            Performance rec = ep.computePerformance(equity.getSymbol(),  quotes);
            equity.updatePerformance(rec);
            etfTbl.update(equity);
        }

        // update fund performance
        for ( Fund equity : fundList) {
            List<Quote> quotes = quoteTbl.getYearsWorthDesc(equity.getSymbol());
            Performance rec = ep.computePerformance(equity.getSymbol(),  quotes);
            equity.updatePerformance(rec);
            fundTbl.update(equity);
        }

        // update stock performance
        System.out.println("Stock Performance " );
        for ( Stock equity : stockList) {
            List<Quote> quotes = quoteTbl.getYearsWorthDesc(equity.getSymbol());
            Performance rec = ep.computePerformance(equity.getSymbol(), quotes);
            equity.updatePerformance(rec);
            stockTbl.update(equity);
        }
    }

}
