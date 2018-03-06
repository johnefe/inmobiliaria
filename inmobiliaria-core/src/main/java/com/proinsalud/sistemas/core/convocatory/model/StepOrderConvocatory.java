package com.proinsalud.sistemas.core.convocatory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Es la tabla para la orden de compra
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 7:38:45 a. m.
 *
 */
@Entity
@Table(name = "step_order_convocatory", schema = "convocatory")
public class StepOrderConvocatory implements Serializable {

	private static final long serialVersionUID = -4080956745035845485L;

	@Id
	@Column(name = "id_step_order_convocatory")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	public StepOrderConvocatory() {
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

	@Override
	public String toString() {
		return "StepOrderGeneral [id=" + id + ", name=" + name + "]";
	}

}
