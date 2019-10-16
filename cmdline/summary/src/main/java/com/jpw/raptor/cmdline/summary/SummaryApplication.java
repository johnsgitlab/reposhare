package com.jpw.raptor.cmdline.summary;

import com.beust.jcommander.JCommander;

import com.jpw.raptor.algorithm.EquityPerformance;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.index.IndexDAO;

import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Fund;
import com.jpw.raptor.model.Index;
import com.jpw.raptor.model.Quote;

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
public class SummaryApplication implements CommandLineRunner {

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

        System.out.println("***************  Good bye  *******************");
        System.out.println();

    }

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(SummaryApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

}
