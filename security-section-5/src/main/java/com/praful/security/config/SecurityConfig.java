/**
 * 
 */
package com.praful.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 * @author jack
 *
 */
@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		/*
		 * httpSecurity.authorizeRequests((requests) ->
		 * requests.anyRequest().authenticated()); httpSecurity.formLogin();
		 * httpSecurity.httpBasic();
		 */

		httpSecurity.authorizeRequests().antMatchers("/account").authenticated().antMatchers("/balance").authenticated()
				.antMatchers("/cards").authenticated().antMatchers("/loans").authenticated().antMatchers("/contact")
				.permitAll().antMatchers("/notices").permitAll().and().formLogin().and().httpBasic();

		/*
		 * httpSecurity.authorizeRequests().anyRequest().denyAll().and().formLogin().and
		 * ().httpBasic();
		 */

		/*
		 * httpSecurity.authorizeRequests().anyRequest().permitAll().and().formLogin().
		 * and().httpBasic();
		 */

	}

	/*
	 * protected void configure(AuthenticationManagerBuilder auth) throws Exception
	 * { auth.inMemoryAuthentication().withUser("admin").password("demo1234").
	 * authorities("admin").and()
	 * .passwordEncoder(NoOpPasswordEncoder.getInstance()); }
	 */

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { InMemoryUserDetailsManager userDetailsService = new
	 * InMemoryUserDetailsManager(); UserDetails user1 =
	 * User.withUsername("admin").password("demo1234").authorities("admin").build();
	 * UserDetails user2 =
	 * User.withUsername("user").password("demo1234").authorities("read").build();
	 * 
	 * userDetailsService.createUser(user1); userDetailsService.createUser(user2);
	 * auth.userDetailsService(userDetailsService); }
	 */

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
