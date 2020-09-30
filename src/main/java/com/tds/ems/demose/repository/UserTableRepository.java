package com.tds.ems.demose.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tds.ems.demose.entities.UserTable;

@Repository
public interface UserTableRepository extends JpaRepository<UserTable, Integer> {
	
	@Transactional
	@Modifying
	@Query("update UserTable ut set ut.isLocked = case when ut.isLocked = true then false else true end,"
			+ "updatedAt = CURRENT_DATE,updatedBy = :name where id = :id")
	public void toggleUserTableLockStatus(String name,int id); 
	
	@Transactional
	@Modifying
	@Query("update UserTable ut set ut.isActive = case when ut.isActive = true then false else true end,"
			+ "updatedAt = CURRENT_DATE,updatedBy = :name where id = :id")
	
	public void toggleUserTableActiveStatus(String name,int id);
}
