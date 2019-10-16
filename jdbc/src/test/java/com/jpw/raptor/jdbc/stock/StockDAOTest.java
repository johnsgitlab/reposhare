package com.jpw.raptor.jdbc.stock;

import com.jpw.raptor.jdbc.stock.StockDAO;

import com.jpw.raptor.model.Stock;
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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by john on 11/4/18.
 */
public class StockDAOTest {

    StockDAO      tbl;
    
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
    public void setUpTest() {
    }

    @AfterClass
    public static void tearDown(){
        myPostgresResource.stop();
    }



    @Test
    public void test00() throws Exception {

        System.out.println("Start");

        // Create connection to docker database
        tbl = new StockDAO();
        tbl.setDataSource(myPostgresResource.getDataSource());
        tbl.postConstruct();

        // Create table for testing
        File resource = new ClassPathResource("Stock.sql").getFile();
        String cmds = new String(Files.readAllBytes(resource.toPath()));
        tbl.sqlScript(cmds);

        StockTestData std = new StockTestData();

        // Write dummy record
        assertEquals(1, tbl.addEmpty("sym", "n", "s", "D", "r"));

        // read the dummy record
        Stock wr = tbl.get("sym");

        // update the record
        Stock rec = std.getData("sym");

        // write Updated record
        assertEquals(1,tbl.update(rec));

        // read the updated record
        Stock rr = tbl.get(wr.getSymbol());

        // validate the record
        std.validateData(rr);

        System.out.println("Fisnish");
    }

}
