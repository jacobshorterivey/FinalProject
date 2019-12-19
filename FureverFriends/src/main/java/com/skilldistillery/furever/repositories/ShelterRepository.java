package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Integer>{
	public Shelter findById(int id);
}
