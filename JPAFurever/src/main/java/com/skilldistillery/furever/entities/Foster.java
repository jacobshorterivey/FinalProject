package com.skilldistillery.furever.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Foster {
	// FIELDS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "max_foster")
	private int maxFoster;
	
	@OneToMany(mappedBy = "foster")
	private List<FosterPet> fosterPets;

	
//	private User user;
	
	// CONSTRUCTORS
	public Foster(int id, int maxFoster) {
		super();
		this.id = id;
		this.maxFoster = maxFoster;
	}

	public Foster() {
		super();
	}

	// GETTERS, SETTERS, TOSTRING, EQUALS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxFoster() {
		return maxFoster;
	}

	public void setMaxFoster(int maxFoster) {
		this.maxFoster = maxFoster;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Foster [id=").append(id).append(", maxFoster=").append(maxFoster).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + maxFoster;
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
		Foster other = (Foster) obj;
		if (id != other.id)
			return false;
		if (maxFoster != other.maxFoster)
			return false;
		return true;
	}
	  

}

