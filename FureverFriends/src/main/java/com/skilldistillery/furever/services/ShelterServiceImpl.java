package com.skilldistillery.furever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Shelter;
import com.skilldistillery.furever.repositories.ShelterRepository;

@Service
public class ShelterServiceImpl implements ShelterService {
	
	// FIELDS
	
	@Autowired
	private ShelterRepository shelterRepo;
	
	@Override
	public List<Shelter> findAllShelters() {
		return shelterRepo.findAll();
	}
	
	@Override
	public Shelter findShelter(Integer id) {
		return shelterRepo.findByShelterId(id);
	}
	
	@Override
	public Shelter createShelter(Shelter shelter) {
		shelterRepo.saveAndFlush(shelter);
	}
}
