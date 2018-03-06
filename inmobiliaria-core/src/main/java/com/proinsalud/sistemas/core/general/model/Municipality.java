package com.proinsalud.sistemas.core.general.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proinsalud.sistemas.core.util.json.CustomJson;

@Entity
@Table(name = "municipality", schema = "general")
@NamedQueries({ @NamedQuery(name = "Municipality.findByIdDepartment", query = "SELECT m FROM Municipality m WHERE m.department.id=:idDepartment"), })
@CustomJson
public class Municipality implements Serializable {

	private static final long serialVersionUID = 235934164500339822L;

	@Id
	@Column(name = "id_municipality")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_department")
	@JsonManagedReference
	private Department department;

	public Municipality() {
		super();
	}

	@Column(name = "id_municipality")
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String toString() {
		return "Municipality [id=" + id + ", name=" + name + "]";
	}
}
