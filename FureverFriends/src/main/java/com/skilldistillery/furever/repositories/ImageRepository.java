package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{
	
}
