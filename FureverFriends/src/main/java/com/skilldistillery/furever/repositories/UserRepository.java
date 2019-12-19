package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
