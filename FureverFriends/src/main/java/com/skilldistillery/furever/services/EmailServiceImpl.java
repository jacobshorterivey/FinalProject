package com.skilldistillery.furever.services;

public class EmailServiceImpl {

	// F I E L D S
	private String toAddress;
	private String subject;
	private String body;
	
	// G E T T E R S   A N D   S E T T E R S
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
