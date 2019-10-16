package com.jpw.raptor.jdbc.quote;

import com.jpw.raptor.jdbc.index.IndexDAO;
import com.jpw.raptor.model.Quote;
import org.junit.*;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by John on 11/25/2017.
 */

public class QuoteDAOTest {


    QuoteDAO        tbl;

    Quote           testRec;
    Quote           r1;
    Quote           r2;
    Quote           r3;
    Quote           r4;

    String          symbol;
    String          s1;
    String          s2;
    String          s3;
    String          s4;

    Date            date;
    Date            d1;
    Date            d2;
    Date            d3;
    Date            d4;

    String          dateString;
    double          open;
    double          high;
    double          low;
    double          close;
    int             volume;



    static class PostgresResource  {

        // postgres:9.6.8

        PostgreSQLContainer postgreSQLContainer;
        PGSimpleDataSource dataSource;

        public PostgresResource() {
            postgreSQLContainer = new PostgreSQLContainer()
                    .withDatabaseName("raptor")
                    .withUsername("postgres")
                    .withPassword("Passw0rd");

            postgreSQLContainer.start();

            dataSource = new PGSimpleDataSource();
            dataSource.setDatabaseName(postgreSQLContainer.getDatabaseName());
            dataSource.setServerName(postgreSQLContainer.getContainerIpAddress());
            dataSource.setPortNumber(postgreSQLContainer.getMappedPort(5432));
            dataSource.setUser(postgreSQLContainer.getUsername());
            dataSource.setPassword(postgreSQLContainer.getPassword());

        }

        public void stop() { postgreSQLContainer.stop(); }

        public PGSimpleDataSource getDataSource () {return dataSource;}

    }


    private static PostgresResource myPostgresResource;

    @BeforeClass
    public static void setUp() throws java.text.ParseException {


        myPostgresResource = new PostgresResource();
    }


    @Before
    public void setUpTest() throws java.text.ParseException {

        // Create test fields
        s1 = "s1";
        s2 = "s2";
        s3 = "s3";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        d1 = sdf.parse("20171101");
        d2 = sdf.parse("20171230");
        d3 = sdf.parse("20180102");
        d4 = sdf.parse("20180222");

        // Create 4 test records
        r1 = new Quote(s1,d1,1.2,1.3,1.4,1.5,1);
        r2 = new Quote(s2,d2,2.2,2.3,2.4,2.5,2);
        r3 = new Quote(s3,d3,3.2,3.3,3.4,3.5,3);
        r4 = new Quote(s3,d4,4.2,4.3,4.4,4.5,4);

        symbol      = new String("1a2b3c");
        date        = new Date();
        open        = 1.1;
        high        = 2.2;
        low         = 3.3;
        close       = 4.4;
        volume      = 5;

        SimpleDateFormat    simpleDateFormat    = new SimpleDateFormat("yyyy-MM-dd");
        dateString   = simpleDateFormat.format(date);

        testRec = new Quote(symbol,date,open,high,low,close,volume);
    }

    @AfterClass
    public static void tearDown(){
        myPostgresResource.stop();
    }


    @Test
    public void test00() throws Exception {

        System.out.println("Start");

        // Create connection to docker database
        tbl = new QuoteDAO();
        tbl.setDataSource(myPostgresResource.getDataSource());
        tbl.postConstruct();

        // Create table for testing
        File resource = new ClassPathResource("Quote.sql").getFile();
        String cmds = new String(Files.readAllBytes(resource.toPath()));
        tbl.sqlScript(cmds);

        // Write 4 records
        tbl.add(r1);
        tbl.add(r2);
        tbl.add(r3);
        tbl.add(r4);

        Quote rr;

        // Read and validate the 4 records
        rr = tbl.get(s1, d1);
        assertTrue(rr.getSymbol().equalsIgnoreCase(r1.getSymbol()));
        assertEquals(rr.getDate().compareTo(d1),0);
        assertEquals(rr.getOpen(),r1.getOpen(),0.001);
        assertEquals(rr.getHigh(),r1.getHigh(),0.001);
        assertEquals(rr.getLow(),r1.getLow(),0.001);
        assertEquals(rr.getClose(),r1.getClose(),0.001);
        assertEquals(rr.getVolume(),r1.getVolume());

        rr = tbl.get(s2, d2);
        assertTrue(rr.getSymbol().equalsIgnoreCase(r2.getSymbol()));
        assertEquals(rr.getDate().compareTo(d2),0);
        assertEquals(rr.getOpen(),r2.getOpen(),0.001);
        assertEquals(rr.getHigh(),r2.getHigh(),0.001);
        assertEquals(rr.getLow(),r2.getLow(),0.001);
        assertEquals(rr.getClose(),r2.getClose(),0.001);
        assertEquals(rr.getVolume(),r2.getVolume());

        rr = tbl.get(s3, d3);
        assertTrue(rr.getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(rr.getDate().compareTo(d3),0);
        assertEquals(rr.getOpen(),r3.getOpen(),0.001);
        assertEquals(rr.getHigh(),r3.getHigh(),0.001);
        assertEquals(rr.getLow(),r3.getLow(),0.001);
        assertEquals(rr.getClose(),r3.getClose(),0.001);
        assertEquals(rr.getVolume(),r3.getVolume());

        rr = tbl.get(s3, d4);
        assertTrue(rr.getSymbol().equalsIgnoreCase(r4.getSymbol()));
        assertEquals(rr.getDate().compareTo(d4),0);
        assertEquals(rr.getOpen(),r4.getOpen(),0.001);
        assertEquals(rr.getHigh(),r4.getHigh(),0.001);
        assertEquals(rr.getLow(),r4.getLow(),0.001);
        assertEquals(rr.getClose(),r4.getClose(),0.001);
        assertEquals(rr.getVolume(),r4.getVolume());

        // Validate read all for a symbol
        List<Quote> l;

        // Validate read all for a symbol
        l = tbl.getAllAsc(s3);
        assertEquals(2,l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(0).getDate().compareTo(d3),0);
        assertTrue(l.get(1).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(1).getDate().compareTo(d4),0);

        // Validate read all DESC for a symbol
        l = tbl.getAllDesc(s3);
        assertEquals(2,l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(0).getDate().compareTo(d4),0);
        assertTrue(l.get(1).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(1).getDate().compareTo(d3),0);

        // Validate Get for split
        l = tbl.getForSplitDesc(s3, d4);
        assertEquals(1,l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(0).getDate().compareTo(d3),0);

        // Validate read by year for a symbol
        l = tbl.getByYearAsc(s3, 2018);
        assertEquals(2,l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(0).getDate().compareTo(d3),0);
        assertTrue(l.get(1).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(1).getDate().compareTo(d4),0);

        l = tbl.getByYearAsc(s3, 2017);
        assertEquals(0,l.size());

        // Validate delete all
        tbl.deleteAll(s3);
        l = tbl.getAllAsc(s3);
        assertEquals(0,l.size());

        // Validate delete one
        tbl.delete(s2,d2);
        l = tbl.getAllAsc(s2);
        assertEquals(0,l.size());

        // Validate Update
        r1.setOpen(90.0);
        r1.setHigh(91.1);
        r1.setLow(92.2);
        r1.setClose(93.3);
        r1.setVolume(94);
        tbl.update(r1);

        // validate update
        rr = tbl.get(s1, d1);
        assertTrue(rr.getSymbol().equalsIgnoreCase(r1.getSymbol()));
        assertEquals(rr.getDate().compareTo(d1),0);
        assertEquals(rr.getOpen(),r1.getOpen(),0.001);
        assertEquals(rr.getHigh(),r1.getHigh(),0.001);
        assertEquals(rr.getLow(),r1.getLow(),0.001);
        assertEquals(rr.getClose(),r1.getClose(),0.001);
        assertEquals(rr.getVolume(),r1.getVolume());

        System.out.println("End");
    }
}
