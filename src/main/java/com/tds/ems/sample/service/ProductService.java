package com.tds.ems.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.ems.sample.entities.Product;
import com.tds.ems.sample.repository.ProductRepository;

    @Service  
    public class ProductService   
    {  
    @Autowired  
    ProductRepository producRepository;  
    
    public List<Product> getAllProducts()   
    {  
    List<Product> p = new ArrayList<Product>();  
    producRepository.findAll().forEach(product -> p.add(product));  
    return p;  
    }  
      
    public Product getProductById(int id)   
    {  
    	return producRepository.findById(id);  
    }  
      
    public Product updateProduct(Product product)   
    {  
        return producRepository.save(product);  
    }
    
    public void deleteProduct(Product product)
    {
      	producRepository.delete(product);
 
    }
  
    }