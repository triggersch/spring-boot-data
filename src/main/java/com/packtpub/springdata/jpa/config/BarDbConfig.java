package com.packtpub.springdata.jpa.config;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "barEntityManagerFactory", 
                       transactionManagerRef = "barTransactionManager",
                       basePackages= {"com.packtpub.springdata.jpa.bar.repository"} )
public class BarDbConfig {
	
	@Resource
	private Environment env;
	
	@Bean(name="barDataSource")
	@ConfigurationProperties(prefix="bar.datasource")
	public DataSource dataSource(){

		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass(env.getRequiredProperty("bar.datasource.driver-class-name"));
		ds.setJdbcUrl(env.getRequiredProperty("bar.datasource.url"));
		ds.setUsername(env.getRequiredProperty("bar.datasource.username"));
		ds.setPassword(env.getRequiredProperty("bar.datasource.password"));
		return ds;
	}
	
	@Bean(name="barEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean
	  barEntityManagerFactory( EntityManagerFactoryBuilder builder,
			  @Qualifier("barDataSource") DataSource dataSource){
		
		return builder.dataSource(dataSource)
				.packages("com.packtpub.springdata.jpa.bar.model")
				.persistenceUnit("bar")
				.build();
	}
	
	
	@Bean(name="barTransactionManager")
	public PlatformTransactionManager barTransactionManager(
			   @Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory  ){
		return new JpaTransactionManager(barEntityManagerFactory);
	}

}
