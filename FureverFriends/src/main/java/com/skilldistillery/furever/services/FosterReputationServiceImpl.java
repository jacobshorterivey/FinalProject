package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.FosterReputationRepository;

@Service
public class FosterReputationServiceImpl implements FosterReputationService {
	// FIELDS
	
	@Autowired
	private FosterReputationRepository fosterReputationRepo;
}
