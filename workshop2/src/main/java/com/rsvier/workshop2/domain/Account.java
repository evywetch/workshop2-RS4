package com.rsvier.workshop2.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
public class Account implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@Column(unique=true)
	@NotBlank
	//@Pattern(regexp=".+@.+\\.[a-z]+", message="Geen geldige e-mail invoer.")
	private String email;
	
	@NotBlank
	//@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", 
	//	message="Wachtwoord moet minimaal 8 karakters, een letter en een nummer")
	private String password;

	public enum AccountType {
		ADMIN, EMPLOYEE, CUSTOMER
	}

	public Account() {

	}
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		/*returns a collection indicating that all users will have "CUSTOMER"
		 * granted authority.
		 */
		return Arrays.asList(new SimpleGrantedAuthority("CUSTOMER"));
	}

	@Override
	public String getUsername() {
		
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// return true to indicate that the users are active.
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// return true to indicate that the users are active.
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
