package com.proinsalud.sistemas.core.inmobiliaria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_bussines", schema = "inmobiliaria")
public class TypeBussines implements Serializable {

	private static final long serialVersionUID = 7271505951259040749L;

	@Id
	@Column(name = "id_type_bussines")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(name = "name_type_bussines")
	private String nameTypeBussines;

	public TypeBussines() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameTypeBussines() {
		return nameTypeBussines;
	}

	public void setNameTypeBussines(String nameTypeBussines) {
		this.nameTypeBussines = nameTypeBussines;
	}

	@Override
	public String toString() {
		return "TypeBussines [id=" + id + ", nameTypeBussines=" + nameTypeBussines + "]";
	}

}
