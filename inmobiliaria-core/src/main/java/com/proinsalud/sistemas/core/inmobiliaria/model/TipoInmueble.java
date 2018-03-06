package com.proinsalud.sistemas.core.inmobiliaria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="type_inmueble", schema = "inmobiliaria")
public class TipoInmueble implements Serializable {

	private static final long serialVersionUID = 6792395338439617270L;
	
	@Id
	@Column(name = "id_type_inmueble")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_type_inmueble")
	private String nameTypeInmueble;
	
	
	public TipoInmueble() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameTypeInmueble() {
		return nameTypeInmueble;
	}

	public void setNameTypeInmueble(String nameTypeInmueble) {
		this.nameTypeInmueble = nameTypeInmueble;
	}

	@Override
	public String toString() {
		return "TipoInmueble [id=" + id + ", nameTypeInmueble=" + nameTypeInmueble + "]";
	}

	
}
