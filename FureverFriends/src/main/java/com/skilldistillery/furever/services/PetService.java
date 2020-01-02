package com.skilldistillery.furever.services;

import java.util.List;

import com.skilldistillery.furever.entities.Pet;
import com.skilldistillery.furever.entities.Shelter;

public interface PetService {

	public List <Pet> displayAllPets();

	Pet showPet(int id);
	
	Pet createPet(Pet pet);
	
	Pet update(int id, Pet pet);
	
	boolean deletePet(int id);

	Pet getLucky();

	Shelter findPetsShelter(int id);
	
	List<Pet> searchByNameTraitsBreed(String key);
}
