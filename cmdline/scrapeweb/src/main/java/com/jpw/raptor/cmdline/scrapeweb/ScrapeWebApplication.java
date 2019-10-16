package com.jpw.raptor.cmdline.scrapeweb;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.treasury.TreasuryDAO;
import com.jpw.raptor.lib.jms.ActiveMq;
import com.jpw.raptor.model.EquityToScrape;
import com.jpw.raptor.model.Index;
import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;
import javax.jms.JMSException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class ScrapeWebApplication implements CommandLineRunner {

    @Autowired
    private ConfigurableApplicationContext context;

    @Resource
    ScrapeWorker worker;

    @Autowired
    public EtfDAO etfTbl;

    @Autowired
    public FundDAO fundTbl;

    @Autowired
    public TreasuryDAO treasuryTbl;

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    // Main loop
    @Override
    public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("***************  Hello  *******************");

        Gson                            gson     = new Gson();
        ActiveMq                        equityQ  = new ActiveMq("scrape");
        BlockingQueue<EquityToScrape>   queue    = new LinkedBlockingQueue<EquityToScrape>();

        // Sleep to let things shake out
        TimeUnit.SECONDS.sleep(1);

        // Pull from ActiveMQ
        try {
            String str = equityQ.get();
            while ( str != null ) {
                queue.put(gson.fromJson(str, EquityToScrape.class));
                str = equityQ.get();
            }
            equityQ.close();
        } catch ( JMSException ex ) {
            System.out.println("Error JMS error " + " - " + ex.getMessage());
        }

        int equitiesToProcess = queue.size();
        System.out.println("Equities queued for processing " + equitiesToProcess);

        long startTime = System.currentTimeMillis();



        // Start the threads
        Future<String> process1 = worker.process(queue);
        Future<String> process2 = worker.process(queue);
        Future<String> process3 = worker.process(queue);

        // Terminate when all of the equities have been processed

        //try {
        //    while ( queue.size() > 0 ) {
        //        TimeUnit.SECONDS.sleep(2);
        //    }
        //} catch (InterruptedException ex) {
        //    System.out.println("ExecutorService interrupted " );
        //}


        // Wait until all threads are finish
        // Wait until They are all Done
        // If all are not Done. Pause 2s for next re-check
        while(!(process1.isDone() && process2.isDone() && process3.isDone())){
            Thread.sleep(2000);
        }

        log.info("All Processes are DONE!");
        // Log results
        log.info("Process 1: " + process1.get());
        log.info("Process 2: " + process2.get());
        log.info("Process 3: " + process3.get());

        long endTime = System.currentTimeMillis();
        System.out.println("Processed " + equitiesToProcess + " equities in " + (endTime - startTime) + " milliseconds" );

        try {
            equityQ.close();
        } catch (JMSException ex) {
            System.out.println(ex.getMessage());
        }
       
        System.out.println("***************  Good bye  *******************");
        System.out.println();

        System.exit(SpringApplication.exit(context));
    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(ScrapeWebApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

}
