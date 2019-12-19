package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	// FIELDS

	@Autowired
	private AddressRepository addressRepo;
}
