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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.proinsalud.sistemas.core.general.model.Municipality;


@Entity
@Table(name = "persons", schema = "inmobiliaria")
public class Persons implements Serializable {

	private static final long serialVersionUID = -2136177026929927298L;

	@Id
	@Column(name = "id_person")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Userr user;

	@Column(name = "identification")
	private String identification;

	@Column(name = "number_phone")
	private String numberPhone;

	@Column(name = "address")
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipality")
	private Municipality municipality;
	
	public Persons() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Userr getUser() {
		return user;
	}

	public void setUser(Userr user) {
		this.user = user;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Municipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	@Override
	public String toString() {
		return "Persons [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ",  identification=" + identification + ", numberPhone=" + numberPhone + ", address=" + address
				+ "]";
	}

}
