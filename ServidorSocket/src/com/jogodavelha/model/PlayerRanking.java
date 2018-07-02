package com.jogodavelha.model;

import java.io.Serializable;
import java.util.List;

	public class PlayerRanking implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private List<Player> playerList;
    private boolean rankSucesul;

    public PlayerRanking(){
        playerList = null;
        setRankSucesul(false);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public boolean isRankSucesul() {
        return rankSucesul;
    }

    public void setRankSucesul(boolean rankSucesul) {
        this.rankSucesul = rankSucesul;
    }
}
