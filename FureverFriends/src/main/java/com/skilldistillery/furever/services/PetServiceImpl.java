package com.skilldistillery.furever.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Pet;
import com.skilldistillery.furever.repositories.PetRepository;

@Service
public class PetServiceImpl implements PetService {
	
	// FIELDS
	
	@Autowired
	private PetRepository petRepo;

	// Methods
	
	@Override
	public List<Pet> displayAllPets() {
		return petRepo.findAll();
	}
	
	@Override
	public Pet showPet(int id) {
		Pet pet = null;
		// TODO use findById, check optional.isPresent(), then optional 
		Optional<Pet> opt = petRepo.findById(id);
		if (opt.isPresent()) {
			pet = opt.get();
		}
		return pet;
	}
	
	@Override
	public Pet createPet(Pet pet) {
		Pet opt = petRepo.saveAndFlush(pet);
		return opt;
	}
	
	@Override
	public Pet update(int id, Pet pet) {
		Pet petToUpdate = null;
		Optional<Pet>opt = petRepo.findById(id);
		if (opt.isPresent()) {
			petToUpdate = opt.get();
			petToUpdate.setColor(pet.getColor());
			petToUpdate.setName(pet.getName());
			petToUpdate.setSize(pet.getSize());
			petToUpdate.setAge(pet.getAge());
			petToUpdate.setWeight(pet.getWeight());
			petToUpdate.setAdopted(pet.isAdopted());
			petToUpdate.setFixed(pet.isFixed());
			petRepo.saveAndFlush(petToUpdate);
		}
		return petToUpdate;
	}
	
	@Override
	public boolean deletePet(int id) {
		petRepo.deleteById(id);
		return false;
	}
}
