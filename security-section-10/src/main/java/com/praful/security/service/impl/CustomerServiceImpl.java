/**
 * 
 */
package com.praful.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.praful.security.data.model.Customer;
import com.praful.security.data.model.SecurityCustomer;
import com.praful.security.repository.CustomerRepository;

/**
 * @author jack
 *
 */
@Service
@Transactional
public class CustomerServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer customer = this.customerRepository.findByEmail(username);
		if (customer == null)
			throw new UsernameNotFoundException("Username not found");

		return new SecurityCustomer(customer);
	}

}
