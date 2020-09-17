package com.multiple.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.multiple.authentication.filter.UserpasswordAuthFilter;
import com.multiple.authentication.provider.OTPAuthProvider;
import com.multiple.authentication.provider.UserPasswordAuthprovider;

@Configuration
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserpasswordAuthFilter filter;
	@Autowired
	UserPasswordAuthprovider userPasswordAuthProvider;
	@Autowired
	OTPAuthProvider otpAuthProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userPasswordAuthProvider).authenticationProvider(otpAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(filter, BasicAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
