package com.meroproduction.rest.basic.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomizedSpringSecurityBasicAuth extends WebSecurityConfigurerAdapter {

    private final Log logger = LogFactory.getLog(CustomizedSpringSecurityBasicAuth.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	logger.info("Here is configured the customized basic authentication adapter");
	http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest()
		.authenticated().and()
		// .formLogin().and()
		.httpBasic();
    }

}
