package com.adnstyle.choicafe.config;


import com.zaxxer.hikari.HikariDataSource;
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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.adnstyle.choicafe.repository.subdb", sqlSessionFactoryRef = "subDBSessionFactory")
public class SubDataSourceConfig {

    @Bean(name = "subDBDataSource")
    @ConfigurationProperties(prefix = "spring.subdb.datasource")
    public DataSource subDB() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="subDBSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("subDBDataSource") DataSource db2DataSource, ApplicationContext applicationContext) throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(db2DataSource);
        sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mybatis/**/*Mapper.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name="subDBSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory db2sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(db2sqlSessionFactory);
    }

    @Bean(name = "subDBtransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("subDBDataSource") DataSource db2DataSource) {
        return new DataSourceTransactionManager(db2DataSource);
    }

}
