package br.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionDB {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoIntegradorV");
	
	public EntityManager getConnection() {
		return emf.createEntityManager();
	}
}
