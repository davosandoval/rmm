/**
 * 
 */
package com.ninja.rmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ninja.rmm.model.Service;

/**
 * @author dsandoval
 *
 */
public interface ServiceRepository extends JpaRepository<Service, String> {

}
