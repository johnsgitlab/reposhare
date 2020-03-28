package com.jpw.raptor.hibernate.treasury;

import com.jpw.raptor.hibernate.stock.StockDao;
import com.jpw.raptor.hibernate.stock.StockDaoTest;
import com.jpw.raptor.model.Treasury;
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
@ContextConfiguration(initializers = { TreasuryDaoTest.Initializer.class })
@EnableJpaRepositories
@EntityScan( basePackages = {"com.jpw.raptor.model"} )
public class TreasuryDaoTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:10.11")
            .withDatabaseName("test-hibernate")
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
    private TreasuryDao tbl;

    @Test
    public void testit() throws Exception {
        Treasury           testRec;
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
        tbl.save(r1);
        tbl.save(r2);
        tbl.save(r3);


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
        tbl.delete(rr);

        // Validate read all
        List<Treasury> l;
        l = tbl.getAll();
        assertEquals(2,l.size());


        // validate read last
        l = tbl.getLast();
        assertEquals(l.get(0).getOneMonth(),      2.36,   0.001);
        assertEquals(l.get(0).getTwoMonths(),     2.43,   0.001);
        assertEquals(l.get(0).getThreeMonths(),   2.46,   0.001);
        assertEquals(l.get(0).getSixMonths(),     2.59,   0.001);
        assertEquals(l.get(0).getOneYear(),       2.72,   0.001);
        assertEquals(l.get(0).getTwoYears(),      2.81,   0.001);
        assertEquals(l.get(0).getThreeYears(),    2.82,   0.001);
        assertEquals(l.get(0).getFiveYears(),     2.80,   0.001);
        assertEquals(l.get(0).getSevenYears(),    2.85,   0.001);
        assertEquals(l.get(0).getTenYears(),      2.92,   0.001);
        assertEquals(l.get(0).getTwentyYears(),   3.06,   0.001);
        assertEquals(l.get(0).getThirtyYears(),   3.17,   0.001);

        // Validate Update
        r1.setOneYear(22.72);
        r1.setThirtyYears(33.17);
        tbl.save(r1);

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

    }
}
