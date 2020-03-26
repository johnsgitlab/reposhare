package com.jpw.raptor.hibernate.fund;

import com.jpw.raptor.hibernate.etf.EtfDao;
import com.jpw.raptor.hibernate.etf.EtfDaoTestData;
import com.jpw.raptor.model.Etf;
import com.jpw.raptor.model.Fund;
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

/*
 AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 SpringBootTest(classes = com.jpw.raptor.hibernate.etf.EtfDao.class)
 @SpringBootTest(classes = com.jpw.raptor.hibernate.TestConfig.class)
 EnableJpaRepositories
*/


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = { com.jpw.raptor.hibernate.fund.FundDaoTest.Initializer.class })
@EnableJpaRepositories
@EntityScan( basePackages = {"com.jpw.raptor.model"} )
public class FundDaoTest {

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
    private FundDao tbl;

    @Test
    public void testit() throws Exception {

        FundDaoTestData td = new FundDaoTestData();

        // test writing and reading a record
        Fund recWritten = td.setValues();
        tbl.save(recWritten);
        Fund recRead    = tbl.get(recWritten.getSymbol());
        td.validateValues(recRead);


    }


}
