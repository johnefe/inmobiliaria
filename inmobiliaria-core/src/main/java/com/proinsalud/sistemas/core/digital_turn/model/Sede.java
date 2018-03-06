package com.proinsalud.sistemas.core.digital_turn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mauricio Pinchao
 * @datetime 6/02/2018 - 7:45:32 a. m.
 *
 */
@Entity
@Table(name = "sede", schema = "digital_turn")
public class Sede implements Serializable {

	private static final long serialVersionUID = 5271257999667146917L;
	
	@Id
	@Column(name = "id_sede")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_sede")
	private String nameSede;
	
	public Sede() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNameSede() {
		return nameSede;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNameSede(String nameSede) {
		this.nameSede = nameSede;
	}

	@Override
	public String toString() {
		return "Sede [id=" + id + ", nameSede=" + nameSede + "]";
	}
}
