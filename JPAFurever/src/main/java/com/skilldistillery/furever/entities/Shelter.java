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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Shelter {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String phone;
	private String email;
	private String username;
	private String password;
	private String role;
	private String name;
	private boolean active;

	@Column(name = "website_url")
	private String websiteUrl;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "shelter")
	private List<Pet> pets;

	@JsonIgnore
	@ManyToMany(mappedBy = "shelters")
	private List<User> users;

	@ManyToMany
	@JoinTable(name = "shelter_image", joinColumns = @JoinColumn(name = "shelter_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
	private List<Image> images;

	// CONSTRUCTORS
	public Shelter(int id, String phone, String email, String username, String password, String role, String name,
			boolean active, String websiteUrl, Address address, List<Pet> pets, List<User> users, List<Image> images) {
		super();
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.active = active;
		this.websiteUrl = websiteUrl;
		this.address = address;
		this.pets = pets;
		this.users = users;
		this.images = images;
	}

	public Shelter() {
		super();
	}

	// GETTERS, SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
		Shelter other = (Shelter) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shelter [id=").append(id).append(", phone=").append(phone).append(", email=").append(email)
				.append(", username=").append(username).append(", password=").append(password).append(", role=")
				.append(role).append(", name=").append(name).append(", active=").append(active).append(", websiteUrl=")
				.append(websiteUrl).append(", address=").append(address).append("]");
		return builder.toString();
	}
}
