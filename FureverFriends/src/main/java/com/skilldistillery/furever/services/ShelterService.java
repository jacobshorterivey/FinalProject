package com.skilldistillery.furever.services;

import java.security.Principal;
import java.util.List;

import com.skilldistillery.furever.entities.Shelter;

public interface ShelterService {

	public List<Shelter> displayAllShelters();

	public Shelter showShelter(int id);

	public Shelter createShelter(Shelter shelter);

	public Shelter updateShelter(Shelter shelter, int id, Principal principal);
	
}
