package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Foster;

public interface FosterRepository extends JpaRepository<Foster, Integer>{

}
