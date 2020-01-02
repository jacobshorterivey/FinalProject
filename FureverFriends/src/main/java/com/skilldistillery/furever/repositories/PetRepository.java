package com.skilldistillery.furever.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.furever.entities.Breed;
import com.skilldistillery.furever.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{

	public List<Pet> findByBreed(Breed breed);
	public List<Pet> findByTraits_Id(int id);
	public List<Pet> findByBreed_Species_Id(int id);
	public List<Pet> findByShelter_Id(int id);
	public List<Pet> findDistinctByNameLikeOrTraits_DescriptionLikeOrBreed_NameLikeOrBreed_DescriptionLike(String keyword1, String keyword2, String keyword3,  String keyword4);
}
