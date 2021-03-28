package com.microsoft.devcon.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	        .antMatchers("/", "/actuator/health").permitAll()
	        .anyRequest()
	        .authenticated()
	        .and()
	        .httpBasic();
	    
	    http.exceptionHandling().accessDeniedPage("/403");
	    http.csrf().disable();
	    http.headers().frameOptions().disable();
	  }
}