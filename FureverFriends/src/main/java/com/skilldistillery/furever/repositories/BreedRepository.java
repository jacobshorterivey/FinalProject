package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Breed;

public interface BreedRepository extends JpaRepository<Breed, Integer> {

}
