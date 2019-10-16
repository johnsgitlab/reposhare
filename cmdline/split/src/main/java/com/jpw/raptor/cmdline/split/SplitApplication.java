package com.jpw.raptor.cmdline.split;


import com.beust.jcommander.JCommander;
import com.jpw.raptor.algorithm.EquityPerformance;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.model.*;
import com.jpw.raptor.jdbc.index.IndexDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class SplitApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    public QuoteDAO quoteTbl;

    @Autowired
    public EtfDAO etfTbl;

    @Autowired
    public FundDAO fundTbl;

    // Main loop
    @Override
    public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("***************  Hello  *******************");

        // define the run time parameters
        AppParameters params = new AppParameters();

        // create parameter parser
        JCommander cmd = new JCommander(params);

        // parse the arguments
        cmd.parse(args);

        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");

        Date    dateValue   = format.parse(params.getDate());
        double  adjust      = Double.parseDouble(params.getSplit());

        // get the quotes to adjust
        List<Quote> recs = quoteTbl.getForSplitDesc(params.getSymbol(), dateValue);

        // adjust the quotes
        SplitFactory sf = new SplitFactory();
        sf.adjust(recs, adjust);

        // update the database
        for ( Quote rec : recs ) {
            quoteTbl.update(rec);
        }

        // Worker to compute performance
        EquityPerformance ep = new EquityPerformance();

        if ( params.getType().equalsIgnoreCase("etf") ) {
            //
            // Update Equity performance
            Etf etfRec = etfTbl.get(params.getSymbol().toUpperCase());
            if (etfRec != null) {
                List<Quote> quotes = quoteTbl.getYearsWorthDesc(etfRec.getSymbol());
                Performance rec = ep.computePerformance(etfRec.getSymbol(), quotes);
                etfRec.updatePerformance(rec);
                etfTbl.update(etfRec);
            }

        } else {
            // must be a mutual fund
            // Update Equity performance
            Fund fundRec = fundTbl.get(params.getSymbol().toUpperCase());
            if (fundRec != null) {
                List<Quote> quotes = quoteTbl.getYearsWorthDesc(fundRec.getSymbol());
                Performance rec =  ep.computePerformance(fundRec.getSymbol(), quotes);
                fundRec.updatePerformance(rec);
                fundTbl.update(fundRec);
            }
        }

        System.out.println("***************  Good bye  *******************");
        System.out.println();

    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(SplitApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

}
