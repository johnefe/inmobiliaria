package com.proinsalud.sistemas.core.general.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proinsalud.sistemas.core.util.json.CustomJson;

@Entity
@Table(name = "department", schema = "general")
@CustomJson
public class Department implements Serializable {

	private static final long serialVersionUID = -9146297450688699114L;

	@Id
	@Column(name = "id_department")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_country")
	@JsonManagedReference
	private Country country;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	private List<Municipality> municipalities;

	public Department() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Municipality> getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", country=" + country + "]";
	}
}
