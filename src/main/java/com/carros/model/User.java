package com.carros.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuId;
	private String usuNome;
	private String usuLogin;
	private String usuPassword;
	private String usuEmail;
	
	
	public User(){
		
	}
	
	// GATTERS AND SETTERS
	public String getUsuPassword() {
		return usuPassword;
	}
	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}
	public Integer getUsuId() {
		return usuId;
	}
	public void setUsuId(Integer usuId) {
		this.usuId = usuId;
	}
	public String getUsuLogin() {
		return usuLogin;
	}
	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}
	public String getUsuEmail() {
		return usuEmail;
	}
	public void setUsuEmail(String usuEmail) {
		this.usuEmail = usuEmail;
	}

	public String getUsuNome() {
		return usuNome;
	}

	public void setUsuNome(String usuNome) {
		this.usuNome = usuNome;
	}
	
	
	
	
	
}
