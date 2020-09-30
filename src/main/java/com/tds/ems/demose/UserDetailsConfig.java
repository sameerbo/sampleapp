package com.tds.ems.demose;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
    	System.out.println("inside userDetailsService");
        var uds = new InMemoryUserDetailsManager();
        var u1 = User.withUsername("john").password("12345").authorities("ROLE_ADMIN").build();
        var u2 = User.withUsername("anil").password("12345").authorities("ROLE_CREATEUSER").build();

        uds.createUser(u1);
        uds.createUser(u2);
  
        return uds;
    }

}