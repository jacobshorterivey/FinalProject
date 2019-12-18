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
	public FosterPet() {}
	
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

	
	// GETTERS, SETTERS, TOSTRING, EQUALS
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

	public void setDate_completed(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	@Override
	public String toString() {
		return "FosterPet [id=" + id + ", foster=" + foster + ", pet=" + pet + ", notes=" + notes + ", active=" + active
				+ ", dateRequested=" + dateRequested + ", dateCompleted=" + dateCompleted + "]";
	}
}
