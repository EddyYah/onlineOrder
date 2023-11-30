package com.laioffer.onlineOrder;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableWebMvc
public class ApplicationConfig {


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        String PACKAGE_NAME = "com.laioffer.onlineOrder.entity";
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGE_NAME);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }


    @Bean
    public DataSource dataSource() {
        String RDS_ENDPOINT = "laiproject-instance.cmyzs4ks5ll7.us-east-1.rds.amazonaws.com";
        String USERNAME = "admin";
        String PASSWORD = "onlineorderproject";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + RDS_ENDPOINT + ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }
}
