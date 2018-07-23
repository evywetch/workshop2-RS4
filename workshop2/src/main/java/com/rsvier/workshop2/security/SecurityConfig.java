package com.rsvier.workshop2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	
	
	
	@Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.userDetailsService(userDetailsService)
		// use new StandardPasswordEncoder("53cr3t") as a password encoder
		.passwordEncoder(encoder());
		
	}
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 // Requests for "/login"  should be for users with a granted authority of "ROLE_USER".
		 .antMatchers("/login") 
	//	 .hasRole("ROLE_ACCOUNT")  .antMatchers("/**")  - app gets error when apply this line
		// All requests should be permitted to all users.
		.access("permitAll")   
		 // To use build-in login page instead of HTTP basic login page
		 .and()
		 .formLogin()
		 .loginPage("/login")
		 /* By default, a successful login will take the user directly to home page
		  By doing " .defaultSuccessUrl("/customer") " we tell spring to take user to "/cutomer"
		  after a successful login
		   */
		 .defaultSuccessUrl("/customer")
		 // Tell spring that the username field in our case named "email"
		 .usernameParameter("email")
		 .passwordParameter("password")
		 // Tell spring that after logout, go to home page
		 .and()
		 .logout()
		 .logoutSuccessUrl("/");
		 
	 }
	 
	
}
