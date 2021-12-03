/**
 * 
 */
package com.praful.security.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.praful.security.data.model.Customer;

/**
 * @author jack
 *
 */
@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	List<Customer> findAllByEmail(String email);
	
	Customer findByEmail(String email);
}
