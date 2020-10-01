package com.tds.ems.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.ems.sample.entities.User;
import com.tds.ems.sample.repository.UserRepository;

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
