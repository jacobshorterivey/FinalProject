package com.skilldistillery.furever.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fost_reputation")
public class FosterReputation {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	private Integer rating;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "foster_id")
	private Foster foster;

	// CONSTRUCTORS
	public FosterReputation(int id, String content, Integer rating, Foster foster) {
		super();
		this.id = id;
		this.content = content;
		this.rating = rating;
		this.foster = foster;
	}

	public FosterReputation() {
		super();
	}

	// GETTERS, SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Foster getFoster() {
		return foster;
	}

	public void setFoster(Foster foster) {
		this.foster = foster;
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
		FosterReputation other = (FosterReputation) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FosterReputation [id=" + id + ", content=" + content + ", rating=" + rating + ", foster=" + foster
				+ "]";
	}

	

}
