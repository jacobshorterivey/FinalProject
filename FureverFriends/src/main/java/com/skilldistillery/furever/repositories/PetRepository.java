package com.skilldistillery.furever.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Breed;
import com.skilldistillery.furever.entities.Pet;
import com.skilldistillery.furever.entities.Shelter;
import com.skilldistillery.furever.entities.Trait;

public interface PetRepository extends JpaRepository<Pet, Integer>{

	public List <Pet> findByBreed(Breed breed);
	public List<Pet> findByTraits_Id(int id);
	public List<Pet> findByBreed_Species_Id(int id);
	public List<Pet> findByShelter_Id(int id);
}
