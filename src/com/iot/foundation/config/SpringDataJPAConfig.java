package com.iot.foundation.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = {"com.iot.usermgmt.dao","com.iot.supervise.dao",
		"com.iot.device.dao","com.iot.sensor.dao","com.iot.threshold.dao"})
public class SpringDataJPAConfig {
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_CONNECTION_CHARSET = "hibernate.connection.charSet";
	private static final String HIBERNATE_CONNECTION_CHARACTERENCODING = "hibernate.connection.characterEncoding";
	private static final String HIBERNATE_CONNECTION_USEUNICODE = "hibernate.connection.useUnicode";
	public static final String DB_SERVERNAME = "datasource.servername";
	public static final String DB_PORT = "datasource.port";
	public static final String DB_DBNAME = "datasource.dbname";
	public static final String DB_USER = "datasource.dbuser";
	public static final String DB_PWD = "datasource.dbpwd";

	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(100);
		config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
		
		
		
		config.addDataSourceProperty("serverName", this.env.getRequiredProperty("datasource.servername"));
		config.addDataSourceProperty("port", this.env.getRequiredProperty("datasource.port"));
		config.addDataSourceProperty("databaseName", this.env.getRequiredProperty("datasource.dbname"));
		config.addDataSourceProperty("user", this.env.getRequiredProperty("datasource.dbuser"));
		config.addDataSourceProperty("password", this.env.getRequiredProperty("datasource.dbpwd"));

		config.addDataSourceProperty("cachePrepStmts", Boolean.valueOf(true));
		config.addDataSourceProperty("prepStmtCacheSize", Integer.valueOf(250));
		config.addDataSourceProperty("prepStmtCacheSqlLimit", Integer.valueOf(2048));
		config.addDataSourceProperty("useServerPrepStmts", Boolean.valueOf(true));

		config.addDataSourceProperty("useUnicode", "true");
		
		//鑷畾涔夋湰鏈烘祴璇曢厤缃�
		try {
			java.net.InetAddress addr =  java.net.InetAddress.getLocalHost();
			List<String> locals =new ArrayList<String>();
			locals.add("TongPeng-Wu-PC");
			if (locals.contains(addr.getHostName())){
				config.addDataSourceProperty("serverName", this.env.getRequiredProperty("datasource.servername"));
			}
		} catch (UnknownHostException e) {
			//
		}

		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) throws ClassNotFoundException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		transactionManager.setJpaDialect(new HibernateJpaDialect());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(new String[] { "com.iot.usermgmt.domain","com.iot.supervise.domain",
				"com.iot.device.domain","com.iot.sensor.domain","com.iot.threshold.domain"});

		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", this.env.getRequiredProperty("hibernate.dialect"));
		jpaProperties.put("hibernate.format_sql", this.env.getRequiredProperty("hibernate.format_sql"));
		jpaProperties.put("hibernate.ejb.naming_strategy",this.env.getRequiredProperty("hibernate.ejb.naming_strategy"));
		jpaProperties.put("hibernate.show_sql", this.env.getRequiredProperty("hibernate.show_sql"));
		/*jpaProperties.put("hibernate.hbm2ddl.auto", this.env.getRequiredProperty("hibernate.hbm2ddl.auto"));*/
		jpaProperties.put("hibernate.connection.charSet", this.env.getRequiredProperty("hibernate.connection.charSet"));
		jpaProperties.put("hibernate.connection.characterEncoding",
				this.env.getRequiredProperty("hibernate.connection.characterEncoding"));
		jpaProperties.put("hibernate.connection.useUnicode",
				this.env.getRequiredProperty("hibernate.connection.useUnicode"));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}
}