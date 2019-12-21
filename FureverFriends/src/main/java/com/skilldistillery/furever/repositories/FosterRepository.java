package com.skilldistillery.furever.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Foster;
import com.skilldistillery.furever.entities.User;

public interface FosterRepository extends JpaRepository<Foster, Integer>{
	public Foster findByUser(User user);
	public List<Foster> findBySpeciesListId(int id);
	public List<Foster> findByBreedListId(int id);
	public List<Foster> findByTraitListId(int id);
	
	
}
