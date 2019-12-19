package com.skilldistillery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.furever.services.ShelterService;

@RestController
@RequestMapping("api/shelter")
public class ShelterController {
	
	@Autowired
	private ShelterService service;
	
//	@GetMapping
//	private List<Shelter> index();
	
}
