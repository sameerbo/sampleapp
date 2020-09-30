package com.tds.ems.demose.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tds.ems.demose.entities.CustomerPrograms;

public interface CustomerProgramsRepository extends JpaRepository<CustomerPrograms, Long>{
	public List<CustomerPrograms> findByCustomerId(Long id);

}
