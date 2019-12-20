package com.skilldistillery.furever.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Foster;
import com.skilldistillery.furever.entities.User;

public interface FosterRepository extends JpaRepository<Foster, Integer>{
	public Foster findByUser(User user);
	public List<Foster> findBySpeciesList_Id(int id);
	public List<Foster> findByBreedList_Id(int id);
	public List<Foster> findByTraitList_Id(int id);
	
	
}
