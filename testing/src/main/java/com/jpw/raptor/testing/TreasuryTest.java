package com.jpw.raptor.testing;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.fund.FundDAO;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.jdbc.stock.StockDAO;
import com.jpw.raptor.jdbc.treasury.TreasuryDAO;

import com.jpw.raptor.model.Treasury;
import com.jpw.raptor.scrape.quandl.QuandlScrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;


/**
 * Created by john on 12/7/18.
 */
public class TreasuryTest {

    public void doit(QuoteDAO quoteTbl, EtfDAO etfTbl, FundDAO fundTbl, StockDAO stockTbl, TreasuryDAO tbl)
            throws java.text.ParseException {

/*
        // get treasury data from the web
        QuandlScrapper quandlScrapper = new QuandlScrapper();
        SortedMap<String, Treasury> map            = quandlScrapper.readPage("2014-01-02");

        // Update the database
        for (SortedMap.Entry<String, Treasury> entry : map.entrySet()) {
            tbl.upsert(entry.getValue());
            System.out.println("Treasury date : " + entry.getKey() );
        }
*/

/*
        Treasury testRec;
        Treasury           r1;
        Treasury           r2;
        Treasury           r3;

        // Create test fields
        String [] rec1 = {"2018-12-02","2.37","2.42","2.45","2.58","2.71","2.80","2.81","2.79","2.84","2.91","3.05","3.16"};
        String [] rec2 = {"2018-12-03","2.36","2.43","2.46","2.59","2.72","2.81","2.82","2.80","2.85","2.92","3.06","3.17"};
        String [] rec3 = {"2018-12-04","2.39","2.44","2.47","2.60","2.73","2.82","2.83","2.81","2.86","2.93","3.07","3.18"};

        r1 = new Treasury(rec1);
        r2 = new Treasury(rec2);
        r3 = new Treasury(rec3);



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2018-12-02");
        Date d2 = sdf.parse("2018-12-03");
        Date d3 = sdf.parse("2018-12-04");


        // Write 4 records
        tbl.upsert(r1);
        tbl.upsert(r2);
        tbl.upsert(r3);


        Treasury rr;

        // Read and validate the 3 records
        rr = tbl.get(d1);
        assert rr.getOneMonth() == 2.37;
        System.out.println(" ");
        System.out.println(rr.getDate() +       " expected       2018-12-02");
        System.out.println(rr.getOneMonth() +   " expected       2.37");
        System.out.println(rr.getTwoMonths()+   " expected       2.42");
        System.out.println(rr.getThreeMonths()+ " expected       2.45");
        System.out.println(rr.getSixMonths()+   " expected       2.58");
        System.out.println(rr.getOneYear()+     " expected       2.71");
        System.out.println(rr.getTwoYears()+    " expected       2.80");
        System.out.println(rr.getThreeYears()+  " expected       2.81");
        System.out.println(rr.getFiveYears()+   " expected       2.79");
        System.out.println(rr.getSevenYears()+  " expected       2.84");
        System.out.println(rr.getTenYears()+    " expected       2.91");
        System.out.println(rr.getTwentyYears()+ " expected       3.05");
        System.out.println(rr.getThirtyYears()+ " expected       3.16");
        System.out.println(" ");


        rr = tbl.get(d2);
        System.out.println(" ");
        System.out.println(rr.getDate() +       " expected       2018-12-03");
        System.out.println(rr.getOneMonth()+    " expected       2.36");
        System.out.println(rr.getTwoMonths()+   " expected       2.43");
        System.out.println(rr.getThreeMonths()+ " expected       2.46");
        System.out.println(rr.getSixMonths()+   " expected       2.59");
        System.out.println(rr.getOneYear()+     " expected       2.72");
        System.out.println(rr.getTwoYears()+    " expected       2.81");
        System.out.println(rr.getThreeYears()+  " expected       2.82");
        System.out.println(rr.getFiveYears()+   " expected       2.80");
        System.out.println(rr.getSevenYears()+  " expected       2.85");
        System.out.println(rr.getTenYears()+    " expected       2.92");
        System.out.println(rr.getTwentyYears()+ " expected       3.06");
        System.out.println(rr.getThirtyYears()+ " expected       3.17");
        System.out.println(" ");


        rr = tbl.get(d3);
        System.out.println(" ");
        System.out.println(rr.getDate() +       " expected       2018-12-04");
        System.out.println(rr.getOneMonth()+    " expected       2.39");
        System.out.println(rr.getTwoMonths()+   " expected       2.44");
        System.out.println(rr.getThreeMonths()+ " expected       2.47");
        System.out.println(rr.getSixMonths()+   " expected       2.60");
        System.out.println(rr.getOneYear()+     " expected       2.73");
        System.out.println(rr.getTwoYears()+    " expected       2.82");
        System.out.println(rr.getThreeYears()+  " expected       2.83");
        System.out.println(rr.getFiveYears()+   " expected       2.81");
        System.out.println(rr.getSevenYears()+  " expected       2.86");
        System.out.println(rr.getTenYears()+    " expected       2.93");
        System.out.println(rr.getTwentyYears()+ " expected       3.07");
        System.out.println(rr.getThirtyYears()+ " expected       3.18");
        System.out.println(" ");


        System.out.println(" ");


        // Validate delete one
        tbl.delete(d3);

        // validate read last
        rr = tbl.getLast();
        System.out.println(" ");
        System.out.println(rr.getDate() +       " expected       2018-12-03");
        System.out.println(rr.getOneMonth()+    " expected       2.36");
        System.out.println(rr.getTwoMonths()+   " expected       2.43");
        System.out.println(rr.getThreeMonths()+ " expected       2.46");
        System.out.println(rr.getSixMonths()+   " expected       2.59");
        System.out.println(rr.getOneYear()+     " expected       2.72");
        System.out.println(rr.getTwoYears()+    " expected       2.81");
        System.out.println(rr.getThreeYears()+  " expected       2.82");
        System.out.println(rr.getFiveYears()+   " expected       2.80");
        System.out.println(rr.getSevenYears()+  " expected       2.85");
        System.out.println(rr.getTenYears()+    " expected       2.92");
        System.out.println(rr.getTwentyYears()+ " expected       3.06");
        System.out.println(rr.getThirtyYears()+ " expected       3.17");
        System.out.println(" ");

        // Validate Update
        r1.setOneYear(22.72);
        r1.setThirtyYears(33.17);
        tbl.upsert(r1);

        // validate update
        rr = tbl.get(d1);
        System.out.println(" ");
        System.out.println(rr.getDate() +       " expected       2018-12-03");
        System.out.println(rr.getOneMonth()+    " expected       2.36");
        System.out.println(rr.getTwoMonths()+   " expected       2.43");
        System.out.println(rr.getThreeMonths()+ " expected       2.46");
        System.out.println(rr.getSixMonths()+   " expected       2.59");
        System.out.println(rr.getOneYear()+     " expected       22.72");
        System.out.println(rr.getTwoYears()+    " expected       2.81");
        System.out.println(rr.getThreeYears()+  " expected       2.82");
        System.out.println(rr.getFiveYears()+   " expected       2.80");
        System.out.println(rr.getSevenYears()+  " expected       2.85");
        System.out.println(rr.getTenYears()+    " expected       2.92");
        System.out.println(rr.getTwentyYears()+ " expected       3.06");
        System.out.println(rr.getThirtyYears()+ " expected       33.17");
        System.out.println(" ");

         /*
        // Validate read all
        List<Treasury> l;
        l = tbl.getAll();
        System.out.println(" ");
        System.out.println(l.size()+ " expected           2");
        System.out.println(" ");

        // validate read last
        rr = tbl.getLast();
        System.out.println(" ");
        System.out.println(rr.getDate() +       " expected       2018-12-04");
        System.out.println(rr.getOneMonth()+    " expected       2.36");
        System.out.println(rr.getTwoMonths()+   " expected       2.43");
        System.out.println(rr.getThreeMonths()+ " expected       2.46");
        System.out.println(rr.getSixMonths()+   " expected       2.59");
        System.out.println(rr.getOneYear()+     " expected       2.72");
        System.out.println(rr.getTwoYears()+    " expected       2.81");
        System.out.println(rr.getThreeYears()+  " expected       2.82");
        System.out.println(rr.getFiveYears()+   " expected       2.80");
        System.out.println(rr.getSevenYears()+  " expected       2.85");
        System.out.println(rr.getTenYears()+    " expected       2.92");
        System.out.println(rr.getTwentyYears()+ " expected       3.06");
        System.out.println(rr.getThirtyYears()+ " expected       3.17");
        System.out.println(" ");


*/
        System.out.println("End");

    }
}
