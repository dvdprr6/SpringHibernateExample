package com.david.springhibernate.utils;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.david.springhibernate.persistence.dao.ICarDao;
import com.david.springhibernate.persistence.dao.IReservationDao;
import com.david.springhibernate.persistence.dao.IUserDao;
import com.david.springhibernate.persistence.dao.impl.CarDao;
import com.david.springhibernate.persistence.dao.impl.ReservationDao;
import com.david.springhibernate.persistence.dao.impl.UserDao;
import com.david.springhibernate.persistence.service.ICarService;
import com.david.springhibernate.persistence.service.IReservationService;
import com.david.springhibernate.persistence.service.IUserService;
import com.david.springhibernate.persistence.service.impl.UserService;
import com.david.springhibernate.persistence.service.impl.CarService;
import com.david.springhibernate.persistence.service.impl.ReservationService;

/*
 * REFERENCE: https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-hibernate4/src/main/java/com/baeldung/spring/PersistenceConfig.java#L88
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.david.springhibernate.persistence" }, transactionManagerRef = "hibernateTransactionManager")
@PropertySource({ "classpath:persistence-postgres.properties" })
@ComponentScan({ "com.david.springhibernate.persistence" })
public class PersistenceConfig {

	@Autowired
	private Environment envrionment;
	
	public PersistenceConfig() {
		super();
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.david.springhibernate.model.db" });
        sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
	}
	
	// Do I need this
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(restDataSource());
//        emf.setPackagesToScan(new String[] { "com.baeldung.persistence.model" });
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        emf.setJpaVendorAdapter(vendorAdapter);
//        emf.setJpaProperties(hibernateProperties());
//
//        return emf;
//    }
	
	@Bean
	public DataSource restDataSource() {
		final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(envrionment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(envrionment.getProperty("jdbc.url"));
        dataSource.setUsername(envrionment.getProperty("jdbc.user"));
        dataSource.setPassword(envrionment.getProperty("jdbc.pass"));
		
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		
		return transactionManager;
	}
	
	// Do I need this?
//	@Bean
//	public PlatformTransactionManager jpaTransactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        
//        return transactionManager;
//	}
	
	// Do I need this?
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
    
    @Bean
    public IUserDao userDao() {
    	return new UserDao();
    }
    
    @Bean
    public ICarDao carDao() {
    	return new CarDao();
    }
    
    @Bean
    public IReservationDao reservationDao() {
    	return new ReservationDao();
    }
    
    @Bean
    public IUserService userService() {
    	return new UserService();
    }
    
    @Bean
    public ICarService carService() {
    	return new CarService();
    }
	
    @Bean
    public IReservationService reservationService() {
    	return new ReservationService();
    }
    
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", envrionment.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.connection.pool_size", envrionment.getProperty("hibernate.connection.pool_size"));
		properties.setProperty("hibernate.c3p0.acquire_increment", envrionment.getProperty("hibernate.c3p0.acquire_increment"));
		properties.setProperty("hibernate.c3p0.idle_test_period", envrionment.getProperty("hibernate.c3p0.idle_test_period"));
		properties.setProperty("hibernate.c3p0.max_size", envrionment.getProperty("hibernate.c3p0.max_size"));
		properties.setProperty("hibernate.c3p0.max_statements", envrionment.getProperty("hibernate.c3p0.max_statements"));
		properties.setProperty("hibernate.c3p0.min_size", envrionment.getProperty("hibernate.c3p0.min_size"));
		properties.setProperty("hibernate.c3p0.timeout", envrionment.getProperty("hibernate.c3p0.timeout"));
		properties.setProperty("hibernate.show_sql", envrionment.getProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.hbm2ddl.auto", envrionment.getProperty("hibernate.hbm2ddl.auto"));
	
		return properties;
	}
}
