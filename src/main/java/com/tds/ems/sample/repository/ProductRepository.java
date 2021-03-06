package com.tds.ems.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

import com.tds.ems.sample.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

public Product findById(int id);
}
