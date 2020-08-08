package com.yaozuw.logistics_server.entity.properties;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInformation {

	@Column(name = "real_name", nullable = true, length=10)
	private String realName;
	@Column(name = "phone_number", nullable = true, length=11)
	private String phoneNumber;
	@Column(name = "email", nullable = true, length=20)
	private String email;
	
	public static boolean isValidPhoneNumber(String phoneNumber) {
	    return phoneNumber.matches("\\d{11}") ;
	}
	
	public ContactInformation() {
		super();
	}
	public ContactInformation(String realName, String phoneNumber, String email) {
		this();
		this.setRealName(realName);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public boolean boolEmptyRealName(){
		
		return this.realName == null ? true : false;
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		if( ContactInformation.isValidPhoneNumber(phoneNumber) ) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new IllegalArgumentException("The number of digits of this telephone is wrong.");
		}
	}
	public boolean boolEmptyPhoneNumber(){
		
		return this.phoneNumber == null ? true : false;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean boolEmptyEmail(){
		
		return this.email == null ? true : false;
	}
	
	@Override
	public String toString() {
		
		return this.getRealName() + ";" +
				this.getPhoneNumber() + ";" +
				this.getEmail();
	}
	
	
}
