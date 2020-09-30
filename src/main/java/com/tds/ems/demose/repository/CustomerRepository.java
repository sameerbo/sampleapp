package com.tds.ems.demose.repository;

import java.util.Optional;

//import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tds.ems.demose.entities.Customer;

//@JaversSpringDataAuditable
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Optional<Customer> findById(Long id);
}
