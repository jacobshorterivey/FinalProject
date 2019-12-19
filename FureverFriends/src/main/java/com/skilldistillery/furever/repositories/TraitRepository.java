package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Trait;

public interface TraitRepository extends JpaRepository<Trait, Integer>{

}
