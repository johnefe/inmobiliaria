package com.proinsalud.sistemas.core.inmobiliaria.model;

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

@Entity
@Table(name = "calification", schema = "inmobiliaria")
public class Calification implements Serializable {

	private static final long serialVersionUID = -1182833744751183412L;

	@Id
	@Column(name = "id_calification")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person")
	private Persons person;


	@Column(name = "number")
	private Integer number;

	public Calification(Persons person, int number) {
		this.person = person;
		this.number = number;
	}
	
	public Calification() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Calification [id=" + id + ", number=" + number + "]";
	}

}
