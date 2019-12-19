package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.TraitRepository;

@Service
public class TraitServiceImpl implements TraitService {

	// FIELDS
	
	@Autowired
	private TraitRepository traitRepo;
}
