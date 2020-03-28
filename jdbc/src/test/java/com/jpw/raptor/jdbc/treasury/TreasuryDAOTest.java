package com.jpw.raptor.jdbc.treasury;

import com.jpw.raptor.model.Treasury;
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

public class TreasuryDAOTest {

    TreasuryDAO        tbl;

    Treasury           testRec;
    Treasury           r1;
    Treasury           r2;
    Treasury           r3;


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
    public void setUpTest() {

        // Create test fields
        String [] rec1 = {"2018-12-02","2.37","2.42","2.45","2.58","2.71","2.80","2.81","2.79","2.84","2.91","3.05","3.16"};
        String [] rec2 = {"2018-12-03","2.36","2.43","2.46","2.59","2.72","2.81","2.82","2.80","2.85","2.92","3.06","3.17"};
        String [] rec3 = {"2018-12-04","2.39","2.44","2.47","2.60","2.73","2.82","2.83","2.81","2.86","2.93","3.07","3.18"};

        r1 = new Treasury(rec1);
        r2 = new Treasury(rec2);
        r3 = new Treasury(rec3);
    }

    @AfterClass
    public static void tearDown(){
        myPostgresResource.stop();
    }


    @Test
    public void test00() throws Exception {

        System.out.println("Start");

        // Create connection to docker database
        tbl = new TreasuryDAO();
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
        assertEquals(rr.getOneMonth(),      2.37,   0.001);
        assertEquals(rr.getTwoMonths(),     2.42,   0.001);
        assertEquals(rr.getThreeMonths(),   2.45,   0.001);
        assertEquals(rr.getSixMonths(),     2.58,   0.001);
        assertEquals(rr.getOneYear(),       2.71,   0.001);
        assertEquals(rr.getTwoYears(),      2.8,    0.001);
        assertEquals(rr.getThreeYears(),    2.81,   0.001);
        assertEquals(rr.getFiveYears(),     2.79,   0.001);
        assertEquals(rr.getSevenYears(),    2.84,   0.001);
        assertEquals(rr.getTenYears(),      2.91,   0.001);
        assertEquals(rr.getTwentyYears(),   3.05,   0.001);
        assertEquals(rr.getThirtyYears(),   3.16,   0.001);

        rr = tbl.get(d2);
        assertEquals(rr.getOneMonth(),      2.36,   0.001);
        assertEquals(rr.getTwoMonths(),     2.43,   0.001);
        assertEquals(rr.getThreeMonths(),   2.46,   0.001);
        assertEquals(rr.getSixMonths(),     2.59,   0.001);
        assertEquals(rr.getOneYear(),       2.72,   0.001);
        assertEquals(rr.getTwoYears(),      2.81,   0.001);
        assertEquals(rr.getThreeYears(),    2.82,   0.001);
        assertEquals(rr.getFiveYears(),     2.80,   0.001);
        assertEquals(rr.getSevenYears(),    2.85,   0.001);
        assertEquals(rr.getTenYears(),      2.92,   0.001);
        assertEquals(rr.getTwentyYears(),   3.06,   0.001);
        assertEquals(rr.getThirtyYears(),   3.17,   0.001);

        rr = tbl.get(d3);
        assertEquals(rr.getOneMonth(),      2.39,   0.001);
        assertEquals(rr.getTwoMonths(),     2.44,   0.001);
        assertEquals(rr.getThreeMonths(),   2.47,   0.001);
        assertEquals(rr.getSixMonths(),     2.60,   0.001);
        assertEquals(rr.getOneYear(),       2.73,   0.001);
        assertEquals(rr.getTwoYears(),      2.82,   0.001);
        assertEquals(rr.getThreeYears(),    2.83,   0.001);
        assertEquals(rr.getFiveYears(),     2.81,   0.001);
        assertEquals(rr.getSevenYears(),    2.86,   0.001);
        assertEquals(rr.getTenYears(),      2.93,   0.001);
        assertEquals(rr.getTwentyYears(),   3.07,   0.001);
        assertEquals(rr.getThirtyYears(),   3.18,   0.001);


        // Validate delete one
        tbl.delete(d3);

        // Validate read all
        List<Treasury> l;
        l = tbl.getAll();
        assertEquals(2,l.size());


        // validate read last
        rr = tbl.getLast();
        assertEquals(rr.getOneMonth(),      2.36,   0.001);
        assertEquals(rr.getTwoMonths(),     2.43,   0.001);
        assertEquals(rr.getThreeMonths(),   2.46,   0.001);
        assertEquals(rr.getSixMonths(),     2.59,   0.001);
        assertEquals(rr.getOneYear(),       2.72,   0.001);
        assertEquals(rr.getTwoYears(),      2.81,   0.001);
        assertEquals(rr.getThreeYears(),    2.82,   0.001);
        assertEquals(rr.getFiveYears(),     2.80,   0.001);
        assertEquals(rr.getSevenYears(),    2.85,   0.001);
        assertEquals(rr.getTenYears(),      2.92,   0.001);
        assertEquals(rr.getTwentyYears(),   3.06,   0.001);
        assertEquals(rr.getThirtyYears(),   3.17,   0.001);

        // Validate Update
        r1.setOneYear(22.72);
        r1.setThirtyYears(33.17);
        tbl.upsert(r1);

        // validate update
        rr = tbl.get(d1);
        assertEquals(rr.getOneMonth(),      2.37,   0.001);
        assertEquals(rr.getTwoMonths(),     2.42,   0.001);
        assertEquals(rr.getThreeMonths(),   2.45,   0.001);
        assertEquals(rr.getSixMonths(),     2.58,   0.001);
        assertEquals(rr.getOneYear(),      22.72,   0.001);
        assertEquals(rr.getTwoYears(),      2.8,    0.001);
        assertEquals(rr.getThreeYears(),    2.81,   0.001);
        assertEquals(rr.getFiveYears(),     2.79,   0.001);
        assertEquals(rr.getSevenYears(),    2.84,   0.001);
        assertEquals(rr.getTenYears(),      2.91,   0.001);
        assertEquals(rr.getTwentyYears(),   3.05,   0.001);
        assertEquals(rr.getThirtyYears(),  33.17,   0.001);

        System.out.println("End");
    }
}
