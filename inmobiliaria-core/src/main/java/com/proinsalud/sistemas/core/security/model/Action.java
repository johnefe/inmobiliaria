package com.proinsalud.sistemas.core.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:03:19 a. m.
 *
 */
@Entity
@Table(name = "action", schema = "security")
public class Action implements Serializable {

	private static final long serialVersionUID = -6621414124615781559L;

	@Id
	@Column(name = "id_action")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "action")
	private String action;

	public Action() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", action=" + action + "]";
	}

}
