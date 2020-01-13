package com.jio.vm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomAuthenticationProvider.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers("/**")
		.authenticated(); // Use Basic authentication

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Custom authentication provider - Order 1
		auth.authenticationProvider(customAuthenticationProvider);
		
	}
}
