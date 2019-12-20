package com.skilldistillery.furever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.furever.entities.Pet;
import com.skilldistillery.furever.services.PetService;

@RestController
@RequestMapping("api/pet")
public class PetController {

	@Autowired
	private PetService svc;
	
	@GetMapping
	private List<Pet> index() {
		return svc.displayAllPets();
	}	
	
	@GetMapping("{id}")
	private Pet show(@PathVariable int id, HttpServletResponse response) {
		Pet pet = svc.showPet(id);
		if (pet == null) {
			response.setStatus(404);
		}
		return pet;
	}
	
	@GetMapping("lucky")
	private Pet lucky(@PathVariable int id, HttpServletResponse response) {
		Pet pet = svc.getLucky();
		if (pet == null) {
			response.setStatus(404);
		}
		return pet;
	}
	
	@PostMapping
	public Pet create(@RequestBody Pet pet, HttpServletResponse response) {
		Pet newPet = svc.createPet(pet);
		if (newPet == null) {
			response.setStatus(500);
		}
		return newPet;
	}
	
	@PutMapping("{id}")
	public Pet update(@PathVariable int id, @RequestBody Pet newPet, HttpServletResponse response) {
		Pet pet1 = svc.update(id, newPet);
		if (pet1 == null) {
			response.setStatus(404);
		}
		return pet1;
	}
	@DeleteMapping("{id}")
	  void deletePet(@PathVariable Integer id) {
	    svc.deletePet(id);
	  }
}
