package com.tds.ems.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tds.ems.sample.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String username);
}
