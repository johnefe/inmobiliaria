package com.proinsalud.sistemas.core.inmobiliaria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "inmobiliaria")
public class Userr implements Serializable {

	private static final long serialVersionUID = 7249456655922975704L;

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "nameUser")
	private String nameUser;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;

	public Userr() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Userr [id=" + id + ", email=" + email + ", nameUser=" + nameUser + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}

}
