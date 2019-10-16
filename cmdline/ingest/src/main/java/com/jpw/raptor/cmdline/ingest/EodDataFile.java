package com.jpw.raptor.cmdline.ingest;

import com.jpw.raptor.algorithm.EquityPerformance;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;

import com.jpw.raptor.jdbc.treasury.TreasuryDAO;
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
    public StockDAO                 stockTbl;

    @Autowired
    public QuoteDAO                 quoteTbl;

    @Autowired
    public TreasuryDAO              treasuryTbl;

    public HashMap<String, String>  symbols;

    public List<Index>              indexList;

    public List<Etf>                etfList;

    public List<Fund>               fundList;

    public List<Stock>              stockList;


    @PostConstruct
    public void postConstruct() {
        symbols = new HashMap<String, String>();

        etfList = etfTbl.getAll();
        for ( Etf v : etfList) {
            if ( symbols.containsKey(v.getSymbol())) {
                System.out.println("SYMBOL CLASH " + v.getSymbol());
            } else {
                symbols.put(v.getSymbol(), "etf");
            }
        }

        stockList = stockTbl.getAll();
        for ( Stock v : stockList) {
            if ( symbols.containsKey(v.getSymbol())) {
                System.out.println("SYMBOL CLASH " + v.getSymbol());
            } else {
                symbols.put(v.getSymbol(), "stock");
            }
        }

        fundList = fundTbl.getAll();
        for ( Fund v : fundList) {
            if ( symbols.containsKey(v.getSymbol())) {
                System.out.println("SYMBOL CLASH " + v.getSymbol());
            } else {
                symbols.put(v.getSymbol(), "fund");
            }
        }

        indexList = indexTbl.getAll();
        for ( Index v : indexList) {
            if ( symbols.containsKey(v.getSymbol())) {
                System.out.println("SYMBOL CLASH " + v.getSymbol());
            } else {
                symbols.put(v.getSymbol(), "index");
            }
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
                    if (rec.getClose() == 0.0 || rec.getOpen() == 0.0 ||
                            rec.getHigh() == 0.0 || rec.getLow() == 0.0) {
                        // do not process invalid data
                    } else {

                        // Only process known equities
                        String symbolType = symbols.get(rec.getSymbol());
                        if (symbolType != null) {

                            if (symbolType.equalsIgnoreCase("fund")) {
                                if (fileQualified.getType().equalsIgnoreCase("USMF")) {
                                    quoteTbl.add(rec);
                                }
                            } else if (symbolType.equalsIgnoreCase("etf")) {
                                if (fileQualified.getType().equalsIgnoreCase("AMEX") ||
                                        fileQualified.getType().equalsIgnoreCase("NASDAQ")) {
                                    quoteTbl.add(rec);
                                }
                            } else if (symbolType.equalsIgnoreCase("stock")) {
                                if (fileQualified.getType().equalsIgnoreCase("AMEX") ||
                                        fileQualified.getType().equalsIgnoreCase("NASDAQ") ||
                                        fileQualified.getType().equalsIgnoreCase("NYSE")) {
                                    quoteTbl.add(rec);
                                }
                            } else if (symbolType.equalsIgnoreCase("index")) {
                                if (fileQualified.getType().equalsIgnoreCase("INDEX")) {
                                    quoteTbl.add(rec);
                                }
                            } else {
                                // dont process
                            }
                        }
                    }
                } else {
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
        System.out.println("ETF Performance " );
        for ( Etf equity : etfList) {
            List<Quote> quotes = quoteTbl.getYearsWorthDesc(equity.getSymbol());
            Performance rec = ep.computePerformance(equity.getSymbol(), quotes);
            equity.updatePerformance(rec);
            etfTbl.update(equity);
        }

        // update fund performance
        System.out.println("Fund Performance " );
        for ( Fund equity : fundList) {
            List<Quote> quotes = quoteTbl.getYearsWorthDesc(equity.getSymbol());
            Performance rec = ep.computePerformance(equity.getSymbol(), quotes);
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

    public void updateTreasuries() {
        ProcessTreasury processTreasury = new ProcessTreasury();
        processTreasury.process(treasuryTbl);
    }
}
