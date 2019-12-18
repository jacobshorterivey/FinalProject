package com.skilldistillery.furever.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pet {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String color;
	private String name;
	private String size;
	private int age;
	private int weight;
	private boolean adopted;
	private boolean fixed;
	
	@Column(name = "special_conditions")
	private String specialConditions;
	
	@ManyToOne
	@JoinColumn(name = "shelter_id")
	private Shelter shelter;

	
	// CONSTRUCTORS
	public Pet() {}
	
	public Pet(int id, String color, String name, String size, int age, int weight, boolean adopted, boolean fixed,
			String specialConditions, Shelter shelter) {
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
	}


	// GETTERS, SETTERS, TOSTRING, EQUALS
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

	@Override
	public String toString() {
		return "Pet [id=" + id + ", color=" + color + ", name=" + name + ", size=" + size + ", age=" + age + ", weight="
				+ weight + ", adopted=" + adopted + ", fixed=" + fixed + ", specialConditions=" + specialConditions
				+ ", shelter=" + shelter + "]";
	}
}
