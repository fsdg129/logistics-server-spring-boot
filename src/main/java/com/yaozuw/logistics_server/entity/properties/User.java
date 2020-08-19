package com.yaozuw.logistics_server.entity.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	
	//The index of the user stored in the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
	private Long id;
    
    @Column(name = "username", nullable = false, length=15, unique = true)
	private String username;
    
    @Column(name = "password", nullable = false, length=70)
	private String password;
    
	//Determine what this user could see or change
	@Enumerated(EnumType.STRING)
	@Column(name = "priviledge", nullable = false, length=15)
	private Priviledge priviledge;
	
	//Determine which group the user belongs to
	//private IdentificationType identificationType;
	
	//The link to the detail information of the user
	@OneToOne
	@JoinColumn(name="identification_id")
	private Identification identification;
	
	public static boolean boolValidUsername(String username) {
		//FIX ME
		return true;
	}
	
	public User() {
		super();
	}
	public User(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public Priviledge getPriviledge() {
		return priviledge;
	}
	public void setPriviledge(Priviledge priviledge) {
		this.priviledge = priviledge;
	}
	
	public Identification getIdentification() {
		return identification;
	}
	public void setIdentification(Identification identification) {
		this.identification = identification;
		if(identification != null && identification.getUser() != this) {
			identification.setUser(this);
		}
	}
	
	@Override
	public String toString() {
		
		return this.getId() + ";" +
				this.getUsername() + ";" +
				this.getUsername() + ";" +
				this.getPriviledge().toString();
	}
	

}

