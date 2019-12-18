package com.skilldistillery.furever.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trait {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	// CONSTRUCTORS

	public Trait(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	// Getters and Setters, To String

	public Trait() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Trait [id=" + id + ", description=" + description + "]";
	}
	
	
	
}
