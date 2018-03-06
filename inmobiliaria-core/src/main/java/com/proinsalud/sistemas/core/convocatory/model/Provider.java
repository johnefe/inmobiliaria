package com.proinsalud.sistemas.core.convocatory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.util.BaseEntity;

/**
 * Es la tabla proveedor
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "provider", schema = "convocatory")
@NamedQueries({ @NamedQuery(name = "Provider.findEntityByIdPerson", query = "SELECT u FROM Provider u WHERE u.person.id=:idPerson"), })
public class Provider extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -334827270461557339L;

	@Id
	@Column(name = "id_provider")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "phone", length = 50)
	private String phone;

	@Column(name = "address", length = 100)
	private String address;

	@Column(name = "email", length = 200)
	private String email;

	@Column(name = "nit", length = 100)
	private String nit;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
	private List<Quotation> cotizations;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person")
	@JsonManagedReference
	private Person person;

	public Provider() {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Quotation> getCotizations() {
		return cotizations;
	}

	public void setCotizations(List<Quotation> cotizations) {
		this.cotizations = cotizations;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", email=" + email + "]";
	}

}
