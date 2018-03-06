package com.proinsalud.sistemas.core.digital_turn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.proinsalud.sistemas.core.security.model.Users;

/**
 * @author Mauricio Pinchao
 * @datetime 6/02/2018 - 9:30:13 a. m.
 *
 */

@Entity
@Table(name = "module_user", schema = "digital_turn")
@NamedQueries({
	@NamedQuery(name = "ModuleUser.findEntityByUserId", query = "SELECT m FROM ModuleUser m WHERE m.user.id=:idUser"),
	@NamedQuery(name = "ModuleUser.findAllEntityByUserTypeModule", query = "SELECT m FROM ModuleUser m WHERE m.user.id=:idUser AND m.module.typeModule=:typeModule"),
	@NamedQuery(name = "ModuleUser.deleteEntityByIdModule", query = "DELETE FROM ModuleUser m WHERE m.module.id=:idModule AND m.user.id=:idUser")
})

public class ModuleUser implements Serializable {

	private static final long serialVersionUID = -7797872783931220279L;

	@Id
	@Column(name = "id_module_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_module")
	private Module module;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users user;
	
	public ModuleUser() {
		super();
	}

	public ModuleUser(Module module, Users user) {
		super();
		this.module = module;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ModuleUser [id=" + id + ", module=" + module + ", user=" + user + "]";
	}
}
