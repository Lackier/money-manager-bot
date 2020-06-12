package com.lackier.telegram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("com.lackier.telegram")
public class DatabaseConfig {
    @Resource
    private Environment env;

    private static final String hibernatePropertiesFileName = "hibernate.properties";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());
        return em;
    }

    private Properties getHibernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream(hibernatePropertiesFileName);
            properties.load(is);
            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("Cant find " + hibernatePropertiesFileName + " in classpath!", e);
        }
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSourceBuilder = new DriverManagerDataSource();
        dataSourceBuilder.setDriverClassName(env.getRequiredProperty("db.driver.classname"));
        dataSourceBuilder.setUrl(env.getRequiredProperty("db.url"));
        dataSourceBuilder.setUsername(env.getRequiredProperty("db.username"));
        dataSourceBuilder.setPassword(env.getRequiredProperty("db.password"));
        return dataSourceBuilder;
    }
}
