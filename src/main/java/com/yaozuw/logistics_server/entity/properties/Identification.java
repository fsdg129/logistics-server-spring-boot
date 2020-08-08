package com.yaozuw.logistics_server.entity.properties;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "identification_type", 
	discriminatorType = DiscriminatorType.STRING, length = 10)
public abstract class Identification {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
	private Long id;
    
    @Embedded
	private ContactInformation contactInformation;
    
	@OneToOne(mappedBy = "identification")
	//@JoinColumn(name = "user_id", unique=true, nullable=false)
	private User user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		if( isValidID(id) ) {
			this.id = id;
		} else {
			throw new IllegalArgumentException("Invalid customerId.");
		}
		
	}
	public boolean isValidID(Long id) {	
		//FIX ME	
		return true;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		if(user != null && user.getIdentification() != this) {
				user.setIdentification(this);
		}
	}
	
	@Override
	public String toString() {
		
		return this.getId() + ";" + this.getContactInformation().toString();
	}
}


