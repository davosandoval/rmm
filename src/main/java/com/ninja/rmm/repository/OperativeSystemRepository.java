/**
 * 
 */
package com.ninja.rmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninja.rmm.model.OperativeSystem;

/**
 * @author dsandoval
 *
 */
@Repository
public interface OperativeSystemRepository extends JpaRepository<OperativeSystem, String> {

}
