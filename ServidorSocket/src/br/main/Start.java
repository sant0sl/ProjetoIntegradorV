package br.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import br.socket.Server;

public class Start{
	
	private static ServerSocket server;

	public static void main(String[] args) {
		try {
			server = new ServerSocket(49500);
			
			System.out.println("Porta: " + String.valueOf(server.getLocalPort()) + 
					"\r\nEndereço: " + InetAddress.getLocalHost().getHostAddress());
			
			while(true){
				Socket socket = server.accept();
				System.out.println("Nova Conexão.");
				Thread t = new Server(socket);
				t.start();
			}
		}catch (IOException e) {
			System.out.println("Main: "+ e.getMessage());
		} 
	}
}
