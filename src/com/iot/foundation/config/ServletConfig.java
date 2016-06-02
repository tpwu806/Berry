package com.iot.foundation.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.iot.foundation.intercepts.ModuleStatusHandlerInterceptor;

@Configuration
@ComponentScan(basePackages = {"com.iot.usermgmt.controller", "com.iot.foundation.controller","com.iot.supervise.controller",
		 "com.iot.sensor.controller","com.iot.device.controller","com.iot.foundation.scheduler","com.iot.threshold.controller"})
@EnableWebMvc
@EnableScheduling
@PropertySource({ "classpath:application.properties" })
public class ServletConfig extends WebMvcConfigurerAdapter {

	//@Autowired
	//private ModuleStatusHandlerInterceptor moduleStatusHandlerInterceptor;

	@Bean
	public InternalResourceViewResolver configureViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);

		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setExposedContextBeanNames(new String[] { "properties" });

		return resolver;
	}

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(new String[] { "/css/**" }).addResourceLocations(new String[] { "/css/" });
		registry.addResourceHandler(new String[] { "/images/**" }).addResourceLocations(new String[] { "/images/" });
		registry.addResourceHandler(new String[] { "/js/**" }).addResourceLocations(new String[] { "/js/" });
		registry.addResourceHandler(new String[] { "/fonts/**" }).addResourceLocations(new String[] { "/fonts/" });
	}

	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();

		resolver.setFallbackPageable(new PageRequest(0, 40));
		argumentResolvers.add(resolver);
	}

	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(this.moduleStatusHandlerInterceptor);
	}
}