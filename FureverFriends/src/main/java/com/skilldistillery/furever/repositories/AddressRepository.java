package com.skilldistillery.furever.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
