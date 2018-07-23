package com.packtpub.springdata.jpa.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.packtpub.springdata.jpa.config.ApplicationContext;

public class DataJPAExampleInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//loading application context
		AnnotationConfigWebApplicationContext rootContext = new 
				AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationContext.class);
		
		//dipatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		//context loader listener
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
	}

	
}
