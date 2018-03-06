package com.proinsalud.sistemas.core.warehouse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Es la tabla producto de medicina
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "medicine", schema = "warehouse")
public class Medicine implements Serializable {

	private static final long serialVersionUID = 4314896593718262278L;

	@Id
	@Column(name = "id_medicine")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	public Medicine() {
		super();
	}

	public Medicine(String name) {
		super();
		this.name = name;
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
		return "Medicine [id=" + id + ", name=" + name + "]";
	}

}
