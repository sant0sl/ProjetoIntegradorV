package br.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.model.Player;
import br.service.PlayerService;

@Path("/player")
public class PlayerRest {

	private PlayerService service;
	
	public PlayerRest() {
		service = new PlayerService();
	}
	
	//----------- Persistência ------------
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/post")
	public void insertPlayer(Player p) {
		service.insertPlayer(p);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/put")
	public void updatePlayer(Player p) {
		service.updatePlayer(p);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public List<Player> listPlayers(){
		return service.listPlayers();
	}
	//-------------------------------------
	
	//------- Métodos rest restantes ------
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Player checkLogin(Player p) {
		return service.checkLogin(p);
	}
	
	@GET
	@Path("/ranking")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> rankingPlayers(){
		return service.rankingPlayers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obterPlayer")
	public Player obterPlayer(Player p) {
		return service.obterPlayer(p.getId());
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/passwordRecovery")
	public void passwordRecovery(Player p) {
		service.passwordRecovery(p);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/win")
	public void win(Player p) {
		service.win(p.getId());
	}
	//-------------------------------------
}
