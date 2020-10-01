package com.tds.ems.sample.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="customer_programs")
@Audited
public class CustomerPrograms implements IEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Long customerId;
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	private String programName;
	private Date programStartDate;
	private Date programEndDate;
	

	

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Date getProgramStartDate() {
		return programStartDate;
	}

	public void setProgramStartDate(Date programStartDate) {
		this.programStartDate = programStartDate;
	}

	public Date getProgramEndDate() {
		return programEndDate;
	}

	public void setProgramEndDate(Date programEndDate) {
		this.programEndDate = programEndDate;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return this.id;
	}
	

}
