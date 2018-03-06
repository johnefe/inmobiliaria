package com.proinsalud.sistemas.core.human_talent.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proinsalud.sistemas.core.general.model.Person;

/**
 * Esta clase contiene la información del usuario
 * @author Mauricio Pinchao
 * @datetime 2/01/2018 - 8:17:15 a. m.
 */
@Entity
@Table(name = "employee", schema = "human_talent")
@NamedQueries({ @NamedQuery(name = "Employee.findEntityByIdPerson", query = "SELECT e FROM Employee e WHERE e.person.id=:idPerson")
			})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee implements Serializable{

	private static final long serialVersionUID = 3362499418684959754L;

	@Id
	@Column(name = "id_employee")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "area", length = 150)
	private String area;
	
	@Column(name = "cargo", length = 150)
	private String cargo;
	
	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;
	
	@Column(name = "salario", length = 100)
	private String salario;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person")
	@JsonManagedReference
	private Person person;
	
	public Employee() {
		
	}
	
	public Long getId() {
		return id;
	}

	public String getArea() {
		return area;
	}

	public String getCargo() {
		return cargo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public String getSalario() {
		return salario;
	}

	public Person getPerson() {
		return person;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
