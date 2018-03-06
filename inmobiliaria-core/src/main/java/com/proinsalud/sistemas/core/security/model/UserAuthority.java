package com.proinsalud.sistemas.core.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Es la tabla intermedia para las autoridades del usuario
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:01:23 a. m.
 *
 */
@Entity
@Table(name = "user_authority", schema = "security")
public class UserAuthority implements Serializable {

	private static final long serialVersionUID = -8865396533267018783L;

	@Id
	@Column(name = "id_user_auth")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_auth")
	private Authority authority;

	public UserAuthority() {
		super();
	}

	public UserAuthority(Users user, Authority authority) {
		super();
		this.user = user;
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "UserAuthority [id=" + id + ", user=" + user + ", authority=" + authority + "]";
	}

}
