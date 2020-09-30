package com.tds.ems.demose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tds.ems.demose.entities.User;
import com.tds.ems.demose.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PutMapping("/users")
	public User createUser(@RequestParam User user) {
		return userService.saveUser(user);
	}
}
