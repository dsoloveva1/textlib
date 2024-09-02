package com.example.textlib.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource("classpath:application.properties")
public class JdbcTemplateConfig {
    private static Logger LOGGER =
            LoggerFactory.getLogger(JdbcTemplateConfig.class);

    @Value("${spring.datasource.postgres.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.postgres.url}")
    private String url;

    @Value("${spring.datasource.postgres.username}")
    private String username;

    @Value("${spring.datasource.postgres.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        try {
            var dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver =
                    (Class<? extends Driver>) Class.forName(driverClassName);
            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            LOGGER.info("DataSource bean created");
            return dataSource;
        } catch(Exception e) {
            LOGGER.error("DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
