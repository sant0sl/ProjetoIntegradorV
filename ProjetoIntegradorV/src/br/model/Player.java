package br.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	private Integer id;
	
	private String name;
	private Integer totalWins;
	private String username;
	private String password;
	private String email;

	//Construtor vazio
	public Player() {
		
	}
	
	//Construtor para inserir novos Players
	/*(Não há necessidade de id por parâmetro, pois a função AUTO INCREMENT 
	*do banco de dados seta automaticamente o id do próximo registro)*/
	public Player(String name, String username, String password, String email, Integer totalWins) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.totalWins = totalWins;
	}
	
	//Construtor para alteração de dados.
	public Player(Integer id, String name, String username, String password, String email, Integer totalWins) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.totalWins = totalWins;
	}
	
	//Método toString
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", totalWins=" + totalWins + ", username=" + username
				+ ", password=" + password + ", email=" + email + "]\n";
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getTotalWins() {
		return totalWins;
	}
	public void setTotalWins(Integer totalWins) {
		this.totalWins = totalWins;
	}
}
