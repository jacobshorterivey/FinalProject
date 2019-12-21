package com.skilldistillery.furever.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Skill;
import com.skilldistillery.furever.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public List<User> findBySkills(Skill s);

}
