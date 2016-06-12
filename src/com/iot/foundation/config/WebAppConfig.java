package com.iot.foundation.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.iot.usermgmt.service" ,"com.iot.supervise.service",
		"com.iot.device.service" ,"com.iot.sensor.service","com.iot.common.dao",
		"com.iot.common.utilities","com.iot.threshold.service"})
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource({ "classpath:application.properties" })
public class WebAppConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySources() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = { "properties" })
	public PropertiesFactoryBean properties() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("application.properties"));
		return bean;
	}
}

