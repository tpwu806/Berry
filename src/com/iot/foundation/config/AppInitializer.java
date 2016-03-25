package com.iot.foundation.config;

import java.util.ResourceBundle;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
	private static Logger LOG = LoggerFactory.getLogger(AppInitializer.class);

	@Resource
	private Environment env;
	public static final String SYSTEM_FILEUPLOAD_PATH = "system.file.storage.path";

	public void onStartup(ServletContext servletContext) {
		WebApplicationContext rootContext = createRootContext(servletContext);

		configureMyFilter(servletContext);
		
		configureSpringMvc(servletContext, rootContext);

//		configureSpringSecurity(servletContext, rootContext);

		configureEncodingFilter(servletContext);

		configureMultipartFilter(servletContext);
	}

	private WebApplicationContext createRootContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

		rootContext
				.register(new Class[] { SpringDataJPAConfig.class, WebAppConfig.class });
		rootContext.refresh();

		servletContext.addListener(new ContextLoaderListener(rootContext));
		servletContext.setInitParameter("defaultHtmlEscape", "true");

		return rootContext;
	}

	private void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();

		mvcContext.register(new Class[] { ServletConfig.class });
		mvcContext.setParent(rootContext);

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
				new DispatcherServlet(mvcContext));

		servlet.setLoadOnStartup(1);
		Set mappingConflicts = servlet.addMapping(new String[] { "/" });

		if (!mappingConflicts.isEmpty()) {
			for (Object s : mappingConflicts) {
				LOG.error("Mapping conflict: " + s);
			}
			throw new IllegalStateException("'webservice' cannot be mapped to '/'");
		}

		ResourceBundle rb = ResourceBundle.getBundle("application");
		String path = rb.getString("system.file.storage.path");

		servlet.setMultipartConfig(new MultipartConfigElement(path, 5242880L, 26214400L, 1048576));
	}

//	private void configureSpringSecurity(ServletContext servletContext, WebApplicationContext rootContext) {
//		FilterRegistration.Dynamic springSecurity = servletContext.addFilter("springSecurityFilterChain",
//				new DelegatingFilterProxy("springSecurityFilterChain", rootContext));
//
//		springSecurity.addMappingForUrlPatterns(null, true, new String[] { "/*" });
//	}

	private void configureEncodingFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter",
				new CharacterEncodingFilter());

		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, new String[] { "/*" });
	}

	private void configureMultipartFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("multipartFilter", new MultipartFilter());

		multipartFilter.addMappingForUrlPatterns(null, true, new String[] { "/*" });
	}
	
	private void configureMyFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("myFilter", new MyFilter());
		encodingFilter.addMappingForUrlPatterns(null, true, new String[] { "/*" });

	}
}