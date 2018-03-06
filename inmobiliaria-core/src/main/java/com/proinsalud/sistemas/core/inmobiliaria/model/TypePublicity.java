package com.proinsalud.sistemas.core.inmobiliaria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_publicity", schema = "inmobiliaria")
public class TypePublicity implements Serializable {

	private static final long serialVersionUID = 4320817501684560660L;

	@Id
	@Column(name = "id_type_publicity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_type_publicity")
	private String nameTypePublicity;
	
	@Column(name = "cant_days")
	private Integer cantDays;
	
	@Column(name = "price")
	private double price;
		
	public TypePublicity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameTypePublicity() {
		return nameTypePublicity;
	}

	public void setNameTypePublicity(String nameTypePublicity) {
		this.nameTypePublicity = nameTypePublicity;
	}

	public Integer getCantDays() {
		return cantDays;
	}

	public void setCantDays(Integer cantDays) {
		this.cantDays = cantDays;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TypePublicity [id=" + id + ", nameTypePublicity=" + nameTypePublicity + ", cantDays=" + cantDays
				+ ", price=" + price + "]";
	}
		
}
