package com.skilldistillery.furever.entities;

import java.util.List;

import javax.persistence.CascadeType;
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

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "species_foster", joinColumns = @JoinColumn(name = "foster_id"), inverseJoinColumns = @JoinColumn(name = "species_id"))
	private List<Species> speciesList;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "foster_breed", joinColumns = @JoinColumn(name = "foster_id"), inverseJoinColumns = @JoinColumn(name = "breed_id"))
	private List<Breed> breedList;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "foster_trait", joinColumns = @JoinColumn(name = "foster_id"), inverseJoinColumns = @JoinColumn(name = "trait_id"))
	private List<Trait> traitList;

	
	// CONSTRUCTORS
	public Foster(int id, int maxFoster, List<FosterPet> fosterPets, User user,
			List<FosterReputation> fosterReputations, List<Species> speciesList, List<Breed> breedList,
			List<Trait> traitList) {
		super();
		this.id = id;
		this.maxFoster = maxFoster;
		this.fosterPets = fosterPets;
		this.user = user;
		this.fosterReputations = fosterReputations;
		this.speciesList = speciesList;
		this.breedList = breedList;
		this.traitList = traitList;
	}

	public Foster() {
		super();
	}


	// GETTERS, SETTERS
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


	public List<Species> getSpeciesList() {
		return speciesList;
	}


	public void setSpeciesList(List<Species> speciesList) {
		this.speciesList = speciesList;
	}


	public List<Breed> getBreedList() {
		return breedList;
	}


	public void setBreedList(List<Breed> breedList) {
		this.breedList = breedList;
	}


	public List<Trait> getTraitList() {
		return traitList;
	}


	public void setTraitList(List<Trait> traitList) {
		this.traitList = traitList;
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
		Foster other = (Foster) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Foster [id=" + id + ", maxFoster=" + maxFoster + ", fosterPets=" + fosterPets + ", user=" + user
				+ ", fosterReputations=" + fosterReputations + "]";
	}
	
	
}