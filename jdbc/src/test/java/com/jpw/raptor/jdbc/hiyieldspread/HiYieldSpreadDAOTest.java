package com.jpw.raptor.jdbc.hiyieldspread;

import com.jpw.raptor.model.HiYieldSpread;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by John on 11/25/2017.
 */

public class HiYieldSpreadDAOTest {

    HiYieldSpreadDAO        tbl;

    HiYieldSpread           testRec;
    HiYieldSpread           r1;
    HiYieldSpread           r2;
    HiYieldSpread           r3;


    static class PostgresResource  {

        // postgres:9.6.8

        PostgreSQLContainer postgreSQLContainer;
        PGSimpleDataSource  dataSource;

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
    public void setUpTest() {

        // Create test fields
        String [] rec1 = {"2018-12-02","2.37"};
        String [] rec2 = {"2018-12-03","2.36"};
        String [] rec3 = {"2018-12-04","2.39"};

        r1 = new HiYieldSpread(rec1);
        r2 = new HiYieldSpread(rec2);
        r3 = new HiYieldSpread(rec3);
    }

    @AfterClass
    public static void tearDown(){
        myPostgresResource.stop();
    }


    @Test
    public void test00() throws Exception {

        System.out.println("Start");

        // Create connection to docker database
        tbl = new HiYieldSpreadDAO();
        tbl.setDataSource(myPostgresResource.getDataSource());
        tbl.postConstruct();

        // Create table for testing
        File resource = new ClassPathResource("HiYieldSpread.sql").getFile();
        String cmds = new String(Files.readAllBytes(resource.toPath()));
        tbl.sqlScript(cmds);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2018-12-02");
        Date d2 = sdf.parse("2018-12-03");
        Date d3 = sdf.parse("2018-12-04");

        // Write 3 records
        tbl.upsert(r1);
        tbl.upsert(r2);
        tbl.upsert(r3);

        HiYieldSpread rr;

        // Read and validate the 3 records
        rr = tbl.get(d1);
        assertEquals(rr.getSpread(),      2.37,   0.001);

        rr = tbl.get(d2);
        assertEquals(rr.getSpread(),      2.36,   0.001);
       
        rr = tbl.get(d3);
        assertEquals(rr.getSpread(),      2.39,   0.001);

        // Validate delete one
        tbl.delete(d3);

        // Validate read all
        List<HiYieldSpread> l;
        l = tbl.getAll();
        assertEquals(2,l.size());

        // validate read last
        rr = tbl.getLast();
        assertEquals(rr.getSpread(),      2.36,   0.001);
        
        // Validate Update
        r1.setSpread(22.72);
        tbl.upsert(r1);

        // validate update
        rr = tbl.get(d1);
        assertEquals(rr.getSpread(),      22.72,   0.001);
        
        System.out.println("End");
    }
}
