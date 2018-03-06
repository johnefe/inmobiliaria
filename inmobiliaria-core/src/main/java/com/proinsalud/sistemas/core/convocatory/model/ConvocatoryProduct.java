package com.proinsalud.sistemas.core.convocatory.model;

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

/**
 * Es la tabla intermedia convocatoria producto
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 5:00:20 p. m.
 *
 */
@Entity
@Table(name = "convocatory_producto", schema = "convocatory")
public class ConvocatoryProduct implements Serializable {

	private static final long serialVersionUID = -5213948004994867515L;

	@Id
	@Column(name = "id_convocatory_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_convocatory")
	private Convocatory convocatory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_product")
	private Product product;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "price")
	private Double price;

	public ConvocatoryProduct() {
		super();
	}

	public ConvocatoryProduct(Long id, Convocatory convocatory, Product product, Integer quantity, Double price) {
		super();
		this.id = id;
		this.convocatory = convocatory;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Convocatory getConvocatory() {
		return convocatory;
	}

	public void setConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ConvocatoryProduct [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
