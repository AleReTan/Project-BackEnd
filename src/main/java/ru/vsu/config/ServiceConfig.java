package ru.vsu.config;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "ru.vsu")
@PropertySource("classpath:db.properties")
public class ServiceConfig {

    @Resource
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        @SuppressWarnings("deprecation")
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setServerName(env.getRequiredProperty("db.url"));
        source.setDatabaseName(env.getRequiredProperty("db.name"));
        source.setUser(env.getRequiredProperty("db.username"));
        source.setPassword(env.getRequiredProperty("db.password"));
        source.setMaxConnections(10);
        return source;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

}
