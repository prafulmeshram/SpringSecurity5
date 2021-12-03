/**
 * 
 */
package com.praful.security.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.praful.security.data.model.Authority;
import com.praful.security.data.model.Customer;
import com.praful.security.repository.CustomerRepository;

/**
 * @author jack
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();

		Customer customer = this.customerRepository.findByEmail(username);

		if (customer != null) {
			if (passwordEncoder.matches(pwd, customer.getPwd())) {
				/*
				 * List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				 * authorities.add(new SimpleGrantedAuthority(customer.getRole()));
				 */

				return new UsernamePasswordAuthenticationToken(username, pwd,
						this.getGratedAuthorities(customer.getAuthorities()));

			} else {
				throw new BadCredentialsException("Bad Credentials");
			}
		} else {
			throw new BadCredentialsException("Bad Credentials");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private List<GrantedAuthority> getGratedAuthorities(Set<Authority> authorities) {

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		for (Authority authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}

		return grantedAuthorities;
	}

}
