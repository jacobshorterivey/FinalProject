package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.furever.repositories.UserRepository;

public class UserServiceImpl {
	
	// FIELDS
	
	@Autowired
	private UserRepository userRepo;
}
