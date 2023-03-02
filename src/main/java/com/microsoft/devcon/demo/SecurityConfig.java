package com.microsoft.devcon.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	        .antMatchers("/", "/actuator/health").permitAll()
	        .anyRequest()
	        .authenticated()
	        .and()
	        .httpBasic();
	    
	    http.exceptionHandling().accessDeniedPage("/403");
	    http.csrf().disable();
	    http.headers().frameOptions().disable();
        return http.build();
	  }
}