package com.skilldistillery.furever.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pet_adoption")
public class PetAdoption {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date_requested")
	private LocalDateTime dateRequested;

	@Column(name = "accepted_date")
	private LocalDateTime acceptedDate;

	@Column(name = "meet_date")
	private LocalDateTime meetDate;

	@Column(name = "meet_req_date")
	private LocalDateTime meetReqDate;

	@Column(name = "meet_notes")
	private String meetNotes;

	private boolean accepted;

	@Column(name = "reason_denied")
	private String reasonDenied;

//	private User user;

//	private Pet pet;

	// CONSTRUCTORS
	public PetAdoption(int id, LocalDateTime dateRequested, LocalDateTime acceptedDate, LocalDateTime meetDate,
			LocalDateTime meetReqDate, String meetNotes, boolean accepted, String reasonDenied) {
		super();
		this.id = id;
		this.dateRequested = dateRequested;
		this.acceptedDate = acceptedDate;
		this.meetDate = meetDate;
		this.meetReqDate = meetReqDate;
		this.meetNotes = meetNotes;
		this.accepted = accepted;
		this.reasonDenied = reasonDenied;
	}

	public PetAdoption() {
		super();
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(LocalDateTime dateRequested) {
		this.dateRequested = dateRequested;
	}

	public LocalDateTime getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(LocalDateTime acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public LocalDateTime getMeetDate() {
		return meetDate;
	}

	public void setMeetDate(LocalDateTime meetDate) {
		this.meetDate = meetDate;
	}

	public LocalDateTime getMeetReqDate() {
		return meetReqDate;
	}

	public void setMeetReqDate(LocalDateTime meetReqDate) {
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

	// HASH CODE AND EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accepted ? 1231 : 1237);
		result = prime * result + ((acceptedDate == null) ? 0 : acceptedDate.hashCode());
		result = prime * result + ((dateRequested == null) ? 0 : dateRequested.hashCode());
		result = prime * result + id;
		result = prime * result + ((meetDate == null) ? 0 : meetDate.hashCode());
		result = prime * result + ((meetNotes == null) ? 0 : meetNotes.hashCode());
		result = prime * result + ((meetReqDate == null) ? 0 : meetReqDate.hashCode());
		result = prime * result + ((reasonDenied == null) ? 0 : reasonDenied.hashCode());
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
		if (accepted != other.accepted)
			return false;
		if (acceptedDate == null) {
			if (other.acceptedDate != null)
				return false;
		} else if (!acceptedDate.equals(other.acceptedDate))
			return false;
		if (dateRequested == null) {
			if (other.dateRequested != null)
				return false;
		} else if (!dateRequested.equals(other.dateRequested))
			return false;
		if (id != other.id)
			return false;
		if (meetDate == null) {
			if (other.meetDate != null)
				return false;
		} else if (!meetDate.equals(other.meetDate))
			return false;
		if (meetNotes == null) {
			if (other.meetNotes != null)
				return false;
		} else if (!meetNotes.equals(other.meetNotes))
			return false;
		if (meetReqDate == null) {
			if (other.meetReqDate != null)
				return false;
		} else if (!meetReqDate.equals(other.meetReqDate))
			return false;
		if (reasonDenied == null) {
			if (other.reasonDenied != null)
				return false;
		} else if (!reasonDenied.equals(other.reasonDenied))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PetAdoption [id=").append(id).append(", dateRequested=").append(dateRequested)
				.append(", acceptedDate=").append(acceptedDate).append(", meetDate=").append(meetDate)
				.append(", meetReqDate=").append(meetReqDate).append(", meetNotes=").append(meetNotes)
				.append(", accepted=").append(accepted).append(", reasonDenied=").append(reasonDenied).append("]");
		return builder.toString();
	}

}
