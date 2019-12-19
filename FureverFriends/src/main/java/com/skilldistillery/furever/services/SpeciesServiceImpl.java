package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.SpeciesRepository;

@Service
public class SpeciesServiceImpl implements SpeciesService {
	
	// FIELDS
	
	@Autowired
	private SpeciesRepository speciesRepo;

}
