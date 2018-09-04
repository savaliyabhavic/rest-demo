package com.ikhodal.empdeptrest.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class CustomBeans
{
    @Value("${spring.datasource.url}")
    private String dataSourceURL;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dataSourceClassName;

//    @Bean
//    DataSource dataSource()
//    {
//        return DataSourceBuilder.create()
//                .driverClassName(this.dataSourceClassName)
//                .url(this.dataSourceURL)
//                .username(this.dataSourceUsername)
//                .password(this.dataSourcePassword)
//                .build();
//
//
//    }
//
//    @Bean(name = "customJDBCTemplate")
//    JdbcTemplate jdbcTemplate(DataSource dataSource){
//        return new JdbcTemplate(dataSource);
//    }
}
