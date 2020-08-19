package com.yaozuw.logistics_server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class AppConfig {

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
		
	}
	
	@Bean
	WebMvcConfigurer createWebMvcConfigurer(@Autowired HandlerInterceptor[] interceptors) {
	    return new WebMvcConfigurer() {
	    	//Support static resources
	        @Override
	        public void addResourceHandlers(ResourceHandlerRegistry registry) {
	            registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	        }
	        
	        //Configure CORS
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	        	String[] allowedMethods = (String[]) env.getProperty("app.cors.allowedMethods", List.class).toArray(String[]::new);
	            registry.addMapping( env.getProperty("app.cors.mapping") )
	                    .allowedOrigins( env.getProperty("app.cors.allowedOrigins") )
	                    .allowedMethods( allowedMethods )
	                    .allowedHeaders( env.getProperty("app.cors.allowedHeaders") )
	                    .allowCredentials( env.getProperty("app.cors.allowCredentials", Boolean.class) )
	                    .maxAge( env.getProperty("app.cors.maxAge", Integer.class) );
	        }
	    };
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Autowired
	private Environment env;
	

}
