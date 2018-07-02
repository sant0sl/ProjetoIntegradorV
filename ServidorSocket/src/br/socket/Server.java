package br.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.jogodavelha.model.Login;
import com.jogodavelha.model.MyProfile;
import com.jogodavelha.model.NewAccount;
import com.jogodavelha.model.Player;
import com.jogodavelha.model.PlayerRanking;
import com.jogodavelha.model.RecoverPassword;
import com.jogodavelha.model.Score;

import br.dao.PlayerDao;

public class Server extends Thread {
	
	private Socket socket;
	
	private InputStream inputStream;  
	private ObjectInputStream objectInputStream;
	
	private OutputStream outputStream;
	private ObjectOutputStream objectOutputStream;
	
	public Server(Socket socket) {
		this.socket = socket;
		try {
	         inputStream = socket.getInputStream();
	         objectInputStream = new ObjectInputStream(inputStream);
	        
	         outputStream =  this.socket.getOutputStream();
	         objectOutputStream = new ObjectOutputStream(outputStream);
	   } catch (IOException e) {
		   System.out.println("Construtor: "+ e.getMessage());
	   }
	}
	
	public void run() {
		try{
			while(true){
				System.out.println("entrou");
				Object object = objectInputStream.readObject();
				if(object instanceof Login) {
					Login l = (Login) object;
					Player p = new PlayerDao().loginPlayer(l.getEmail(), l.getPassword());
					
					if(p == null) {
						l.setSucessul(false);
						l.setPlayer(null);
					}else {
						l.setSucessul(true);
						l.setPlayer(p);
					}
					objectOutputStream.writeObject(l);
					objectOutputStream.flush();
					break;
				}else if(object instanceof NewAccount) {
					NewAccount newAccount = (NewAccount) object;
					newAccount.setConflitEmail(new PlayerDao().conflictEmail(newAccount.getPlayer().getEmail()));
					newAccount.setConflitUsername(new PlayerDao().conflictUsername(newAccount.getPlayer().getUsername()));
					
					if(newAccount.isConflitEmail() == false && newAccount.isConflitUsername() == false) {
						new PlayerDao().insert(newAccount.getPlayer());
						newAccount.setSucessul(true);
					}else {
						newAccount.setSucessul(false);
					}
					objectOutputStream.writeObject(newAccount);
					objectOutputStream.flush();
					break;
				}else if(object instanceof RecoverPassword) {
					RecoverPassword recoverPassword = (RecoverPassword) object;
					new PlayerDao().passwordRecovery(recoverPassword.getEmail(),recoverPassword.getUsername());
					objectOutputStream.writeObject(recoverPassword);
					objectOutputStream.flush();
					break;
				}else if(object instanceof MyProfile) {
					MyProfile myProfile = (MyProfile) object;
					Player p = new PlayerDao().loginPlayer(myProfile.getEmail(), myProfile.getPassword());
					if(p == null) {
						myProfile.setFindSucessul(false);
						myProfile.setPlayer(null);
					}else {
						myProfile.setFindSucessul(true);
						myProfile.setPlayer(p);
					}
					objectOutputStream.writeObject(myProfile);
					objectOutputStream.flush();				
					break;
				}
				else if(object instanceof PlayerRanking) {
					PlayerRanking playerRanking = (PlayerRanking) object;
					playerRanking.setPlayerList(new PlayerDao().rankingPlayers());
					
					if(playerRanking.getPlayerList().isEmpty()) {
						playerRanking.setRankSucesul(false);
					}else {
						playerRanking.setRankSucesul(true);
					}
					objectOutputStream.writeObject(playerRanking);
					objectOutputStream.flush();	
					break;
				}else if(object instanceof Score) {
					Score score = (Score) object;
					System.out.println("chegou score");
					Player p = new PlayerDao().loginPlayer(score.email, score.password);
					
					if(p == null) {
						score.scoreSuceful = false;
					}else {
						p = new PlayerDao().win(p.getId());
						score.scoreSuceful = true;
					}
					System.out.println("enviou score");
					objectOutputStream.writeObject(score);
					objectOutputStream.flush();	
					break;
				}
			}
			close();
		}catch (Exception  e) {
			System.out.println("Metodo run: "+ e.getMessage());
		}
	}

	private void close() {
		try {
			System.out.println("Conexão encerrada.");
	
			objectInputStream.close();
			inputStream.close();
			
			objectOutputStream.close();
			outputStream.close();
			
			socket.close();
		} catch (IOException e) {
			System.out.println("Metodo close: "+ e.getMessage());
		}
	}
}
