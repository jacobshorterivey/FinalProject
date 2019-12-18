package com.skilldistillery.furever.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FosterReputation {
	
	//FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="foster_id")
	private Integer fosterId;
	private String content;
	private Integer rating;
	
	//CONSTRUCTORS
	public FosterReputation() {
		super();
	}

	public FosterReputation(int id, Integer fosterId, String content, Integer rating) {
		super();
		this.id = id;
		this.fosterId = fosterId;
		this.content = content;
		this.rating = rating;
	}

	
	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getFosterId() {
		return fosterId;
	}

	public void setFosterId(Integer fosterId) {
		this.fosterId = fosterId;
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

	
	
	// HASH CODE AND EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((fosterId == null) ? 0 : fosterId.hashCode());
		result = prime * result + id;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (fosterId == null) {
			if (other.fosterId != null)
				return false;
		} else if (!fosterId.equals(other.fosterId))
			return false;
		if (id != other.id)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		return "FosterReputation [id=" + id + ", fosterId=" + fosterId + ", content=" + content + ", rating=" + rating
				+ "]";
	}
	
	
	
	
	
	
}
