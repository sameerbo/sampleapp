package com.tds.ems.demose.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tds.ems.demose.entities.Customer;
import com.tds.ems.demose.entities.CustomerPrograms;
import com.tds.ems.demose.repository.CustomerProgramsRepository;
import com.tds.ems.demose.service.AclPermissionService;
import com.tds.ems.demose.service.CustomerService;

@RestController
public class CustomerProgramsController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
    private AclPermissionService permissionService;
	
	@Autowired
	CustomerProgramsRepository customerProgramRepository;
	
	@GetMapping("/customerprograms/{id}")
	@PostFilter("hasPermission(filterObject, 'CREATE')")
    public List<CustomerPrograms> getCustomerById(@PathVariable("id") Long id) {
        
		List<CustomerPrograms> objectList = new ArrayList<>();
        Optional<CustomerPrograms> customerPrograms = customerProgramRepository.findById(id);
        if (null != customerPrograms.get()) {
            objectList.add(customerPrograms.get());
        }
        return objectList;
	
	
	}
	
	 @PostMapping("/customerprograms")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 public CustomerPrograms addProduct(@RequestBody CustomerPrograms customerPrograms,Authentication authentication) {
		 CustomerPrograms customerProgram = customerProgramRepository.save(customerPrograms);
		 Customer customer = customerService.getCustomerById(customerProgram.getCustomerId());
    	 permissionService.inheritPermission(customer,customerProgram);
    	 return customerProgram;
	    }
}
