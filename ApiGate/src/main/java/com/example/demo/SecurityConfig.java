package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
		public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
	        http.csrf().disable()
	            .authorizeExchange(ex -> ex
	            		.pathMatchers(HttpMethod.GET,"/product/Product/**").permitAll()
	            		.pathMatchers(HttpMethod.POST,"/product/Product/**").hasRole("ADMIN")
	            		.pathMatchers(HttpMethod.PUT,"/product/product/**").hasRole("ADMIN")
	            		.pathMatchers(HttpMethod.DELETE,"/product/product/**").hasRole("ADMIN")     
	            		.anyExchange().authenticated())
	            .httpBasic();
	        return http.build();
	    }
	@Bean
	public ReactiveUserDetailsService userDetailsService()
	{
		var admin=User.withDefaultPasswordEncoder()
					  .username("Om")
					  .password("712118122")
					  .roles("ADMIN")
					  .build();
		
		
		var user=User.withDefaultPasswordEncoder()
				     .username("Ramesh")
				     .password("user")
				     .roles("USER")
				     .build();
		
		
		return new MapReactiveUserDetailsService(admin,user);
					 
	}}
