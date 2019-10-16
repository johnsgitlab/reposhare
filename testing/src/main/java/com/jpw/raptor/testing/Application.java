package com.jpw.raptor.testing;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import com.jpw.raptor.algorithm.*;
import com.jpw.raptor.algorithm.signals.*;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.hiyieldspread.HiYieldSpreadDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.jdbc.treasury.TreasuryDAO;
import com.jpw.raptor.lib.properties.FinanceProperties;

import com.jpw.raptor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


/**
 * Created by john on 8/30/18.
 */
@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class Application implements CommandLineRunner {

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

    @Autowired
    public TreasuryDAO treasuryTbl;

    @Autowired
    public HiYieldSpreadDAO hiYieldSpreadTbl;

    // Main loop
    @Override
    public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("***************  Start Test  *******************");

        HiYieldTest tester = new HiYieldTest();
        tester.processFile(hiYieldSpreadTbl);

        //IngestTest worker = new IngestTest();
        //worker.doit(quoteTbl, etfTbl, fundTbl, stockTbl);

        //TreasuryTest worker = new TreasuryTest();
        //worker.doit(quoteTbl, etfTbl, fundTbl, stockTbl, treasuryTbl);

        //DataBaseTester worker = new DataBaseTester();
        //worker.doit(quoteTbl, etfTbl, fundTbl, stockTbl);

        //StockCreator worker = new StockCreator();
        //worker.doit(stockTbl);

        //StockList worker = new StockList();
        //worker.doit(stockTbl);

        //StockScrapper worker = new StockScrapper();
        //worker.doit(stockTbl, "AAPL");

        System.out.println("***************  End Test  *******************");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        //SpringApplication app = new SpringApplication(IngestApplication.class);
        //app.setBannerMode(Banner.Mode.OFF);
        //app.run(args);
        System.exit(SpringApplication
                .exit(SpringApplication.run(Application.class, args)));
    }

}

