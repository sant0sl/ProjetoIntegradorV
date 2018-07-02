package br.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.model.Player;
import br.service.PlayerService;

@ManagedBean(name = "playerMB")
@SessionScoped
public class PlayerMB implements Serializable{

	//Serial para localizar o objeto na memória
	private static final long serialVersionUID = 2578162390345622255L;
	
	//Criando variáveis do tipo das Classes necessárias
	private Player player;
	private Player playerLogado;
	private PlayerService service;
	private List<Player> listPlayers;
	private String path;
	private String contentType;
	
	//Construtor para novas instâncias das Classes
	@PostConstruct
	public void inicializar() {
		firstAction();
	}
	//Instancia novos objetos
	public void firstAction() {
		this.player = new Player();
		this.playerLogado = new Player();
		this.service = new PlayerService();
		this.listPlayers = this.service.listPlayers();
	}
	//Limpa os valores do Player
	public void clearPlayer() {
		this.player = new Player();
	}
	//Limpa os registros da Lista
	public void clearList() {
		this.listPlayers = new ArrayList<Player>();
	}
	//Faz logout e limpa os objetos
	public void logoutAction() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		this.playerLogado = new Player();
		redirectLogin();
	}
	//Verifica o email e senha para fazer o login
	public void checkLogin() {
		this.playerLogado = this.service.checkLogin(this.player);
		System.out.println("Player has been loaded on the object");
		if(this.playerLogado != null) {
			listPlayers();
			redirectHome();
		}else {
			redirectLogin();
		}
	}
	//Registra novos Players
	public void insertPlayer() {
		Boolean b = this.service.conflictUsername(this.player.getUsername());
		Boolean e = this.service.conflictEmail(this.player.getEmail());
		if((b==false)&&(e==false)) {
			this.service.insertPlayer(this.player);
			System.out.println("Player has been registered");
			redirectLogin();
		}else {
			redirectRegister();
		}
	}
	//Altera as informações do PlayerLogado
	public void updatePlayer() {
		if(this.playerLogado.getId()!=null) {
			if((this.playerLogado.getPassword().equals(null))||(this.playerLogado.getPassword()=="")) {
				Boolean e = this.service.conflictEmail(this.player.getEmail());
				if(e==false) {
					Player p = this.service.obterPlayer(this.playerLogado.getId());
					this.playerLogado.setPassword(p.getPassword());
					this.service.updatePlayer(this.playerLogado);
					System.out.println("Data from this player has been updated with successful");
					redirectHome();
				}else {
					redirectPerfil();
				}
			}else if((this.playerLogado.getEmail().equals(null))||(this.playerLogado.getEmail()=="")) {
				Boolean e = this.service.conflictEmail(this.player.getEmail());
				if(e==false) {
					Player p = this.service.obterPlayer(this.playerLogado.getId());
					this.playerLogado.setEmail(p.getEmail());
					this.service.updatePlayer(this.playerLogado);
					System.out.println("Data from this player has been updated with successful");
					redirectHome();
				}else {
					redirectPerfil();
				}
			}else if(((this.playerLogado.getEmail().equals(null))||(this.playerLogado.getEmail()==""))&&((this.playerLogado.getPassword().equals(null))||(this.playerLogado.getPassword()==""))) {
				Boolean e = this.service.conflictEmail(this.player.getEmail());
				if(e==false) {
					Player p = this.service.obterPlayer(this.playerLogado.getId());
					this.playerLogado.setEmail(p.getEmail());
					this.playerLogado.setPassword(p.getPassword());
					this.service.updatePlayer(this.playerLogado);
					System.out.println("Data from this player has been updated with successful");
					redirectHome();
				}else {
					redirectPerfil();
				}
			}else {
				Boolean e = this.service.conflictEmail(this.player.getEmail());
				if(e==false) {
					this.service.updatePlayer(this.playerLogado);
					System.out.println("Data from this player has been updated with successful");
					redirectHome();
				}else {
					redirectPerfil();
				}
			}
		}else {
			redirectLogin();
		}
	}
	//Recupera a senha através do email
	public void passwordRecovery() {
		Boolean b = this.service.passwordRecovery(this.player);
		if(b==true) {
			System.out.println("The password was sent on respective email");
			redirectLogin();			
		}else {
			redirectRecoveryPassword();
		}
	}
	//Coloca os registros de players na Lista
	public void listPlayers() {
		clearList();
		this.listPlayers = this.service.listPlayers();
		System.out.println("Data from players are ready on list");
	}
	//Coloca os registros de players na Lista em ordem de maior pontuação de vitórias
	public void rankingPlayers() {
		clearList();
		this.listPlayers = this.service.rankingPlayers();
		System.out.println("Data from players are ready on list order by points");
	}
	//Obtém o player através do id
	public void obterPlayer() {
		this.player = service.obterPlayer(this.player.getId());
		System.out.println("Data from this players is ready on the object");
	}
	//Obtém as informações do player logado
	public void obterPlayerLogado() {
		this.playerLogado = this.service.obterPlayer(this.playerLogado.getId());
		System.out.println("Data from this players is ready on the object");
	}
	//Contabiliza 1 ponto para o Player que vencer
	public void win() {
		this.service.win(this.player.getId());
		System.out.println("Point has been added on this player account");
		clearPlayer();
	}
	//Método de preparação para baixar .zip do apk
	public void downloadAction() {
		path = "C:/Users/Administrador/git/ProjetoIntegradorV/ProjetoIntegradorV/apk/jogodavelha.zip";
		contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
	}
	//Redireciona para homepage, limpando o objeto Player
	public void redirectHome() {
		try {
			if(this.playerLogado.getId()!=null) {
				clearPlayer();
				obterPlayerLogado();
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			}else {
				redirectLogin();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Redireciona para página de login, limpando o objeto Player e os registros na lista
	public void redirectLogin() {
		try {
			clearPlayer();
			clearList();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Redireciona para página das informações do PlayerLogado, limpando o objeto Player
	public void redirectPerfil() {
		try {
			if(this.playerLogado.getId()!=null) {
				clearPlayer();
				obterPlayerLogado();
				FacesContext.getCurrentInstance().getExternalContext().redirect("infoPlayer.xhtml");
			}else {
				redirectLogin();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Redireciona para página de registro, limpando o objeto Player
	public void redirectRegister() {
		try {
			clearPlayer();
			FacesContext.getCurrentInstance().getExternalContext().redirect("insertPlayer.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Redireciona para a página com a tabela de ranking, limpando o objeto Player e adicionando na lista os registros em ordem rankeada
	public void redirectRanking() {
		try {
			if(this.playerLogado.getId()!=null) {
				clearPlayer();
				rankingPlayers();
				FacesContext.getCurrentInstance().getExternalContext().redirect("rankingPlayer.xhtml");
			}else {
				redirectLogin();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Redireciona para a página de recuperação de senha
	public void redirectRecoveryPassword() {
		try {
			clearPlayer();
			FacesContext.getCurrentInstance().getExternalContext().redirect("recoveryPassword.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//-------------------------------------------
	
	//Getters and Setters
	
	public StreamedContent getFile() throws IOException{
		downloadAction();
		return new DefaultStreamedContent(new FileInputStream(path), "application/zip", "jogodavelha.zip");
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayerLogado() {
		return playerLogado;
	}

	public void setPlayerLogado(Player playerLogado) {
		this.playerLogado = playerLogado;
	}

	public PlayerService getService() {
		return service;
	}

	public void setService(PlayerService service) {
		this.service = service;
	}

	public List<Player> getListPlayers() {
		return listPlayers;
	}

	public void setListPlayers(List<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}
}
