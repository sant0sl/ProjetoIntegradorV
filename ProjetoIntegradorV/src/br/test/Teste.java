package br.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.dao.PlayerDao;
import br.model.Player;
import br.service.PlayerService;

public class Teste {

	@Test
	public void junit() {
		Player p = new Player();
		PlayerService pd = new PlayerService();
		PlayerDao dao = new PlayerDao();
		List<Player> players = new ArrayList<>();
		Boolean answer = null;
		
		/*p.setId(2);
		p.setPassword("bololohaha");
		
		pd.updatePlayer(p);*/
		
		/*p.setEmail("sant0sl@outlook.com");
		p.setPassword("-");
		
		Player pp = new Player();
		pp = pd.checkLogin(p);*/
		
		//pd.win(3);
		//p = pd.obterPlayer(3);
		
		//answer = pd.conflictEmail("sant0sl@outlook.com");
		
		//players = pd.listPlayers();
		//players = pd.rankingPlayers();
		//System.out.println(answer);
	}

}
