package com.tds.ems.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.ems.sample.entities.UserTable;
import com.tds.ems.sample.repository.UserTableRepository;

@Service
public class UserTableService {
	
	@Autowired
	UserTableRepository userTabelRepository;

	
	public List<UserTable> getAllUsersTable() {
		return userTabelRepository.findAll();
	}
	
	public void toggleUserTableLockStatus(int id) {
		Optional<UserTable> userTable = userTabelRepository.findById(id);
		 userTabelRepository.toggleUserTableLockStatus(userTable.get().getName(),id);
	}
	
	public void toggleUserTableActivateStatus(int id) {
		Optional<UserTable> userTable = userTabelRepository.findById(id);
		 userTabelRepository.toggleUserTableActiveStatus(userTable.get().getName(),id);
	}
}
