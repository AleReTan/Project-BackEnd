package ru.vsu.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.vsu.entity.ScheduleTaskEntity;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "ru.vsu")
public class ServiceConfig {

   @Bean
   public DataSource getDataSource() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("postgres");
        source.setUser("postgres");
        source.setPassword("1488");
        return source;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public ScheduleTaskEntity createBean() {
        return new ScheduleTaskEntity();
    }
}
