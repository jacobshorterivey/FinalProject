package com.skilldistillery.furever.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pet_adoption")
public class PetAdoption {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean accepted;

	@Column(name = "date_requested")
	private Date dateRequested;

	@Column(name = "accepted_date")
	private Date acceptedDate;

	@Column(name = "meet_date")
	private Date meetDate;

	@Column(name = "meet_req_date")
	private Date meetReqDate;

	@Column(name = "meet_notes")
	private String meetNotes;

	@Column(name = "reason_denied")
	private String reasonDenied;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	// CONSTRUCTORS
	public PetAdoption(int id, boolean accepted, Date dateRequested, Date acceptedDate, Date meetDate, Date meetReqDate,
			String meetNotes, String reasonDenied, User user, Pet pet) {
		super();
		this.id = id;
		this.accepted = accepted;
		this.dateRequested = dateRequested;
		this.acceptedDate = acceptedDate;
		this.meetDate = meetDate;
		this.meetReqDate = meetReqDate;
		this.meetNotes = meetNotes;
		this.reasonDenied = reasonDenied;
		this.user = user;
		this.pet = pet;
	}
	

	public PetAdoption() {
		super();
	}

	// GETTERS, SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public Date getMeetDate() {
		return meetDate;
	}

	public void setMeetDate(Date meetDate) {
		this.meetDate = meetDate;
	}

	public Date getMeetReqDate() {
		return meetReqDate;
	}

	public void setMeetReqDate(Date meetReqDate) {
		this.meetReqDate = meetReqDate;
	}

	public String getMeetNotes() {
		return meetNotes;
	}

	public void setMeetNotes(String meetNotes) {
		this.meetNotes = meetNotes;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getReasonDenied() {
		return reasonDenied;
	}

	public void setReasonDenied(String reasonDenied) {
		this.reasonDenied = reasonDenied;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
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
		PetAdoption other = (PetAdoption) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PetAdoption [id=").append(id).append(", accepted=").append(accepted).append(", dateRequested=")
				.append(dateRequested).append(", acceptedDate=").append(acceptedDate).append(", meetDate=")
				.append(meetDate).append(", meetReqDate=").append(meetReqDate).append(", meetNotes=").append(meetNotes)
				.append(", reasonDenied=").append(reasonDenied).append(", user=").append(user).append(", pet=")
				.append(pet).append("]");
		return builder.toString();
	}

}
