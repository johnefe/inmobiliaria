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
import javax.persistence.Transient;

/**
 * Es la tabla intermedia convocatoria producto
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 5:00:20 p. m.
 *
 */
@Entity
@Table(name = "quotation_convocatory_producto", schema = "convocatory")
public class QuotationConvocatoryProduct implements Serializable {

	private static final long serialVersionUID = -5213948004994867515L;

	@Id
	@Column(name = "id_quotation_convocatory_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_quotation")
	private Quotation quotation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_convocatory_product")
	private ConvocatoryProduct convocatoryProduct;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "price")
	private Double price;

	@Transient
	private String color;

	@Transient
	private boolean checked;

	public QuotationConvocatoryProduct() {
		super();
	}

	public QuotationConvocatoryProduct(Quotation quotation, ConvocatoryProduct convocatoryProduct, Integer quantity, Double price) {
		super();
		this.quotation = quotation;
		this.convocatoryProduct = convocatoryProduct;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	public ConvocatoryProduct getConvocatoryProduct() {
		return convocatoryProduct;
	}

	public void setConvocatoryProduct(ConvocatoryProduct convocatoryProduct) {
		this.convocatoryProduct = convocatoryProduct;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "QuotationConvocatoryProduct [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
