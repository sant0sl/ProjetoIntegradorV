package br.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.jogodavelha.model.Player;

import br.util.ConnectionDB;
import br.util.EmailUtil;

public class PlayerDao {
	
private EntityManager em;
	
	public PlayerDao() {
		em = (new ConnectionDB().getConnection());
	}	
	
	// Registra um novo player (FUNCIONANDO)
	public void insert(Player p) {
		p.setId(null);
		p.setTotalWins(0);
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		
		String destinatario = p.getEmail();
		String assunto = "Bem-Vindo ao Jogo da Velha - P.I-V";
		String texto = "Seja muito bem-vindo ao Jogo da Velha do evento da MAX5!\nVocê foi cadastrado e agora você possui acesso ao nosso jogo. Aqui estão as suas informações de acesso:\n\nNome de usuário:"+p.getUsername()+"\nE-mail:"+p.getEmail()+"\nSenha:"+p.getPassword()+"\n\nDivirta-se!";
		
		EmailUtil.sendEmail(destinatario, assunto, texto);
	}
	
	// Altera as informações (FUNCIONANDO)
	public void update(Player p) {
		Player pp = em.find(Player.class, p.getId());
		p.setTotalWins(pp.getTotalWins());
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();
	}
	
	// Obter Player (FUNCIONANDO)
	public Player obter(Integer id) {
		Player p = em.find(Player.class, id);
		return p;
	}
	
	// Busca players registrados (FUNCIONANDO)
	public List<Player> listPlayers(){
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Player> criteria = builder.createQuery(Player.class);
	    criteria.from(Player.class);
	    List<Player> list = em.createQuery(criteria).getResultList();
	    em.close();
	    return list;
	}
	
	// Busca a lista de players registrados em ordem decrescente do total de pontos(FUNCIONANDO)
	@SuppressWarnings("unchecked")
	public List<Player> rankingPlayers(){
		String hql = "from Player e order by e.totalWins desc";
		Query query = em.createQuery(hql);
		List<Player> list = (List<Player>) query.getResultList();
		return list;
	}
	
	// Verifica login do usuário (FUNCIONANDO)
	public Player loginPlayer(String email, String password) {
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.email = ?1 AND p.password = ?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
        try {
	        Player p = (Player) query.getSingleResult();
	        return p;
        }catch(javax.persistence.NoResultException e) {
        	em.close();
        	e.printStackTrace();
        	return null;
        }
	}
	
	// Faz a recuperação de senha por email (FUNCIONANDO)
	public Boolean passwordRecovery(String email, String username) {
		String senhaAleatoria = EmailUtil.geradorSenhaAutomatico();
		String assunto = "Recuperação de senha - Jogo da Velha - P.I-V";
		String texto = "Sua senha foi alterada com sucesso!\n\nNova senha:"+" "+senhaAleatoria+"\n\nAgora você já pode acessar novamente o Jogo da Velha!";
        
        Query query = em.createQuery("SELECT p FROM Player p WHERE p.email = ?1 AND p.username = ?2");
		query.setParameter(1, email);
		query.setParameter(2, username);
        
        try {
	        Player p = (Player) query.getSingleResult();
	        
	        p.setPassword(senhaAleatoria);
        	
        	em.getTransaction().begin();
    		em.merge(p);
    		em.getTransaction().commit();
    		em.close();
    		
    		EmailUtil.sendEmail(email, assunto, texto);
    		System.out.println("Senha alterada!");
    		
    		return true;
        }catch(javax.persistence.NoResultException e) {
        	em.close();
        	e.printStackTrace();
        	return false;
        }
	}
	
	// true > ja esta em uso --- false > disponivel (FUNCIONANDO)
	public boolean conflictUsername(String username) {
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.username = ?1");
        query.setParameter(1, username);
        try {
	        Player p = (Player) query.getSingleResult();
			if(p.getUsername().equals(username)) {				
				return true;
			}else {
				return false;
			}
        }catch(javax.persistence.NoResultException e) {
        	e.printStackTrace();
        	return false;
        }
	}
	
	// true > ja esta em uso --- false > disponivel (FUNCIONANDO)
	public boolean conflictEmail(String email) {
		Query query = em.createQuery("SELECT p FROM Player p WHERE p.email = ?1");
	    query.setParameter(1, email);
	    try {
		    Player p = (Player) query.getSingleResult();
		    if(p.getEmail().equals(email)) {				
				return true;
			}else {
				return false;
			}
	    }catch(javax.persistence.NoResultException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	// Adiciona um ponto de vitória para o vencedor da partida (FUNCIONANDO)
	public Player win(Integer id) {
		Player p = new Player();
		p = em.find(Player.class, id);
		p.setTotalWins(p.getTotalWins()+1);
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();
		return p;
	}
}
