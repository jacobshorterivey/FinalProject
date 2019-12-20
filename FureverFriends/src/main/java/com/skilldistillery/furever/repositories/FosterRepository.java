package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Foster;
import com.skilldistillery.furever.entities.User;

public interface FosterRepository extends JpaRepository<Foster, Integer>{
	public Foster findByUser(User user);
}
