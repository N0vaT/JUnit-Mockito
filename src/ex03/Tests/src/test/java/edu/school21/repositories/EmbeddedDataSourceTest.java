package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;

class EmbeddedDataSourceTest {
    private DataSource dataBase;
    @BeforeEach
    void init(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataBase = builder.generateUniqueName(true)
                .addDefaultScripts()
                .build();
    }

    @Test
    void getConnectionTest(){
        try(Connection connection = dataBase.getConnection()) {
            assertNotNull(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
