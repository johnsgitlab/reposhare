package com.jpw.raptor.analytic;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import com.jpw.raptor.algorithm.AvgDirMovementIndicator;
import com.jpw.raptor.algorithm.BollingerBand;
import com.jpw.raptor.algorithm.SimpleMovingAverage;
import com.jpw.raptor.algorithm.StochasticOscillator;
import com.jpw.raptor.algorithm.signals.*;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
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
import java.util.List;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class AnalyticApplication implements CommandLineRunner {

    //////////////////////////////////////////////////////////////////////////////////////
    //
    // Must be rewritten for ascending quote order
    //
    /////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    public QuoteDAO quoteTbl;

    @Autowired
    public EtfDAO etfTbl;

    public void generateSpreadSheet() throws IOException {

        System.out.println();
        System.out.println("***************  Generate Spread sheet  *******************");

        String symbol   = "SPY";

        // Application properties
        FinanceProperties fp   = new FinanceProperties();
        Properties prop = fp.get();

        String dir  = prop.getProperty("analysis_export_dir");
        if ( dir == null )
        {
            dir = "/home/finance/analysis";
        }

        dir = "/home/finance/analysis";
        String fileName = symbol;

        // Get a list of quotes
        List<Quote> quotes = quoteTbl.getForAnalyticsDesc(symbol);

        SpreadSheet                 spreadSheet          = new SpreadSheet();

        // Generate ADX
        AvgDirMovementIndicator     oscillator              = new AvgDirMovementIndicator();
        AdxSignalFactory            adxSignalFactory        = new AdxSignalFactory();
        List<Adx>                   adxList                 = oscillator.generateAdx(quotes, 14);

        // Generate Stochastoc
        StochasticOscillator        stoOscillator           = new StochasticOscillator();
        StochasticSignalFactory     stoSignalFactory        = new StochasticSignalFactory();
        List<Stochastic>            stochasticList          = stoOscillator.generateStochastic(quotes, 14);

        // Generate moving averages
        SimpleMovingAverage         sma                     = new SimpleMovingAverage();
        MovingAverageSignalFactory  smaSignalFactory        = new MovingAverageSignalFactory();
        List<AveragesModel>         averagesList            = sma.generateAverages(quotes);

        // Generate Bollinger bans
        BollingerBand               bollingerBand           = new BollingerBand();
        BollingerSignalFactory      bollingerSignalFactory  = new BollingerSignalFactory();
        List<Bollinger>             bollingerList           = bollingerBand.generateBollinger(quotes, 14);

        // Generate entry and exit signals
        TradingSignalFactory        tradingSignalFactory    = new TradingSignalFactory();

        // Generate 4 months of signals
        for ( int i=0; i<150; i++ ) {

            Adx              adx         = adxList.get(i);
            AdxSignal        adxSignal   = adxSignalFactory.getSignal(adxList, i);
            Stochastic       stochastic  = stochasticList.get(i);
            StochasticSignal stoSignal   = stoSignalFactory.getSignal(stochasticList, i);
            BollingerSignal  bollSignal  = bollingerSignalFactory.getSignal(bollingerList, i);
            String           smaSignal   = smaSignalFactory.get_simple_ma_signal(averagesList.get(i));

            TradeSignal      tradeSignal = tradingSignalFactory.getSignal(averagesList, adxList, stochasticList, i );

            spreadSheet.addRow(adx, adxSignal, stoSignal, bollSignal, smaSignal, tradeSignal);

        }

        // write spreadsheet to file
        spreadSheet.writeToFile(dir, fileName);
    }


    public void generateAdxSpreadSheet() throws IOException {

        System.out.println();
        System.out.println("***************  Generate ADX Signals  *******************");


        // Application properties
        FinanceProperties fp   = new FinanceProperties();
        Properties prop = fp.get();

        String dir  = prop.getProperty("analysis_export_dir");
        if ( dir == null )
        {
            dir = "/home/finance/analysis";
        }

        dir = "/home/finance/analysis";
        String fileName = "adx";

        // Get a list of quotes
        List<Quote> quotes = quoteTbl.getForAnalyticsDesc("IVV");

        // Generate Stochastoc
        AvgDirMovementIndicator oscillator       = new AvgDirMovementIndicator();
        AdxSignalFactory        adxSignalFactory = new AdxSignalFactory();
        AdxSpreadSheet          adxSpreadSheet   = new AdxSpreadSheet();
        List<Adx>               adxList          = oscillator.generateAdx(quotes, 14);

        // Generate signals
        for ( int i=0; i<100; i++ ) {

            Adx       adx        = adxList.get(i);
            AdxSignal signal     = adxSignalFactory.getSignal(adxList, i);
            adxSpreadSheet.addRow(adx, signal);
        }

        // write spreadsheet to file
        adxSpreadSheet.writeToFile(dir, fileName);
    }


    public void generateStochasticSpreadSheet() throws IOException {

        System.out.println();
        System.out.println("***************  Generate Stochastic Signals  *******************");


        // Application properties
        FinanceProperties fp   = new FinanceProperties();
        Properties prop = fp.get();

        String dir  = prop.getProperty("analysis_export_dir");
        if ( dir == null )
        {
            dir = "/home/finance/analysis";
        }

        dir = "/home/finance/analysis";
       String fileName = "stochastic";

        // Get a list of quotes
        List<Quote> quotes = quoteTbl.getForAnalyticsDesc("IVV");

        // Generate Stochastoc
        StochasticOscillator    stoOscillator    = new StochasticOscillator();
        StochasticSignalFactory stoSignalFactory = new StochasticSignalFactory();
        StochasticSpreadSheet   stoSpreadSheet   = new StochasticSpreadSheet();
        List<Stochastic>        stochasticList   = stoOscillator.generateStochastic(quotes, 14);

        // Generate 4 months of signals
        for ( int i=0; i<100; i++ ) {

            Stochastic       stochastic  = stochasticList.get(i);
            StochasticSignal stoSignal   = stoSignalFactory.getSignal(stochasticList, i);
            stoSpreadSheet.addRow(stochastic, stoSignal);
        }

        // write spreadsheet to file
        stoSpreadSheet.writeToFile(dir, fileName);

    }

   
    // Main loop
    @Override
    public void run(String... args) throws Exception {

/*
        // define the run time parameters
        AppParameters params = new AppParameters();

        // create parameter parser
        JCommander cmd = new JCommander(params);

        try {
            cmd.parse(args);

            if ( params.getKnowledge() != null ) {
                knowledgeIngest();
            } else {
                eodIngest();
            }

        } catch (ParameterException ex) {
            System.out.println(ex.getMessage());
            cmd.usage();
        }
*/

        generateSpreadSheet();
        //generateStochasticSpreadSheet();
        //generateAdxSpreadSheet();
        System.out.println("***************  Good bye  *******************");
        System.out.println();

    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        //SpringApplication app = new SpringApplication(IngestApplication.class);
        //app.setBannerMode(Banner.Mode.OFF);
        //app.run(args);
        System.exit(SpringApplication
                .exit(SpringApplication.run(AnalyticApplication.class, args)));
    }

}
