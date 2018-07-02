package com.jogodavelha.model;

import java.io.Serializable;

public class NewAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Player player;
	private boolean sucessul;
	private boolean conflitUsername;
	private boolean conflitEmail;
	
	
	public NewAccount() {
		super();
		this.sucessul = false;
		this.player = null;
		this.conflitUsername = false;
		this.conflitEmail = false;
	}
	


	@Override
	public String toString() {
		return "NewAccount [player=" + player + ", sucessul=" + sucessul + ", conflitUsername=" + conflitUsername
				+ ", conflitEmail=" + conflitEmail + "]";
	}



	public NewAccount(Player player, boolean sucessul, boolean conflitUsername, boolean conflitEmail) {
		super();
		this.player = player;
		this.sucessul = sucessul;
		this.conflitUsername = conflitUsername;
		this.conflitEmail = conflitEmail;
	}



	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public boolean isSucessul() {
		return sucessul;
	}


	public void setSucessul(boolean sucessul) {
		this.sucessul = sucessul;
	}


	public boolean isConflitUsername() {
		return conflitUsername;
	}


	public void setConflitUsername(boolean conflitUsername) {
		this.conflitUsername = conflitUsername;
	}


	public boolean isConflitEmail() {
		return conflitEmail;
	}


	public void setConflitEmail(boolean conflitEmail) {
		this.conflitEmail = conflitEmail;
	}
	

}
