package com.proinsalud.sistemas.core.convocatory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Es la tabla tipo de convocatoria
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 9:59:49 a. m.
 *
 */
@Entity
@Table(name = "type_convocatory", schema = "convocatory")
public class TypeConvocatory implements Serializable {

	private static final long serialVersionUID = 3050764292366835436L;

	@Id
	@Column(name = "id_type_convocatory")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100, nullable = false, unique = true)
	private String name;

	@Column(name = "description", length = 300, nullable = true)
	private String description;

	public TypeConvocatory() {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TypeConvocatory [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
