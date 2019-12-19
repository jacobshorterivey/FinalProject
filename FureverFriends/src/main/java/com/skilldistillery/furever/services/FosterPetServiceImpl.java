package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.FosterPetRepository;

@Service
public class FosterPetServiceImpl implements FosterService {

	// FIELDS
	
	@Autowired
	private FosterPetRepository fosterPetRepo;
}
