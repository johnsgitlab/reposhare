package com.jpw.raptor.hibernate.hiyieldspread;

import com.jpw.raptor.hibernate.event.EventDao;
import com.jpw.raptor.hibernate.event.EventDaoTest;
import com.jpw.raptor.model.Event;
import com.jpw.raptor.model.HiYieldSpread;
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
@ContextConfiguration(initializers = { HiYieldSpreadDaoTest.Initializer.class })
@EnableJpaRepositories
@EntityScan( basePackages = {"com.jpw.raptor.model"} )
public class HiYieldSpreadDaoTest {

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
    private HiYieldSpreadDao tbl;

    @Test
    public void testit() throws Exception {

        HiYieldSpread testRec;
        HiYieldSpread r1;
        HiYieldSpread r2;
        HiYieldSpread r3;

        // Create test fields
        String [] rec1 = {"2018-12-02","2.37"};
        String [] rec2 = {"2018-12-03","2.36"};
        String [] rec3 = {"2018-12-04","2.39"};

        r1 = new HiYieldSpread(rec1);
        r2 = new HiYieldSpread(rec2);
        r3 = new HiYieldSpread(rec3);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2018-12-02");
        Date d2 = sdf.parse("2018-12-03");
        Date d3 = sdf.parse("2018-12-04");

        // Write 3 records
        tbl.save(r1);
        tbl.save(r2);
        tbl.save(r3);

        HiYieldSpread rr;

        // Read and validate the 3 records
        rr = tbl.get(d1);
        assertEquals(rr.getSpread(),      2.37,   0.001);

        rr = tbl.get(d2);
        assertEquals(rr.getSpread(),      2.36,   0.001);

        rr = tbl.get(d3);
        assertEquals(rr.getSpread(),      2.39,   0.001);

        // Validate delete one
        rr.setDate(d3);
        tbl.delete(rr);

        // Validate read all
        List<HiYieldSpread> l;
        l = tbl.getAll();
        assertEquals(2,l.size());

        // validate read last
        l = tbl.getLast();
        assertEquals(l.get(0).getSpread(),      2.36,   0.001);

        // Validate Update
        r1.setSpread(22.72);
        tbl.save(r1);

        // validate update
        rr = tbl.get(d1);
        assertEquals(rr.getSpread(),      22.72,   0.001);

        System.out.println("End");

    }

}
