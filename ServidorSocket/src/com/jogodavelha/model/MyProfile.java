package com.jogodavelha.model;

import java.io.Serializable;

public class MyProfile implements Serializable {


	/**
    *
    */
   private static final long serialVersionUID = 1L;


   private String email;
   private String password;
   private Player player;
   private boolean findSucessul;

   public MyProfile(){
       this.email = "";
       this.password ="";
       this.player = null;
       this.setFindSucessul(false);
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

   public Player getPlayer() {
       return player;
   }

   public void setPlayer(Player player) {
       this.player = player;
   }

   public boolean isFindSucessul() {
       return findSucessul;
   }

   public void setFindSucessul(boolean findSucessul) {
       this.findSucessul = findSucessul;
   }
}