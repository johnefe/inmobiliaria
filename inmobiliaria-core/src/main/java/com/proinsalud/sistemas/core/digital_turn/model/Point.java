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
import javax.persistence.Table;

/**
 * @author Mauricio Pinchao
 * @datetime 6/02/2018 - 7:42:46 a. m.
 *
 */
@Entity
@Table(name = "point", schema = "digital_turn")
public class Point implements Serializable {

	private static final long serialVersionUID = -7193136635996286540L;
	
	@Id
	@Column(name = "id_point")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 100)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sede")
	private Sede sede;
	
	public Point() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Sede getSede() {
		return sede;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", name=" + name + ", sede=" + sede + "]";
	}
}
