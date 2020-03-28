package com.jpw.raptor.hibernate.index;

import com.jpw.raptor.hibernate.etf.EtfDaoTestData;
import com.jpw.raptor.hibernate.event.EventDao;
import com.jpw.raptor.hibernate.event.EventDaoTest;
import com.jpw.raptor.model.Event;
import com.jpw.raptor.model.Index;
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
@ContextConfiguration(initializers = { IndexDaoTest.Initializer.class })
@EnableJpaRepositories
@EntityScan( basePackages = {"com.jpw.raptor.model"} )
public class IndexDaoTest {

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
    private IndexDao tbl;

    @Test
    public void testit() throws Exception {

        IndexDaoTestData td = new IndexDaoTestData();

        // Write dummy record
        tbl.addEmpty("testit");

        // read the dummy record
        Index wr = tbl.get("testit");

        // update the record
        wr = td.setValues();

        // write Updated record
        tbl.save(wr);

        // read the updated record
        Index rr = tbl.get(wr.getSymbol());

        // validate the record
        td.validateValues(rr);

        // delete the record
        tbl.delete(rr);


        tbl.addEmpty("a");
        tbl.addEmpty("b");
        List<Index> l = tbl.getAll();
        assertEquals(2, l.size());
    }

}
