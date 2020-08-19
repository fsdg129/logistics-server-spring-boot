package com.yaozuw.logistics_server.entity.properties;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@DiscriminatorValue("STAFF")
public class Staff extends Identification{
	
	@Enumerated(EnumType.STRING)
	@Column(name = "position", length = 15)
	private Position position;
	
	@Column(name = "checked", nullable = false)
	@Type(type="yes_no")
	private Boolean boolChecked;
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Boolean isBoolChecked() {
		return boolChecked;
	}
	public void setBoolChecked(Boolean boolChecked) {
		this.boolChecked = boolChecked;
	}
	
	@Override
	public String toString() {
		
		return super.toString() + ";" + this.getPosition().toString();
	}

}

	
 
