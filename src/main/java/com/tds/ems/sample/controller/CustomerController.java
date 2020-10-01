package com.tds.ems.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tds.ems.sample.entities.Customer;
import com.tds.ems.sample.service.AclPermissionService;
import com.tds.ems.sample.service.CustomerService;


@RestController
public class CustomerController {
	
	@Autowired
    CustomerService customerService;
	
	@Autowired
    private AclPermissionService permissionService;
	
	@Autowired
	UserDetailsService userDetailsService;

    @GetMapping("/customers")
    @PostFilter("hasPermission(filterObject, 'CREATE') or hasRole('ROLE_CREATEUSER')")
    public List<Customer> getAllCustomers() {
    	return customerService.getAllCustomers();
    }
    
    @GetMapping("/customers/{id}")
    @PostAuthorize("hasPermission(returnObject, 'ADMINISTRATION')")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }
    
    @PutMapping("/customers")
    @PreAuthorize("hasPermission(#customer, 'CREATE')")
    public Customer updateCustomer(@RequestBody Customer customer) {
         return customerService.updateCustomer(customer);
    }
    
    @DeleteMapping("/customers")
    @PreAuthorize("hasPermission(#customer, 'READ')")
    public void deleteProduct(@RequestBody Customer customer) {
    	customerService.deleteProduct(customer);
    }
    
    @PostMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Customer addProduct(@RequestBody Customer customer, Authentication authentication) {
    	 Customer resultingCustomer = customerService.addCustomer(customer);
    	 permissionService.addPermissionForUser(resultingCustomer, BasePermission.ADMINISTRATION, authentication.getName());
    	 return resultingCustomer;
    }

    @Autowired
    //@PreAuthorize("hasRole('Users')")
    @RequestMapping("/")
    public String helloWorld() {
       return "Hello Users!";
    }
    
    //@PreAuthorize("hasRole('Group1')")
    @RequestMapping("/Group1")
    public String groupOne() {
       return "Hello Group 1 Users!";
    }
    
    //@PreAuthorize("hasRole('Group2')")
    @RequestMapping("/Group2")
    public String groupTwo() {
       return "Hello Group 2 Users!";
    }    
    
    @PostMapping("/customersbyrole")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasPermission('CREATE')")
    public Customer addProductByRole(@RequestBody Customer customer, Authentication authentication) {
    	 Customer resultingCustomer = customerService.addCustomer(customer);
         permissionService.addPermissionForAuthority(resultingCustomer, BasePermission.ADMINISTRATION, authentication.getName());
         permissionService.addPermissionForAuthority(resultingCustomer, BasePermission.CREATE, authentication.getName());
         permissionService.addPermissionForAuthority(resultingCustomer, BasePermission.READ, authentication.getName());
         permissionService.addPermissionForAuthority(resultingCustomer, BasePermission.CREATE, authentication.getName());
    	 return resultingCustomer;
    }
}
