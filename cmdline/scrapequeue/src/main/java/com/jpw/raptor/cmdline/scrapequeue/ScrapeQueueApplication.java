package com.jpw.raptor.cmdline.scrapequeue;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.lib.jms.ActiveMq;
import com.jpw.raptor.model.EquityToScrape;
import com.jpw.raptor.model.Index;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.model.Quote;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.JMSException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class ScrapeQueueApplication implements CommandLineRunner {

    @Autowired
    public EtfDAO etfTbl;

    @Autowired
    public FundDAO fundTbl;

    @Autowired
    public StockDAO stockTbl;

    // Main loop
    @Override
    public void run(String... args) throws Exception {

        log.info("***************  Hello  *******************");

        ActiveMq jmsQ  = new ActiveMq("scrape");

        // define the run time parameters
        AppParameters params = new AppParameters();

        // create parameter parser
        JCommander cmd = new JCommander(params);

        Gson gson = new Gson();

        try {
            cmd.parse(args);
            log.info("Type   {}", params.getType());
            log.info("Select {}", params.getSelect());
            log.info("Symbol {}", params.getSymbol());

            if ( params.getSelect() == null &&  params.getSymbol()  == null) {
                throw new ParameterException("Must specify select or symbol");
            }

            if ( params.getSelect() != null &&  params.getSymbol() != null ) {
                throw new ParameterException("Cannot specify both select and symbol");
            }

            GenerateScrapeSymbols gen = new GenerateScrapeSymbols();

            if ( params.getType().equalsIgnoreCase("etf") ) {
                List<String> array;
                if ( params.getSymbol() != null ) {
                   array = gen.getOneEtfSymbol(etfTbl, params.getSymbol());
                } else {
                   array = gen.getEtfSymbols(etfTbl, params.getSelect());
                }
                for ( String symbol : array ) {
                    EquityToScrape ets = new EquityToScrape("etf", symbol);
                    log.info("{}", gson.toJson(ets));
                    jmsQ.put(gson.toJson(ets));
                }
            } else if ( params.getType().equalsIgnoreCase("fund") ) {
                List<String> array;
                if ( params.getSymbol() != null ) {
                    array = gen.getOneFundSymbol(fundTbl, params.getSymbol());
                } else {
                    array = gen.getFundSymbols(fundTbl, params.getSelect());
                }
                for ( String symbol : array ) {
                    EquityToScrape ets = new EquityToScrape("fund", symbol);
                    log.info("{}", gson.toJson(ets));
                    jmsQ.put(gson.toJson(ets));
                }
            } else if ( params.getType().equalsIgnoreCase("stock") ) {
                List<String> array;
                if ( params.getSymbol() != null ) {
                    array = gen.getOneStockSymbol(stockTbl, params.getSymbol());
                } else {
                    array = gen.getStockSymbols(stockTbl, params.getSelect());
                }
                for ( String symbol : array ) {
                    EquityToScrape ets = new EquityToScrape("stock", symbol);
                    log.info("{}", gson.toJson(ets));
                    jmsQ.put(gson.toJson(ets));
                }
            }
        } catch (ParameterException | JMSException ex) {
            log.error("{}", ex.getMessage());
            cmd.usage();
        }

        try {
            jmsQ.close();
        } catch (JMSException ex) {
            log.error("{}", ex.getMessage());
        }

        log.info("***************  Good bye  *******************");

    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(ScrapeQueueApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

}
