package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	public Account findById(int id);
}
