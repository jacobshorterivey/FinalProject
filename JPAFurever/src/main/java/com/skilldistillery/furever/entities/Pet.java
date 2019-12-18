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

	@ManyToMany
	@JoinTable(name="pet_trait",
    joinColumns=@JoinColumn(name="pet_id"),
    inverseJoinColumns=@JoinColumn(name="trait_id")
  )
	private List<Trait> traits;
	
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

	
	
	public List<Trait> getTraits() {
		return traits;
	}

	public void setTraits(List<Trait> traits) {
		this.traits = traits;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (adopted ? 1231 : 1237);
		result = prime * result + age;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (fixed ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shelter == null) ? 0 : shelter.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((specialConditions == null) ? 0 : specialConditions.hashCode());
		result = prime * result + weight;
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
		if (adopted != other.adopted)
			return false;
		if (age != other.age)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (fixed != other.fixed)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shelter == null) {
			if (other.shelter != null)
				return false;
		} else if (!shelter.equals(other.shelter))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (specialConditions == null) {
			if (other.specialConditions != null)
				return false;
		} else if (!specialConditions.equals(other.specialConditions))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", color=" + color + ", name=" + name + ", size=" + size + ", age=" + age + ", weight="
				+ weight + ", adopted=" + adopted + ", fixed=" + fixed + ", specialConditions=" + specialConditions
				+ ", shelter=" + shelter + "]";
	}
}
