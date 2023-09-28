package com.kylanches.paineldecontrole.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "painelEntityManagerFactory", transactionManagerRef = "painelTransactionManager", basePackages = {
        "com.jcgontijo.paineldecontrole.repository" })

public class PainelDataSourceConfig {

    @Autowired
    private Environment environment;

    @Primary
    @Bean(name = "painelDataSource")
    @ConfigurationProperties(prefix = "painel.datasource")
    public HikariDataSource painelDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class)
                .build();
    }

    @PersistenceContext(unitName = "painelPU")
    @Primary
    @Bean(name = "painelEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("painelDataSource") DataSource datasource) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("painel.datasource.ddl.auto"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        return entityManagerFactoryBuilder
                .dataSource(datasource)
                .packages("com.kylanches.paineldecontrole.model")
                .persistenceUnit("painelPU")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "painelTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("painelEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
