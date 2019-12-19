package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {
	
	// FIELDS
	
	@Autowired
	private SkillRepository skillRepo;
	
}
