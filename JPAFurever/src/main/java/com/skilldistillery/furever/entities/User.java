package com.skilldistillery.furever.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	
	// CONSTRUCTORS
	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
	public User() {
		super();
	}

	// GETTERS, SETTERS, TOSTRING, EQUALS
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=").append(username).append("]");
		return builder.toString();
	}

	// METHODS
}
