package com.jpw.raptor.cmdline.scrapeweb;


import com.google.gson.Gson;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.lib.jms.ActiveMq;
import com.jpw.raptor.model.EquityToScrape;
import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Fund;
import com.jpw.raptor.model.Stock;
import com.jpw.raptor.scrape.marketwatch.MarketWatchPageContents;
import com.jpw.raptor.scrape.marketwatch.MarketWatchPageScrapper;
import com.jpw.raptor.scrape.yahoo.YahooPageContents;
import com.jpw.raptor.scrape.yahoo.YahooPageScrapper;
import com.jpw.raptor.scrape.yahoostock.YahooStockPageContents;
import com.jpw.raptor.scrape.yahoostock.YahooStockPageScrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by john on 4/3/18.
 */
@Service
public class ScrapeWorker {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    protected BlockingQueue<EquityToScrape> queue;

    @Autowired
    public EtfDAO etfTbl;

    @Autowired
    public FundDAO fundTbl;

    @Autowired
    public StockDAO stockTbl;

    @Async
    public Future<String> process(BlockingQueue<EquityToScrape> blockingQ) throws InterruptedException {

        this.queue = blockingQ;

        String          threadName  = Thread.currentThread().getName();
        ActiveMq        equityQ     = new ActiveMq("scrape");
        EquityToScrape  equity      = null;

        String pathToChromeDriver = "/home/scripts/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);

        //log.info("### Start Processing with Thread id: " + Thread.currentThread().getId());
        log.info("### Start Processing with Thread id: " + threadName);

        boolean dataFound = true;

        while ( dataFound ) {

            equity = queue.poll(1l, TimeUnit.SECONDS);

            if ( equity != null ) {

                if ( equity.getType().equalsIgnoreCase("etf") || equity.getType().equalsIgnoreCase("fund") ) {
                    if ( !processEtfFund(equity, threadName, driver) ) {
                        requeue(equity, equityQ, threadName);
                    }
                } else if ( equity.getType().equalsIgnoreCase("stock") ) {
                    if ( !processStock(equity, threadName, driver) ) {
                        requeue(equity, equityQ, threadName);
                    }
                } else {
                    log.info( "### Unknown equity type " + equity.getType() + " " + threadName );
                    System.out.println("### Unknown equity type " + equity.getType() + " " + threadName);
                }


                TimeUnit.MILLISECONDS.sleep(1);
            } else {
                dataFound = false;
                log.info("### equity queue empty " + threadName );
                System.out.println("### equity queue empty " + threadName );
            }
        }

        try {
            equityQ.close();
        } catch ( JMSException ex  ) {
            System.out.println("### " + threadName + " - " + ex.getMessage());
        }

        //close chrome
        driver.close();

        String processInfo = String.format("### Processing is Done with Thread id= %d", Thread.currentThread().getId());
        return new AsyncResult<>(processInfo);
    }


    public void requeue(EquityToScrape equity, ActiveMq equityQ, String threadName ) {

        log.info("### re-queue " + equity.getSymbol() + " " + threadName );
        System.out.println("### re-queue " + equity.getSymbol() + " " + threadName );

        try {
            Gson gson = new Gson();
            equityQ.put(gson.toJson(equity));
        } catch ( JMSException ex  ) {
            if ( ex.getMessage() != null )
                System.out.println(ex.getMessage());
        }
    }


    public boolean processStock(EquityToScrape  equity, String threadName, WebDriver driver) {

        log.info("### process " + equity.getSymbol() + " " + threadName );

        boolean result = true;

        // Scrape data
        YahooStockPageScrapper yahooScrapper  = new YahooStockPageScrapper();
        YahooStockPageContents ypc            = yahooScrapper.readPage(equity.getSymbol());

        MarketWatchPageScrapper mwScrapper      = new MarketWatchPageScrapper();
        String                  exchange        = mwScrapper.readStockExchange(equity.getSymbol(), driver);

        // Validate the scrape
        if ( ypc == null || !ypc.isFound() || exchange == null ) {
            // Error occured requeue the equity
            result = false;
        } else {
            // Update the data base
            Stock stock = stockTbl.get(equity.getSymbol());
            ypc.updateStockFromYahoo(stock);
            stock.setExchange(exchange);
            stockTbl.update(stock);
        }

        return result;
    }


    public boolean processEtfFund(EquityToScrape  equity, String threadName, WebDriver driver) {

        log.info("### process " + equity.getSymbol() + " " + threadName );

        boolean result = true;

        // Scrape data
        YahooPageScrapper       yahooScrapper   = new YahooPageScrapper();
        MarketWatchPageScrapper mwScrapper      = new MarketWatchPageScrapper();

        YahooPageContents       ypc = yahooScrapper.readPage(equity.getSymbol());
        MarketWatchPageContents mpc = mwScrapper.readPage(equity.getSymbol(), driver);

        // Validate the scrape
        //if ( ypc == null || !ypc.isFound() || mpc == null || !mpc.isFound() ) {
        if ( ypc == null || !ypc.isFound() ) {
            // Error occured requeue the equity
            result = false;
        } else {

            // Update the data base
            if (equity.getType().equalsIgnoreCase("etf")) {
                // Read the database record
                Etf etf = etfTbl.get(equity.getSymbol());

                // Update the record with page contents
                ypc.updateEtfFromYahoo(etf);
                mpc.updateEtfFromMarketWatch(etf);

                // write the record to the database
                etfTbl.update(etf);

            } else if (equity.getType().equalsIgnoreCase("fund")) {
                // Read the database record
                Fund fund = fundTbl.get(equity.getSymbol());

                // Update the record with page contents
                ypc.updateFundFromYahoo(fund);
                mpc.updateFundFromMarketWatch(fund);

                // write the record to the database
                fundTbl.update(fund);

            }
        }
        return result;
    }

}
