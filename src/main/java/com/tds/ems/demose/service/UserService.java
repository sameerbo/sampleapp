package com.tds.ems.demose.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.ems.demose.entities.User;
import com.tds.ems.demose.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

	
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    
    public User saveUser(User user) {
    	return userRepository.save(user);
    }
}
