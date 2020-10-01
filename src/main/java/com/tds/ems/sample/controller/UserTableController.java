package com.tds.ems.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tds.ems.sample.entities.UserTable;
import com.tds.ems.sample.service.UserTableService;

@RestController
public class UserTableController {

	@Autowired
	UserTableService userTableService;
	
	@GetMapping("/v1/user")
	public List<UserTable> getAllUsers() {
		return userTableService.getAllUsersTable();
	}
	
	@PutMapping("/v1/userLock/{id}")
	public void toggleUserTableLockStatus(@PathVariable  int id) {
		
		 userTableService.toggleUserTableLockStatus(id);
	}
	
	@PutMapping("/v1/userActivate/{id}")
	public void toggleUserTableActivateStatus(@PathVariable  int id) {
		
		 userTableService.toggleUserTableActivateStatus(id);
	}
}
