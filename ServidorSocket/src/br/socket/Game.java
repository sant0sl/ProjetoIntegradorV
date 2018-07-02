package br.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.jogodavelha.model.Played;

public class Game {
	
	String[][] matrix = new String[3][3];
	public Integer[] connect = {0,0};
	public Jogador playerX;
	public Jogador playerO;

	
	public class Jogador extends Thread {
		public Socket socket;
		public ObjectInputStream entrada;
		public ObjectOutputStream saida;

		public int player;
		/*player = 1 "X"
		 * player = 2 "O"
		 */
		
		public Jogador(Socket socket, int player) {
			this.socket = socket;
			this.player = player;
			try {
				entrada = new ObjectInputStream(socket.getInputStream());
				saida = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				System.out.println("Oponente Morreu: " + e);
			}
		}
		
		public void run() {
			try {
				  while(true) { 
					  Object object = entrada.readObject();
					  if(object instanceof Played) {
						  Played played = (Played) object;
						  System.out.println("LOG: "+ played.toString());
						  if(played.isLeaveGame()) {
							  if(player == 1) {
								  playerO.saida.writeObject(0);
							  }else {
								  playerX.saida.writeObject(0);
							  }
							  System.out.println("jogador saiu");
							  break;
						  }else {
							  if(player == 1) {
								  playerO.saida.writeObject(played);
							  }
							  else {
								  playerX.saida.writeObject(played);
							  }
							  System.out.println("jogador jogou");
						  }
					  }else if(object instanceof Integer) {
						  Integer num = (Integer) object;
						  if(num == 0) {
							  if(player == 1) {
								  connect[0] = 0;
							  }else {
								  connect[1] = 0;
							  }
							  System.out.println("desconectado");
							  break;
						  }
					  }
				  }
				  socket.close();
			}catch (IOException e) {
				System.out.println("LOG IO:" +e);
			} catch (ClassNotFoundException e) {
				System.out.println("LOG Class: "+ e);
			}
		}
		
	}

	public void startGame() {
		try {
			playerX.saida.writeObject(1);
			playerO.saida.writeObject(2);
			System.out.println("Iniciou jogo");
		} catch (IOException e) {
			System.out.println("Erro :"+ e);
		}
	}
}

