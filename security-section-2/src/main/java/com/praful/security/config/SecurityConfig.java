/**
 * 
 */
package com.praful.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jack
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		/*
		 * httpSecurity.authorizeRequests((requests) ->
		 * requests.anyRequest().authenticated()); httpSecurity.formLogin();
		 * httpSecurity.httpBasic();
		 */

		/*
		 * httpSecurity.authorizeRequests().antMatchers("/account").authenticated().
		 * antMatchers("/balance").authenticated()
		 * .antMatchers("/cards").authenticated().antMatchers("/loans").authenticated().
		 * antMatchers("/contact")
		 * .permitAll().antMatchers("/notices").permitAll().and().formLogin().and().
		 * httpBasic();
		 */

		/*
		 * httpSecurity.authorizeRequests().anyRequest().denyAll().and().formLogin().and
		 * ().httpBasic();
		 */

		httpSecurity.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();

	}
}
