package com.jogodavelha.model;

import java.io.Serializable;

public class Score implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String email;
    public String password;
    public boolean scoreSuceful;


    public Score(String email, String password){
        this.email = email;
        this.password = password;
        this.scoreSuceful = false;
    }

}
