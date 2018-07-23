package com.packtpub.springdata.jpa.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@ComponentScan(basePackages = {"com.packtpub.springdata.jpa.controller", "com.packtpub.springdata.jpa.service" })
@EnableJpaRepositories("com.packtpub.springdata.jpa.repository")
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class ApplicationContext extends WebMvcConfigurerAdapter  {
	
	@Resource
	private Environment env;
	
	@Primary
	@Bean//(name="dataSource")
	public DataSource dataSource(){
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass(env.getRequiredProperty("db.driver"));
		ds.setJdbcUrl(env.getRequiredProperty("db.url"));
		ds.setUsername(env.getRequiredProperty("db.username"));
		ds.setPassword(env.getRequiredProperty("db.password"));
		return ds;
	}
	
	@Primary
	@Bean//(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		LocalContainerEntityManagerFactoryBean emf = 
				new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPackagesToScan(env.getRequiredProperty("entitymanager.packages.to.scan"));
		//emf.setPersistenceUnitName("book");
		
		Properties p = new Properties();
		p.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		p.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		p.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		p.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
		p.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		
		emf.setJpaProperties(p);
		
		return emf;
	}
	
	@Primary
	@Bean//(name="transactionManager")
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new 
				JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
