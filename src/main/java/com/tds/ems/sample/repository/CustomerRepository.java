package com.tds.ems.sample.repository;

import java.util.Optional;

//import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tds.ems.sample.entities.Customer;

//@JaversSpringDataAuditable
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Optional<Customer> findById(Long id);
}
