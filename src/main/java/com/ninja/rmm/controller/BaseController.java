package com.ninja.rmm.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.ninja.rmm.exception.ResourceNotFoundException;
import com.ninja.rmm.model.Customer;
import com.ninja.rmm.repository.CustomerRepository;

public class BaseController {
	@Autowired
	protected CustomerRepository customerRepository;

	
	protected Customer getCustomerByUser(String user) {
		Customer customer = customerRepository.findByUser(user);
		if(customer == null) {
			throw new ResourceNotFoundException("customer", "customerName", user);
		}
		return customer;
	}

}
