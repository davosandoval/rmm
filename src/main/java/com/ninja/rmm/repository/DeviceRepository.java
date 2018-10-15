package com.ninja.rmm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ninja.rmm.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
	@Query("select t from Device t where t.customer.id = ?1")
	List<Device> findByUser(Long id);
	
	@Query("select t from Device t where t.customer.id = ?1 and t.name = ?2")
	Device findByUserAndName(Long id, String name);
}
