package com.skilldistillery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.furever.services.PetService;
import com.skilldistillery.furever.services.ShelterService;

@RestController
@RequestMapping("api/pet")
public class PetController {

	@Autowired
	private PetService service;
}
