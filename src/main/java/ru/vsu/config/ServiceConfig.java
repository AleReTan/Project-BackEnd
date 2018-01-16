package ru.vsu.config;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "ru.vsu")
public class ServiceConfig {

    @Bean
    public DataSource getDataSource() {
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("postgres");
        source.setUser("postgres");
        source.setPassword("1234");
        source.setMaxConnections(10);
        return source;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }
}
//