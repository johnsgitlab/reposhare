package com.jpw.raptor.jdbc.etf;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class EtfContainerTest02 {

    @Rule
    public PostgreSQLContainer postgresContainer = new PostgreSQLContainer()
            .withDatabaseName("raptor")
            .withUsername("postgres")
            .withPassword("Passw0rd");

    @Test
    @Sql({"/Etf.sql"})
    public void testcontainersTest() throws Exception {

        String jdbcUrl  = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();

        System.out.println("**************************");
        System.out.println("url  " + jdbcUrl );
        System.out.println("user " + username );
        System.out.println("pw   " + password );
        System.out.println("**************************");

        Connection conn = DriverManager
                .getConnection(jdbcUrl, username, password);

        ResultSet resultSet =
                conn.createStatement().executeQuery("SELECT 1");
        resultSet.next();
        int result = resultSet.getInt(1);

        assertEquals(1, result);
    }
}
