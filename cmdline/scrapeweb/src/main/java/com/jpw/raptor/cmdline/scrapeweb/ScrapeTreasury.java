package com.jpw.raptor.cmdline.scrapeweb;

import com.jpw.raptor.jdbc.treasury.TreasuryDAO;
import com.jpw.raptor.model.Treasury;
import com.jpw.raptor.scrape.quandl.QuandlScrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.SortedMap;

/**
 * Created by john on 12/8/18.
 */
public class ScrapeTreasury {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public void process(TreasuryDAO treasuryTbl) {

        System.out.println();
        System.out.println("***************  Scrape Treasuries  *******************");

        // Get the last day processed
        Treasury lastProcessed = treasuryTbl.getLast();

        // format the date
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        String           date = sdf.format(lastProcessed.getDate());
        System.out.println("Last treasury date " + date);

        // get treasury data from the web
        QuandlScrapper              quandlScrapper = new QuandlScrapper();
        SortedMap<String, Treasury> map            = quandlScrapper.readPage(date);

        // Update the database
        for (SortedMap.Entry<String, Treasury> entry : map.entrySet()) {
            treasuryTbl.upsert(entry.getValue());
            System.out.println("Treasury date : " + entry.getKey() );
        }

        System.out.println("***************  Finish Treasuries  *******************");
        System.out.println();
    }
}
