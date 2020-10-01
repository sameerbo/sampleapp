package com.tds.ems.sample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tds.ems.sample.entities.Product;
import com.tds.ems.sample.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<Product> getAllProducts() {
        List<Product> allProduct = productService.getAllProducts();
        System.out.println("All Products " + allProduct);
        return allProduct;
    }
    
    @GetMapping("/product/{id}")
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public Product getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id);
        
    }
    
    @PutMapping("/products")
    @PreAuthorize("hasPermission(#product, 'WRITE')")
    public Product updateProduct(@RequestBody Product product) {
         return productService.updateProduct(product);
        
    }
    
    @DeleteMapping("/products")
    @PreAuthorize("hasPermission(#product, 'DELETE')")
    public void deleteProduct(@RequestBody Product product) {
         productService.deleteProduct(product);
        
    }
}

