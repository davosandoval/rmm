package com.ninja.rmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ninja.rmm.model.Cost;

public interface CostRepository extends JpaRepository<Cost, String> {

}
