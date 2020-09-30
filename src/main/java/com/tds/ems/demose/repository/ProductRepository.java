package com.tds.ems.demose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

import com.tds.ems.demose.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

public Product findById(int id);
}
