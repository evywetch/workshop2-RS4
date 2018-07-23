package com.rsvier.workshop2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rsvier.workshop2.domain.Account;
import com.rsvier.workshop2.repository.AccountRepository;

/* This class has a duty to make sure that there is no null account returned.
 * If account is not found, it will throw UsernameNotFoundException and give 
 * the message.
 */

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService{

	private AccountRepository accountRepo;
	
	@Autowired
	public UserRepositoryUserDetailsService(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Account account = accountRepo.findByEmail(email);
		
		if(account != null) {
			return account;
		}
		
		throw new UsernameNotFoundException("Account'" + email +"'not found.");
		
		
	}

}
