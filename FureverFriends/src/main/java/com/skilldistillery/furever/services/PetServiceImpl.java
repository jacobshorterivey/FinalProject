package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.PetRepository;

@Service
public class PetServiceImpl implements PetService {
	
	// FIELDS
	
	@Autowired
	private PetRepository petRepo;
}
