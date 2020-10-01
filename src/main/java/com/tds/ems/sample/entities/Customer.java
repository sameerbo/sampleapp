package com.tds.ems.sample.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@Table(name ="customer")
@Audited
public class Customer implements IEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Long id;

    private String name;
    private String department;
    
    @OneToMany
    private List<CustomerPrograms> customerPrograms;
	
    public List<CustomerPrograms> getCustomerPrograms() {
		return customerPrograms;
	}
	public void setCustomerPrograms(List<CustomerPrograms> customerPrograms) {
		this.customerPrograms = customerPrograms;
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
    
}
