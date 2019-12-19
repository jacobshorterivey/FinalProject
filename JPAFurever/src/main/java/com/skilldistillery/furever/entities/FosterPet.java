package com.skilldistillery.furever.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "foster_pet")
public class FosterPet {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "foster_id")
	private Foster foster;

	@OneToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	@Column
	private String notes;
	private boolean active;

	@Column(name = "date_requested")
	private Date dateRequested;

	@Column(name = "date_completed")
	private Date dateCompleted;

	// CONSTRUCTORS
	public FosterPet(int id, Foster foster, Pet pet, String notes, boolean active, Date dateRequested,
			Date dateCompleted) {
		super();
		this.id = id;
		this.foster = foster;
		this.pet = pet;
		this.notes = notes;
		this.active = active;
		this.dateRequested = dateRequested;
		this.dateCompleted = dateCompleted;
	}

	public FosterPet() {
		super();
	}

	// GETTERS, SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Foster getFoster() {
		return foster;
	}

	public void setFoster(Foster foster) {
		this.foster = foster;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
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
		FosterPet other = (FosterPet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FosterPet [id=").append(id).append(", foster=").append(foster).append(", pet=").append(pet)
				.append(", notes=").append(notes).append(", active=").append(active).append(", dateRequested=")
				.append(dateRequested).append(", dateCompleted=").append(dateCompleted).append("]");
		return builder.toString();
	}
}
