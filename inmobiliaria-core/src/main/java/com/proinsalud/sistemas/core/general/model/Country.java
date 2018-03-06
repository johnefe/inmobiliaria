package com.proinsalud.sistemas.core.general.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proinsalud.sistemas.core.util.json.CustomJson;

@Entity
@Table(name = "country", schema = "general")
@CustomJson
public class Country implements Serializable {

	private static final long serialVersionUID = 657496266589562455L;

	@Id
	@Column(name = "id_country")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100)
	private String name;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	private List<Department> departments;

	public Country() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}

}
