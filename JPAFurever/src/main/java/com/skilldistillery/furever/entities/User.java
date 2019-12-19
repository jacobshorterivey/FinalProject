package com.skilldistillery.furever.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private Integer age;
	private String phone;
	private boolean active;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany
	@JoinTable(name = "user_shelter", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "shelter_id"))
	private List<Shelter> shelters;

	@OneToMany(mappedBy = "user")
	private List<PetAdoption> adoptions;

	@ManyToMany
	@JoinTable(name = "volunteer_skill", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;

	@ManyToMany
	@JoinTable(name = "user_image", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
	private List<Image> images;
	
	// CONSTRUCTORS
	public User(int id, String username, String password, String fname, String lname, Integer age, String phone,
			boolean active, Address address, List<Shelter> shelters, List<PetAdoption> adoptions, List<Skill> skills,
			List<Image> images) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.phone = phone;
		this.active = active;
		this.address = address;
		this.shelters = shelters;
		this.adoptions = adoptions;
		this.skills = skills;
		this.images = images;
	}

	public User() {
		super();
	}
	
	// GETTERS, SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Shelter> getShelters() {
		return shelters;
	}

	public void setShelters(List<Shelter> shelters) {
		this.shelters = shelters;
	}

	public List<PetAdoption> getAdoptions() {
		return adoptions;
	}

	public void setAdoptions(List<PetAdoption> adoptions) {
		this.adoptions = adoptions;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=").append(username).append(", password=")
				.append(password).append(", fname=").append(fname).append(", lname=").append(lname).append(", age=")
				.append(age).append(", phone=").append(phone).append(", active=").append(active).append(", address=")
				.append(address).append("]");
		return builder.toString();
	}

}
