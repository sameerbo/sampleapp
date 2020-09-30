package com.tds.ems.demose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.ems.demose.entities.Customer;
import com.tds.ems.demose.entities.CustomerPrograms;
import com.tds.ems.demose.repository.CustomerProgramsRepository;
import com.tds.ems.demose.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerProgramsRepository customerProgramRepository;
	
	public List<Customer> getAllCustomers()   
    {  
		return customerRepository.findAll();
    }  
      
    public Customer getCustomerById(Long id)   
    {  
    	Customer customer = customerRepository.findById(id).get();
    	List<CustomerPrograms> customerPrograms = customerProgramRepository.findByCustomerId(customer.getId());
    	customer.setCustomerPrograms(customerPrograms);
    	return customer;  
    } 
    
    public CustomerPrograms getCustomerProgramById(Long id)   
    {  
    	CustomerPrograms customerPrograms = customerProgramRepository.getOne(id);
    	return customerPrograms;  
    } 
    
      
    public Customer updateCustomer(Customer customer)   
    {  
        return customerRepository.save(customer);  
    }
    
    public void deleteProduct(Customer customer)
    {
    	customerRepository.delete(customer);
 
    }
    public Customer addCustomer(Customer customer)   
    {  
        return customerRepository.save(customer);  
    }
  

}
