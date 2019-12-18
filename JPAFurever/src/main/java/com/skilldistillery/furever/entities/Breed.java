package com.skilldistillery.furever.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
//	@ManyToOne
//	@JoinColumn(name = "species_id")
//	private Species species;
	
	private String size;

	// CONSTRUCTORS
	public Breed(int id, String name, boolean hypoallergenic, String hairType, String description, String size) {
		super();
		this.id = id;
		this.name = name;
		this.hypoallergenic = hypoallergenic;
		this.hairType = hairType;
		this.description = description;
		this.size = size;
	}

	public Breed() {
		super();
	}

	// GETTERS, SETTERS, TOSTRING, EQUALS
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((hairType == null) ? 0 : hairType.hashCode());
		result = prime * result + (hypoallergenic ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hairType == null) {
			if (other.hairType != null)
				return false;
		} else if (!hairType.equals(other.hairType))
			return false;
		if (hypoallergenic != other.hypoallergenic)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Breed [id=").append(id).append(", name=").append(name).append(", hypoallergenic=")
				.append(hypoallergenic).append(", hairType=").append(hairType).append(", description=")
				.append(description).append(", size=").append(size).append("]");
		return builder.toString();
	}
	
	
	
}
