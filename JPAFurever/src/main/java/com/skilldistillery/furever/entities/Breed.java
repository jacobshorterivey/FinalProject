package com.skilldistillery.furever.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Breed {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private boolean hypoallergenic;

	@Column(name = "hair_type")
	private String hairType;

	private String description;
	
	@ManyToOne
	@JoinColumn(name = "species_id")
	private Species species;

	@JsonIgnore
	@OneToMany(mappedBy = "breed")
	private List<Pet> pets;

	private String size;

	// CONSTRUCTORS
	public Breed(int id, String name, boolean hypoallergenic, String hairType, String description, Species species,
			List<Pet> pets, String size) {
		super();
		this.id = id;
		this.name = name;
		this.hypoallergenic = hypoallergenic;
		this.hairType = hairType;
		this.description = description;
		this.species = species;
		this.pets = pets;
		this.size = size;
	}

	public Breed() {
		super();
	}

	// GETTERS, SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHypoallergenic() {
		return hypoallergenic;
	}

	public void setHypoallergenic(boolean hypoallergenic) {
		this.hypoallergenic = hypoallergenic;
	}

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	// EQUALS, TOSTRING
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Breed other = (Breed) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Breed [id=" + id + ", name=" + name + ", hypoallergenic=" + hypoallergenic + ", hairType=" + hairType
				+ ", description=" + description + ", species=" + species + ", pets=" + pets + ", size=" + size + "]";
	}



}
