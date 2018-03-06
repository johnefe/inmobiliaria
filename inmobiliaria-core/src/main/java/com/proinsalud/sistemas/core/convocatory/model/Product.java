package com.proinsalud.sistemas.core.convocatory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Es la tabla producto
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "product", schema = "convocatory")
public class Product implements Serializable {

	private static final long serialVersionUID = -334827270461557339L;

	@Id
	@Column(name = "id_product")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "id_element")
	private Long idElement;

	@Transient
	private Object element;

	public Product() {
		super();
	}

	public Product(String name, Long idElement, Object element) {
		super();
		this.name = name;
		this.idElement = idElement;
		this.element = element;
	}

	public Product(String name) {
		super();
		this.name = name;
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

	public Long getIdElement() {
		return idElement;
	}

	public void setIdElement(Long idElement) {
		this.idElement = idElement;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", idElement=" + idElement + "]";
	}

}
