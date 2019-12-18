package com.skilldistillery.furever.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "foster")
	private List<FosterReputation> fosterReputations;

	
	// CONSTRUCTORS
	public Foster() {}

	public Foster(int id, int maxFoster, List<FosterPet> fosterPets, User user,
			List<FosterReputation> fosterReputations) {
		super();
		this.id = id;
		this.maxFoster = maxFoster;
		this.fosterPets = fosterPets;
		this.user = user;
		this.fosterReputations = fosterReputations;
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


	public List<FosterPet> getFosterPets() {
		return fosterPets;
	}


	public void setFosterPets(List<FosterPet> fosterPets) {
		this.fosterPets = fosterPets;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<FosterReputation> getFosterReputations() {
		return fosterReputations;
	}


	public void setFosterReputations(List<FosterReputation> fosterReputations) {
		this.fosterReputations = fosterReputations;
	}

	@Override
	public String toString() {
		return "Foster [id=" + id + ", maxFoster=" + maxFoster + ", fosterPets=" + fosterPets + ", user=" + user
				+ ", fosterReputations=" + fosterReputations + "]";
	} 
	
}