	package br.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import br.socket.Game;

public class StartSocketGame {

	private static ServerSocket server;
	
	public static void main(String[] args) {
		try {
			server = new ServerSocket(50500);
			
			System.out.println("Porta: " + String.valueOf(server.getLocalPort()) + 
					"\r\nEndereço: " + InetAddress.getLocalHost().getHostAddress());
			
			while(true){
				Game game = new Game();
				game.playerX = game.new Jogador(server.accept(), 1);
				game.connect[0] = 1;
				game.playerX.start();
				System.out.println(game.playerX.socket.getInetAddress().toString());
				
				while(true) {
					game.playerO = game.new Jogador(server.accept(), 2);
					game.connect[1] = 1;
					game.playerO.start();
					System.out.println(game.playerO.socket.getInetAddress().toString());
					if(game.connect[0] == 0) {
						System.out.println("Sem player 1");
						game.playerX = game.playerO;
						game.playerX.player = 1;
						game.connect[0] = 1;
						game.connect[1] = 0;
						game.playerO = null;
					}else {
						break;
					}
				}
				game.startGame();
			}
		}catch (IOException e) {
			System.out.println("Main: "+ e.getMessage());
		} 

	}

}
