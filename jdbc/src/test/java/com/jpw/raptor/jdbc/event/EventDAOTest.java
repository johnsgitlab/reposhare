package com.jpw.raptor.jdbc.event;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.jdbc.event.EventDAO;
import com.jpw.raptor.model.Event;
import com.jpw.raptor.model.Quote;
import org.junit.*;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 5/13/18.
 */
public class EventDAOTest {

    EventDAO tbl;

    Event r1;
    Event r2;
    Event r3;
    Event r4;

    static class PostgresResource  {

        // postgres:9.6.8

        PostgreSQLContainer postgreSQLContainer;
        PGSimpleDataSource dataSource;

        public PostgresResource() {
            postgreSQLContainer = new PostgreSQLContainer("postgres:10.11")
                     .withDatabaseName("test-jdbc")
                    .withUsername("sa")
                    .withPassword("sa");

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        r1 = new Event(1l, sdf.parse("20171101"), "conflict", "sc1", "e1", "d1");
        r2 = new Event(2l, sdf.parse("20171230"), "earnings", "sc2", "e2", "d2");
        r3 = new Event(3l, sdf.parse("20180102"), "economy", "sc3", "e3", "d3");
        r4 = new Event(4l, sdf.parse("20180103"), "fed", "sc4", "e4", "d4");

    }

    @AfterClass
    public static void tearDown(){
        myPostgresResource.stop();
    }



    @Test
    public void test00() throws Exception {

        System.out.println("Start");

        // Create connection to docker database
        tbl = new EventDAO();
        tbl.setDataSource(myPostgresResource.getDataSource());
        tbl.postConstruct();

        // Create table for testing
        File resource;
        String cmds;

        resource = new ClassPathResource("schema.sql").getFile();
        cmds = new String(Files.readAllBytes(resource.toPath()));
        tbl.sqlScript(cmds);

        resource = new ClassPathResource("data.sql").getFile();
        cmds = new String(Files.readAllBytes(resource.toPath()));
        tbl.sqlScript(cmds);

        // Write 4 records
        tbl.add(r1);
        tbl.add(r2);
        tbl.add(r3);
        tbl.add(r4);

        Event rr;

        // Read and validate the 4 records
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
