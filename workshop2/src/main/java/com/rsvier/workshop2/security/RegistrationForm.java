package com.rsvier.workshop2.security;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.rsvier.workshop2.domain.Account;

import lombok.Data;

/* This class has duty collect the account info from the form and then
 * return the Account with password encoded.
 */

@Data
public class RegistrationForm {
	
	@NotBlank
	//@Pattern(regexp=".+@.+\\.[a-z]+", message="Geen geldige e-mail invoer.")
	private String email;
	
	@NotBlank
	//@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", 
	//	message="Wachtwoord moet minimaal 8 karakters, een letter en een nummer")
	private String password;

	
	public Account ToAccount(PasswordEncoder passwordEncoder) {
		
		return new Account(email,passwordEncoder.encode(password));
	}

}
