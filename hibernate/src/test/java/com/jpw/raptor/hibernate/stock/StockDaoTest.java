package com.jpw.raptor.hibernate.stock;

import com.jpw.raptor.hibernate.quote.QuoteDao;
import com.jpw.raptor.hibernate.quote.QuoteDaoTest;
import com.jpw.raptor.model.Stock;
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
@ContextConfiguration(initializers = { StockDaoTest.Initializer.class })
@EnableJpaRepositories
@EntityScan( basePackages = {"com.jpw.raptor.model"} )
public class StockDaoTest {

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
    private StockDao tbl;

    @Test
    public void testit() throws Exception {
        StockDaoTestData std = new StockDaoTestData();

        // Write dummy record
        tbl.addEmpty("sym", "n", "s", "D", "r");

        // read the dummy record
        Stock wr = tbl.get("sym");

        // update the record
        Stock rec = std.getData("sym");

        // write Updated record
        tbl.save(rec);

        // read the updated record
        Stock rr = tbl.get(wr.getSymbol());

        // validate the record
        std.validateData(rr);

        System.out.println("Finish");
    }

}
