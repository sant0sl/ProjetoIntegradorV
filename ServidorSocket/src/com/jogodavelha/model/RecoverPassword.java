package com.jogodavelha.model;

import java.io.Serializable;

public class RecoverPassword implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String email;
	private String username;

	public RecoverPassword() {
	}

	public RecoverPassword(String email, String username) {
		super();
		this.email = email;
		this.username = username;
	}

	@Override
	public String toString() {
		return "RecoverPassword [email=" + email + ", username=" + username + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
