package com.ninja.rmm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ninja.rmm.model.ServiceCustomer;

public interface ServiceCustomerRepository extends JpaRepository<ServiceCustomer, Long> {
	@Query("select t from ServiceCustomer t where t.customer.id = ?1")
	List<ServiceCustomer> findByUser(Long id);
	
	@Query("select t from ServiceCustomer t where t.customer.id = ?1 and t.service.id = ?2")
	ServiceCustomer findOneByUserAndService(Long userId, String serviceId);
}
