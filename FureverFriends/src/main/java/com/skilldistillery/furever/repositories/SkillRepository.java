package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer>{

}
