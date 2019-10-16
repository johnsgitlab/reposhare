package com.jpw.raptor.cmdline.merge;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.jpw.raptor")
public class MergeApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    public QuoteDAO quoteTbl;

    @Autowired
    public StockDAO stockTbl;

    // Main loop
    @Override
    public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("***************  Hello  *******************");

        // define the run time parameters
        AppParameters params = new AppParameters();

        // create parameter parser
        JCommander cmd = new JCommander(params);

        try {

            // parse the arguments
            cmd.parse(args);

            String oldSymbol = params.getOldSymbol().toUpperCase();
            String newSymbol = params.getNewSymbol().toUpperCase();

            // Obtain the old equity quotes
            List<Quote> quotes = quoteTbl.getAllAsc(oldSymbol);

            // For each quote delete the old one and create a new one
            for ( Quote quote : quotes ) {
                quoteTbl.delete( quote.getSymbol(), quote.getDate() );
                quote.setSymbol( newSymbol );
                quoteTbl.add(quote);
            }

            // get the stock record;
            Stock oldStock = stockTbl.get(oldSymbol);

            //delete the old stock table entry
            stockTbl.delete(oldSymbol);

            // create a new stock table entry
            oldStock.setSymbol(newSymbol);
            oldStock.setName(params.getName());
            stockTbl.addEmpty(oldStock.getSymbol(), oldStock.getName(), "", "", "");
            stockTbl.update(oldStock);

            System.out.println( "Old symbol: " + params.getOldSymbol() );
            System.out.println( "New symbol: " + params.getNewSymbol() );
            System.out.println( "Name      : " + params.getName() );

        } catch (ParameterException ex) {
            System.out.println(ex.getMessage());
            cmd.usage();
        }

        System.out.println("***************  Good bye  *******************");
        System.out.println();

    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(MergeApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

}
