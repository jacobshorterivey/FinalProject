package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.ShelterRepository;

@Service
public class ShelterServiceImpl implements ShelterService {
	
	// FIELDS
	
	@Autowired
	private ShelterRepository shelterRepo;
}
