package com.skilldistillery.furever.services;

import java.util.List;

import com.skilldistillery.furever.entities.Shelter;

public interface ShelterService {

	public List<Shelter> findAllShelters();

	public Shelter findShelter(Integer id);

	public Shelter createShelter(Shelter shelter);
	
}
