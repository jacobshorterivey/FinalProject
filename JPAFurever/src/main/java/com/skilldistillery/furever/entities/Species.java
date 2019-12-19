package com.skilldistillery.furever.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Species {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	// @JsonIgnore
	@OneToMany(mappedBy = "species")
	private List<Breed> breeds;

	// CONSTRUCTORS
	public Species(int id, String name, List<Breed> breeds) {
		super();
		this.id = id;
		this.name = name;
		this.breeds = breeds;
	}

	public Species() {
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

	public List<Breed> getBreeds() {
		return breeds;
	}

	public void setBreeds(List<Breed> breeds) {
		this.breeds = breeds;
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
		Species other = (Species) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Species [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

}
