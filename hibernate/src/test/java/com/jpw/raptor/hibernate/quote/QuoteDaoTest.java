package com.jpw.raptor.hibernate.quote;

import com.jpw.raptor.hibernate.index.IndexDao;
import com.jpw.raptor.hibernate.index.IndexDaoTest;
import com.jpw.raptor.model.Quote;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = { QuoteDaoTest.Initializer.class })
@EnableJpaRepositories
@EntityScan( basePackages = {"com.jpw.raptor.model"} )
public class QuoteDaoTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:10.11")
            .withDatabaseName("test")
            .withUsername("sa")
            .withPassword("sa");


    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    private QuoteDao tbl;

    @Test
    public void testit() throws Exception {

        String          symbol;
        String          s1 = "s1";
        String          s2 = "s2";
        String          s3 = "s3";
        String          s4;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d1 = sdf.parse("20171101");
        Date d2 = sdf.parse("20171230");
        Date d3 = sdf.parse("20180102");
        Date d4 = sdf.parse("20180222");

        Quote testRec;
        Quote r1 = new Quote(s1,d1,1.2,1.3,1.4,1.5,1);
        Quote r2 = new Quote(s2,d2,2.2,2.3,2.4,2.5,2);
        Quote r3 = new Quote(s3,d3,3.2,3.3,3.4,3.5,3);
        Quote r4 = new Quote(s3,d4,4.2,4.3,4.4,4.5,4);

        String          dateString;
        double          open;
        double          high;
        double          low;
        double          close;
        int             volume;

        // Write 4 records
        tbl.save(r1);
        tbl.save(r2);
        tbl.save(r3);
        tbl.save(r4);

        Quote rr;
        SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");

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
        l = tbl.getAll(s3);
        assertEquals(2,l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(0).getDate().compareTo(d4),0);
        assertTrue(l.get(1).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(1).getDate().compareTo(d3),0);

        // Validate Get for split
        l = tbl.getForSplit(s3, d4);
        assertEquals(1,l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase(r3.getSymbol()));
        assertEquals(l.get(0).getDate().compareTo(d3),0);

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
        tbl.save(r1);

        // validate update
        rr = tbl.get(s1, d1);
        assertTrue(rr.getSymbol().equalsIgnoreCase(r1.getSymbol()));
        assertEquals(rr.getDate().compareTo(d1),0);
        assertEquals(rr.getOpen(),r1.getOpen(),0.001);
        assertEquals(rr.getHigh(),r1.getHigh(),0.001);
        assertEquals(rr.getLow(),r1.getLow(),0.001);
        assertEquals(rr.getClose(),r1.getClose(),0.001);
        assertEquals(rr.getVolume(),r1.getVolume());

    }

}
