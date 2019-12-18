package com.skilldistillery.furever.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "fost_reputation")
public class FosterReputation {
	
	//FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column
	private int id;
	private String content;
	private Integer rating;
	
	@ManyToOne
	@JoinColumn(name="foster_id")
	private Foster foster;
	
	//CONSTRUCTORS
	public FosterReputation() {
		super();
	}

	public FosterReputation(int id, String content, Integer rating, Foster foster) {
		super();
		this.id = id;
		this.content = content;
		this.rating = rating;
		this.foster = foster;
	}

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

	@Override
	public String toString() {
		return "FosterReputation [id=" + id + ", content=" + content + ", rating=" + rating + ", foster=" + foster
				+ "]";
	}
}
