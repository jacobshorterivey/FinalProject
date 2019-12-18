package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.FosterPet;

public interface FosterPetRepository extends JpaRepository<FosterPet, Integer>{
	
}
