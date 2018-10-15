package com.ninja.rmm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.rmm.exception.DuplicatedResourceFoundException;
import com.ninja.rmm.exception.ResourceNotFoundException;
import com.ninja.rmm.model.Customer;
import com.ninja.rmm.model.Service;
import com.ninja.rmm.model.ServiceCustomer;
import com.ninja.rmm.repository.ServiceCustomerRepository;
import com.ninja.rmm.repository.ServiceRepository;
import com.ninja.wrapper.ServiceCustomerSuccessfulWraper;
import com.ninja.wrapper.SuccessfulWrapper;

@RestController
@RequestMapping("/rmm")
public class GeneralServicesController extends BaseController{
	
	
	@Autowired
	private ServiceCustomerRepository serviceCustomerRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	
	
	@GetMapping("/serviceCustomer/{user}")
	public List<ServiceCustomer> getServicesByUser(@PathVariable(value = "user")String user){
		Customer customer = getCustomerByUser(user);
		
		List<ServiceCustomer> serviceCustomerList = serviceCustomerRepository.findByUser(customer.getId());
		return serviceCustomerList;
	}
	
	@PostMapping("/serviceCustomer/{user}/{serviceId}")
	public ResponseEntity<ServiceCustomerSuccessfulWraper> addServiceToUser(@PathVariable(value = "user")String user, @PathVariable(value = "serviceId")String serviceId){
		Customer customer = getCustomerByUser(user);
		Service service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("serviceCustomer", "service", serviceId));
		
		ServiceCustomer serviceCustomer = new ServiceCustomer();
		serviceCustomer.setCustomer(customer);
		serviceCustomer.setService(service);
		try {
			return new ResponseEntity<ServiceCustomerSuccessfulWraper>(
					new ServiceCustomerSuccessfulWraper(serviceCustomerRepository.save(serviceCustomer), "Service inserted successfully"), HttpStatus.OK);
		}catch(Exception e) {
			throw new DuplicatedResourceFoundException("serviceCustomer", "serviceId", serviceId);
		}
	}
	
	@DeleteMapping("/serviceCustomer/{user}/{serviceId}")
	public ResponseEntity<SuccessfulWrapper> deleteServiceToUser(@PathVariable(value = "user")String user, @PathVariable(value = "serviceId")String serviceId){
		Customer customer = getCustomerByUser(user);
		Service service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("serviceCustomer", "service", serviceId));
		
		ServiceCustomer serviceCustomer = serviceCustomerRepository.findOneByUserAndService(customer.getId(), service.getId());
		if(serviceCustomer == null) {
			throw new ResourceNotFoundException("serviceCustomer", "service", serviceId);
		}
		serviceCustomerRepository.delete(serviceCustomer);
		
		return new ResponseEntity<SuccessfulWrapper>(new SuccessfulWrapper("Service deleted successful"), HttpStatus.OK);
	}
	

}
