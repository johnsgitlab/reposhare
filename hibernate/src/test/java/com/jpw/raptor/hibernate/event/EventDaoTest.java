package com.jpw.raptor.hibernate.event;

import com.jpw.raptor.hibernate.etf.EtfDao;
import com.jpw.raptor.hibernate.etf.EtfDaoTest;
import com.jpw.raptor.hibernate.etf.EtfDaoTestData;
import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Event;
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
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = { EventDaoTest.Initializer.class })
@EnableJpaRepositories
@EntityScan( basePackages = {"com.jpw.raptor.model"} )
public class EventDaoTest {

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
    private EventDao tbl;

    @Test
    public void testit() throws Exception {

        // Create test fields
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Event r1 = new Event(1l, sdf.parse("20171101"), "conflict", "sc1", "e1", "d1");
        Event r2 = new Event(2l, sdf.parse("20171230"), "earnings", "sc2", "e2", "d2");
        Event r3 = new Event(3l, sdf.parse("20180102"), "economy", "sc3", "e3", "d3");
        Event r4 = new Event(4l, sdf.parse("20180103"), "fed", "sc4", "e4", "d4");

        // Write 4 records
        tbl.save(r1);
        tbl.save(r2);
        tbl.save(r3);
        tbl.save(r4);

        // Read and validate the 4 records
        Event rr;

        rr = tbl.get(r1.getRowId());
        assertEquals(rr.getRowId(),r1.getRowId());
        assertEquals(rr.getDateTx().compareTo(r1.getDateTx()),0);
        assertTrue(rr.getCategory().equalsIgnoreCase(r1.getCategory()));
        assertTrue(rr.getSubCategory().equalsIgnoreCase(r1.getSubCategory()));
        assertTrue(rr.getEffect().equalsIgnoreCase(r1.getEffect()));
        assertTrue(rr.getDescription().equalsIgnoreCase(r1.getDescription()));

        rr = tbl.get(r2.getRowId());
        assertEquals(rr.getRowId(),r2.getRowId());
        assertEquals(rr.getDateTx().compareTo(r2.getDateTx()),0);
        assertTrue(rr.getCategory().equalsIgnoreCase(r2.getCategory()));
        assertTrue(rr.getSubCategory().equalsIgnoreCase(r2.getSubCategory()));
        assertTrue(rr.getEffect().equalsIgnoreCase(r2.getEffect()));
        assertTrue(rr.getDescription().equalsIgnoreCase(r2.getDescription()));

        rr = tbl.get(r3.getRowId());
        assertEquals(rr.getRowId(),r3.getRowId());
        assertEquals(rr.getDateTx().compareTo(r3.getDateTx()),0);
        assertTrue(rr.getCategory().equalsIgnoreCase(r3.getCategory()));
        assertTrue(rr.getSubCategory().equalsIgnoreCase(r3.getSubCategory()));
        assertTrue(rr.getEffect().equalsIgnoreCase(r3.getEffect()));
        assertTrue(rr.getDescription().equalsIgnoreCase(r3.getDescription()));

        rr = tbl.get(r4.getRowId());
        assertEquals(rr.getRowId(),r4.getRowId());
        assertEquals(rr.getDateTx().compareTo(r4.getDateTx()),0);
        assertTrue(rr.getCategory().equalsIgnoreCase(r4.getCategory()));
        assertTrue(rr.getSubCategory().equalsIgnoreCase(r4.getSubCategory()));
        assertTrue(rr.getEffect().equalsIgnoreCase(r4.getEffect()));
        assertTrue(rr.getDescription().equalsIgnoreCase(r4.getDescription()));


        List<Event> l;

        // Validate read all
        l = tbl.getAll();
        assertEquals(4,l.size());

        // Validate read by date

        l = tbl.getByDate(r2.getDateTx(), r3.getDateTx());
        assertEquals(2,l.size());


        assertEquals(l.get(0).getRowId(),r2.getRowId());
        assertEquals(l.get(0).getDateTx().compareTo(r2.getDateTx()),0);
        assertTrue(l.get(0).getCategory().equalsIgnoreCase(r2.getCategory()));
        assertTrue(l.get(0).getSubCategory().equalsIgnoreCase(r2.getSubCategory()));
        assertTrue(l.get(0).getEffect().equalsIgnoreCase(r2.getEffect()));
        assertTrue(l.get(0).getDescription().equalsIgnoreCase(r2.getDescription()));

        assertEquals(l.get(1).getRowId(),r3.getRowId());
        assertEquals(l.get(1).getDateTx().compareTo(r3.getDateTx()),0);
        assertTrue(l.get(1).getCategory().equalsIgnoreCase(r3.getCategory()));
        assertTrue(l.get(1).getSubCategory().equalsIgnoreCase(r3.getSubCategory()));
        assertTrue(l.get(1).getEffect().equalsIgnoreCase(r3.getEffect()));
        assertTrue(l.get(1).getDescription().equalsIgnoreCase(r3.getDescription()));

        System.out.println("End");
    }

}
