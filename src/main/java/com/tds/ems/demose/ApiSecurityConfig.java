package com.tds.ems.demose;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@EnableWebSecurity
public class ApiSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	// @Autowired
    // private UserDetailsService userDetailsService;
    // @Autowired
    // private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;

    public ApiSecurityConfig() {
        super();
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService);
    // } 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        
        http
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .oauth2Login();
        // .userInfoEndpoint()
        // .oidcUserService(oidcUserService);

		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

}
