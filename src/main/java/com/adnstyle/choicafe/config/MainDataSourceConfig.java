package com.adnstyle.choicafe.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.adnstyle.choicafe.repository.maindb", sqlSessionFactoryRef = "mainDBSessionFactory")
public class MainDataSourceConfig {


    @Bean(name = "mainDBDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.maindb.datasource")
    public DataSource mainDB() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "mainDBSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mainDBDataSource") DataSource db1DataSource, ApplicationContext applicationContext) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(db1DataSource);
        sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mybatis/**/*Mapper.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "mainDBSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory db1sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(db1sqlSessionFactory);
    }

    @Bean(name = "mainDBtransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("mainDBDataSource") DataSource db1DataSource) {
        return new DataSourceTransactionManager(db1DataSource);
    }
}
