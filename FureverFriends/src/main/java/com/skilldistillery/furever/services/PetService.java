package com.skilldistillery.furever.services;

import java.util.List;

import com.skilldistillery.furever.entities.Pet;

public interface PetService {

	public List <Pet> displayAllPets();

	Pet showPet(int id);
	
	Pet createPet(Pet pet);
	
	Pet update(int id, Pet pet);
	
	boolean deletePet(int id);

	Pet getLucky();
}
