/**
 * 
 */
package com.praful.security.config;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * @author jack
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return null;
			}
		}).and().csrf().ignoringAntMatchers("/contact").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				/* .disable() */
				.authorizeRequests()
				/*
				 * .antMatchers("/account").hasAuthority("WRITE")
				 * .antMatchers("/balance").hasAuthority("READ")
				 * .antMatchers("/cards").authenticated()
				 * .antMatchers("/loans").hasAuthority("DELETE")
				 */
				.antMatchers("/account").hasRole("ADMIN")
				.antMatchers("/balance").hasRole("USER")
				.antMatchers("/cards").authenticated()
				.antMatchers("/loans").hasRole("ADMIN")
				.antMatchers("/contact").permitAll()
				.antMatchers("/notices").permitAll().and().formLogin().and().httpBasic();

		/*
		 * httpSecurity.authorizeRequests((requests) ->
		 * requests.anyRequest().authenticated()); httpSecurity.formLogin();
		 * httpSecurity.httpBasic();
		 */
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
		return new BCryptPasswordEncoder();
	}

}
