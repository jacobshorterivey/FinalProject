package com.skilldistillery.furever.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Foster;
import com.skilldistillery.furever.entities.User;

public interface FosterRepository extends JpaRepository<Foster, Integer>{
	public Foster findByUser(User user);
	public List<Foster> findBySpeciesId(int id);
	public List<Foster> findByBreedName(int id);
	public List<Foster> findByTraitId(int id);
	
	
}
