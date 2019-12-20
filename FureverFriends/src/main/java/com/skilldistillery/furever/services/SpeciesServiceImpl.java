package com.skilldistillery.furever.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Pet;
import com.skilldistillery.furever.entities.Species;
import com.skilldistillery.furever.repositories.SpeciesRepository;

@Service
public class SpeciesServiceImpl implements SpeciesService {
	
	// FIELDS
	
	@Autowired
	private SpeciesRepository speciesRepo;

	
}
