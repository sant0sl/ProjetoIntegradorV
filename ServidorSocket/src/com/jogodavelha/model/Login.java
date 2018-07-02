package com.jogodavelha.model;

import java.io.Serializable;

public class Login implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private boolean sucessul;
	private Player player;
	
	public Login() {
		super();
		this.email = "";
		this.password = "";
		this.sucessul = false;
		this.player = null;
	}

	public Login(String email, String password, boolean sucessul, Player player) {
		super();
		this.email = email;
		this.password = password;
		this.sucessul = sucessul;
		this.player = player;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSucessul() {
		return sucessul;
	}

	public void setSucessul(boolean sucessul) {
		this.sucessul = sucessul;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + ", sucessul=" + sucessul + ", player=" + player
				+ "]";
	}

}
