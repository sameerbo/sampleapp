package com.tds.ems.demose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class DemoseApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(new Class[] {DemoseApplication.class,ApiSecurityConfig.class}, args);

	}
	

}
