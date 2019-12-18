package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Species;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {

}
