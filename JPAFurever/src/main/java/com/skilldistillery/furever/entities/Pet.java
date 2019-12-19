package com.skilldistillery.furever.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pet {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String color;
	private String name;
	private String size;
	private int age;
	private int weight;
	private boolean adopted;
	private boolean fixed;

	@Column(name = "special_conditions")
	private String specialConditions;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "shelter_id")
	private Shelter shelter;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "breed_id")
	private Breed breed;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "pet_trait", joinColumns = @JoinColumn(name = "pet_id"), inverseJoinColumns = @JoinColumn(name = "trait_id"))
	private List<Trait> traits;
	
	@ManyToMany
	@JoinTable(name = "pet_image", joinColumns = @JoinColumn(name = "pet_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
	private List<Image> images;

	// CONSTRUCTORS
	public Pet(int id, String color, String name, String size, int age, int weight, boolean adopted, boolean fixed,
			String specialConditions, Shelter shelter, Breed breed, List<Trait> traits, List<Image> images) {
		super();
		this.id = id;
		this.color = color;
		this.name = name;
		this.size = size;
		this.age = age;
		this.weight = weight;
		this.adopted = adopted;
		this.fixed = fixed;
		this.specialConditions = specialConditions;
		this.shelter = shelter;
		this.breed = breed;
		this.traits = traits;
		this.images = images;
	}

	public Pet() {
		super();
	}

	// GETTERS, SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isAdopted() {
		return adopted;
	}

	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public String getSpecialConditions() {
		return specialConditions;
	}

	public void setSpecialConditions(String specialConditions) {
		this.specialConditions = specialConditions;
	}

	public Shelter getShelter() {
		return shelter;
	}

	public void setShelter(Shelter shelter) {
		this.shelter = shelter;
	}

	public List<Trait> getTraits() {
		return traits;
	}

	public void setTraits(List<Trait> traits) {
		this.traits = traits;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}
	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
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
		Pet other = (Pet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pet [id=").append(id).append(", color=").append(color).append(", name=").append(name)
				.append(", size=").append(size).append(", age=").append(age).append(", weight=").append(weight)
				.append(", adopted=").append(adopted).append(", fixed=").append(fixed).append(", specialConditions=")
				.append(specialConditions).append(", shelter=").append(shelter).append(", breed=").append(breed)
				.append("]");
		return builder.toString();
	}

}
