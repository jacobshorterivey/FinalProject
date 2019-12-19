package com.skilldistillery.furever.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Breed;
import com.skilldistillery.furever.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{

	public List <Pet> SearchByBreed(Breed breed);
}
