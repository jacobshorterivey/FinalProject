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
@Table(name="pet_adoption")
public class PetAdoption {

	// Fields
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column (name ="date_requested")
		private Date dateRequested;
		
		@Column (name ="accepted_date")
		private Date acceptedDate;
		
		@Column (name ="meet_date")
		private Date meetDate;
		
		@Column (name ="meet_req_date")
		private Date meetReqDate;
		
		@Column (name ="meet_notes")
		private String meetNotes;
		
		private boolean accepted;
		
		@Column (name ="reason_denied")
		private String reasonDenied;
		
		@ManyToOne
		@JoinColumn (name ="user_id")
		private User user;
		
		@ManyToOne
		@JoinColumn (name ="pet_id")
		private Pet pet;

		// Constructors
		public PetAdoption(int id, Date dateRequested, Date acceptedDate, Date meetDate, Date meetReqDate,
				String meetNotes, boolean accepted, String reasonDenied, User user, Pet pet) {
			super();
			this.id = id;
			this.dateRequested = dateRequested;
			this.acceptedDate = acceptedDate;
			this.meetDate = meetDate;
			this.meetReqDate = meetReqDate;
			this.meetNotes = meetNotes;
			this.accepted = accepted;
			this.reasonDenied = reasonDenied;
			this.user = user;
			this.pet = pet;
		}
		

		public PetAdoption() {
			super();
		}

		// Getters Setters
		
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
			result = prime * result + ((pet == null) ? 0 : pet.hashCode());
			result = prime * result + ((reasonDenied == null) ? 0 : reasonDenied.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
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
			if (pet == null) {
				if (other.pet != null)
					return false;
			} else if (!pet.equals(other.pet))
				return false;
			if (reasonDenied == null) {
				if (other.reasonDenied != null)
					return false;
			} else if (!reasonDenied.equals(other.reasonDenied))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "PetAdoption [id=" + id + ", dateRequested=" + dateRequested + ", acceptedDate=" + acceptedDate
					+ ", meetDate=" + meetDate + ", meetReqDate=" + meetReqDate + ", meetNotes=" + meetNotes
					+ ", accepted=" + accepted + ", reasonDenied=" + reasonDenied + ", user=" + user + ", pet=" + pet
					+ "]";
		}
		
		
		
}
