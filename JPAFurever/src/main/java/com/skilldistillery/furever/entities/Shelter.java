package com.skilldistillery.furever.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Shelter {
	
	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String phone;
	private String email;
	private String username;
	private String password;
	private String role;
	private String name;
	private boolean active;
	
	@Column(name= "website_url")
	private String websiteUrl;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	
	// CONSTRUCTORS
	public Shelter() {};

	public Shelter(int id, String phone, String email, String username, String password, String role, String name,
			boolean active, String websiteUrl, Address address) {
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
	}
	

	// GETTERS, SETTERS, TOSTRING, EQUALS
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

	@Override
	public String toString() {
		return "Shelter [id=" + id + ", phone=" + phone + ", email=" + email + ", username=" + username + ", password="
				+ password + ", role=" + role + ", name=" + name + ", active=" + active + ", websiteUrl=" + websiteUrl
				+ ", address=" + address + "]";
	}	
}
