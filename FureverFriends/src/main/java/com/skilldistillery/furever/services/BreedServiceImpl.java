package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.BreedRepository;

@Service
public class BreedServiceImpl implements BreedService {

	// FIELDS

	@Autowired
	private BreedRepository breedRepo;
}
