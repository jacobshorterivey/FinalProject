package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.FosterRepository;

@Service
public class FosterServiceImpl implements FosterService {

	// FIELDS
	
	@Autowired
	private FosterRepository fosterRepo;
}
