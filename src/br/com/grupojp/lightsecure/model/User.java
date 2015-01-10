package br.com.grupojp.lightsecure.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="user")
@NamedQueries ({
	@NamedQuery(name="User.findUserByLogin", query="select u from User u where u.login=:login")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	public static final String FIND_LOGIN = "User.findUserByLogin";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@XmlElement(name="id")
	private long id;
	
	@Column(unique=true)
	@XmlElement(name="login")
	private String login;
	
	@XmlElement(name="password")
	private String password;
	
	@XmlElement(name="active")
	private boolean active;
	
	//Criando a tabela usuario permissão 
	@ElementCollection(targetClass=String.class, fetch=FetchType.EAGER)
	@JoinTable(
			name="user_roles",
			uniqueConstraints={@UniqueConstraint(columnNames = {"user_id","roles"})},
			joinColumns=@JoinColumn(name="user_id"))
	@Column(name="roles",length=50, insertable=true, updatable=true)
	@XmlElement(name="roles")
	private Set<String> roles = new HashSet<String>();

	
	public User() {
		
	}
	
	/***
	 * 
	 * GETTER & SETTER
	 * 
	 * ***/
	
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}



	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	
	
	
	
	
	
}
