package com.jpw.raptor.jdbc.index;

import com.jpw.raptor.model.Index;
import com.jpw.raptor.jdbc.index.IndexDAO;
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
 * Created by john on 2/20/18.
 */
public class IndexDAOTest {

    IndexDAO     tbl;


    private String     symbol;
    private String     name;
    private Date       date;
    private Date       lastUpdate;

    private double     ytd;
    private double     oneDay;
    private double     oneWeek;
    private double     twoWeeks;
    private double     fourWeeks;
    private double     threeMonths;
    private double     oneYear;
    private double     threeYears;

    private String     overview;

    public void init() {

        // Provide default values
        symbol = "testit";
        name = "name";

        date = new Date(-1);
        lastUpdate = new Date();

        ytd = 7.0;
        oneDay = 8.0;
        oneWeek = 9.0;
        twoWeeks = 10.0;
        fourWeeks = 11.0;
        threeMonths = 12.0;
        oneYear = 13.0;
        threeYears = 14.0;

        overview = "overview";
    }


    public void setValues(Index rec) {

        rec.setSymbol(symbol);
        rec.setName(name);

        rec.setDate(new Date(-1));
        rec.setLastUpdate(new Date());

        rec.setYtd(ytd);
        rec.setOneDay(oneDay);
        rec.setOneWeek(oneWeek);
        rec.setTwoWeeks(twoWeeks);
        rec.setFourWeeks(fourWeeks);
        rec.setThreeMonths(threeMonths);
        rec.setOneYear(oneYear);
        rec.setThreeYears(threeYears);

        rec.setOverview(overview);
    }


    public void validateValues(Index rec) {

        assertTrue(rec.getSymbol().equalsIgnoreCase(symbol));
        assertTrue(rec.getName().equalsIgnoreCase(name));

//        assertTrue(rec.getDate().equals(date));
//        assertTrue(rec.getLastUpdate().equals(lastUpdate));

        assertEquals(rec.getYtd(),ytd,0.0001);
        assertEquals(rec.getOneDay(),oneDay,0.0001);
        assertEquals(rec.getOneWeek(),oneWeek,0.0001);
        assertEquals(rec.getTwoWeeks(),twoWeeks,0.0001);
        assertEquals(rec.getFourWeeks(),fourWeeks,0.0001);
        assertEquals(rec.getThreeMonths(),threeMonths,0.0001);
        assertEquals(rec.getOneYear(),oneYear,0.0001);
        assertEquals(rec.getThreeYears(),threeYears,0.0001);

        assertTrue(rec.getOverview().equalsIgnoreCase(overview));
    }


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
        init();
    }

    @AfterClass
    public static void tearDown(){
        myPostgresResource.stop();
    }



    @Test
    public void test00() throws Exception {

        System.out.println("Start");

        // Create connection to docker database
        tbl = new IndexDAO();
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

        // Write dummy record
        tbl.addEmpty("testit");

        // read the dummy record
        Index wr = tbl.get("testit");

        // update the record
        setValues(wr);

        // write Updated record
        tbl.update(wr);

        // read the updated record
        Index rr = tbl.get(wr.getSymbol());

        // validate the record
        validateValues(rr);

        // delete the record
        tbl.delete(rr.getSymbol());


        tbl.addEmpty("a");
        tbl.addEmpty("b");
        List<Index> l = tbl.getAll();
        assertEquals(2, l.size());
    }
}
