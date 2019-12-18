package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{

}
