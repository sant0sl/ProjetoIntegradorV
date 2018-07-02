package br.service;

import java.util.List;

import br.dao.PlayerDao;
import br.model.Player;

public class PlayerService {
	
	//OK
	public void insertPlayer(Player p) {
		if(p!=null) {
			new PlayerDao().insert(p);
		}
	}
	//OK
	public void updatePlayer(Player p) {
		new PlayerDao().update(p);
	}
	//OK
	public Player obterPlayer(Integer id) {
		return new PlayerDao().obter(id);
	}
	//OK
	public List<Player> listPlayers(){
		return new PlayerDao().listPlayers();
	}
	//OK
	public List<Player> rankingPlayers(){
		return new PlayerDao().rankingPlayers();
	}
	//OK
	public Player checkLogin(Player p) {
		return new PlayerDao().loginPlayer(p);
	}
	//OK
	public Boolean passwordRecovery(Player p) {
		return new PlayerDao().passwordRecovery(p);
	}
	//OK
	public Boolean conflictUsername(String username) {
		return new PlayerDao().conflictUsername(username);
	}
	//OK
	public Boolean conflictEmail(String email) {
		return new PlayerDao().conflictEmail(email);
	}
	//OK
	public Player win(Integer id) {
		Player p = new PlayerDao().win(id);
		return p;
	}
}
