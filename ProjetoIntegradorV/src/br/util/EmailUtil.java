package br.util;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	public static void sendEmail(String destinatario, String assunto, String texto) {
		Properties props = new Properties();
		props.put("mail.transport.protocol","smtp");
		props.put("mail.smtp.host","smtp.live.com");
		props.put("mail.smtp.socketFactory.port","587");
		props.put("mail.smtp.socketFactory.fallback","false");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.port","587");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("clarioganrbr@hotmail.com","outubro2009");
			}
		});
		session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("clarioganrbr@hotmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			message.setSubject(assunto);
			message.setText(texto);
			Transport.send(message);
			System.out.println("Email enviado!");
		}catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static String geradorSenhaAutomatico() {
		// adicionamos caracteres para gerar a senha
		String caracteres = "qwertyuioplkjhgfdsazxcvbnmMNBVCXZASDFGHJKLPOIUYTREWQ0123456789";
		// instancia random
		Random aleatorio = new Random();
		// cria a string novaSenha
		String novaSenha = "";
		for (int i = 0; i < 10; ++i) {
			// gera um numero aleatório de acordo com a quantidade maxima da
			// string caracteres
			int gerador = aleatorio.nextInt(caracteres.length());
			// concatena o caractere selecionado na nova senha
			novaSenha += caracteres.charAt(gerador);
		}
		// retorna a nova senha
		return novaSenha;
	}
	
}
