package com.tds.ems.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tds.ems.sample.entities.CustomerPrograms;

public interface CustomerProgramsRepository extends JpaRepository<CustomerPrograms, Long>{
	public List<CustomerPrograms> findByCustomerId(Long id);

}
