package com.ninja.wrapper;

import com.ninja.rmm.model.ServiceCustomer;

public class ServiceCustomerSuccessfulWraper extends SuccessfulWrapper {
	private ServiceCustomer serviceCustomer;
	
	public ServiceCustomerSuccessfulWraper(ServiceCustomer serviceCustomer, String message) {
		this(message);
		this.serviceCustomer = serviceCustomer;
	}
	private ServiceCustomerSuccessfulWraper(String message) {
		super(message);
	}
	public ServiceCustomer getServiceCustomer() {
		return serviceCustomer;
	}
	public void setServiceCustomer(ServiceCustomer serviceCustomer) {
		this.serviceCustomer = serviceCustomer;
	}

}
