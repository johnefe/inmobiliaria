package com.proinsalud.sistemas.core.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Es la tabla de autoridades
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:02:52 a. m.
 *
 */
@Entity
@Table(name = "authority", schema = "security")
public class Authority implements Serializable {

	private static final long serialVersionUID = -4233205259898481437L;

	@Id
	@Column(name = "id_auth")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	public Authority() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", name=" + name + "]";
	}

}
