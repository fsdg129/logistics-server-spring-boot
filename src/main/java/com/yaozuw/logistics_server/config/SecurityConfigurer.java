package com.yaozuw.logistics_server.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.yaozuw.logistics_server.service.UserService;


@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
    @Autowired
	UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
    		// Configure CORS
        	.cors()
	    	//Disable CSRF protection	
	        .and()
        		.csrf().disable()
        	// Configure Spring Security to require HTTPS requests	
        		.requiresChannel()
        		.anyRequest()
        		.requiresSecure()
        	//Configure the method of authentication
        	.and()
        		.httpBasic()
        	//Configure the authorization for the domain /users
        	.and()
        		.authorizeRequests()
        		.antMatchers(HttpMethod.GET, "/api/users/").hasRole("MANAGER")
        		.antMatchers(HttpMethod.POST, "/api/users/").permitAll()
        		.antMatchers(HttpMethod.GET, "/api/users/{\\d+}").hasRole("VISITOR")
        		.antMatchers(HttpMethod.GET, "/api/users/**").permitAll()
        		.antMatchers(HttpMethod.HEAD, "/api/users/**").permitAll()
        		.antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("VISITOR")
        		.antMatchers(HttpMethod.PATCH, "/api/users/**").hasRole("VISITOR")
        		.antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("MANAGER");


    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	UserDetailsService userDetailsService = new UserDetailsService() {
    		
    		@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    			
    			com.yaozuw.logistics_server.entity.properties.User user = userService.getUserByUsername(username);
    			if(user == null) {
    				throw new UsernameNotFoundException(username);
    			}
    			return new User( user.getUsername(), user.getPassword(), user.getPriviledge().getAuthoritise() );
    		}
    		
    		
    	};
        auth.userDetailsService(userDetailsService).passwordEncoder(
        		PasswordEncoderFactories.createDelegatingPasswordEncoder()
        		);

          
    }
    
    /*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList( env.getProperty("app.cors.allowedOrigins") ));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","OPTION"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( env.getProperty("app.cors.mapping"), configuration);
        return source;
    }

	@Autowired
	private Environment env;
	*/

}
