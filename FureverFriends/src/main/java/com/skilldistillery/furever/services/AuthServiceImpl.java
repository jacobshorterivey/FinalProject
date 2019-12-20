package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Account;
import com.skilldistillery.furever.entities.User;
import com.skilldistillery.furever.repositories.AccountRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Account register(Account acc) {
		String encrypted = encoder.encode(acc.getPassword());
		acc.setPassword(encrypted);
		
//		if (type instanceof User) {
			acc.setActive(true);
			acc.setRole("user");
//		} else {
//			acc.setActive(false);
//			acc.setRole("shelter");
//		}
		
		accRepo.saveAndFlush(acc);
		
		return acc;
	}
}