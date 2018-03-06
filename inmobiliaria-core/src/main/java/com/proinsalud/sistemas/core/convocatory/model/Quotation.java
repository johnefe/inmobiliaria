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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Es la tabla de cotización
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:22:49 a. m.
 *
 */
@Entity
@Table(name = "quotation", schema = "convocatory")
@NamedQueries({ @NamedQuery(name = "Quotation.findEntityByIdProvider", query = "SELECT q FROM Quotation q WHERE q.convocatory.state =:state AND q.provider.id=:idProvider") })
public class Quotation implements Serializable {

	private static final long serialVersionUID = -5213948004994867515L;

	@Id
	@Column(name = "id_quotation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_convocatory")
	private Convocatory convocatory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_provider")
	private Provider provider;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quotation")
	private List<QuotationConvocatoryProduct> quotationConvocatoryProducts;

	public Quotation() {
		super();
	}

	public Quotation(Convocatory convocatory, Provider provider) {
		super();
		this.convocatory = convocatory;
		this.provider = provider;
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

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<QuotationConvocatoryProduct> getQuotationConvocatoryProducts() {
		return quotationConvocatoryProducts;
	}

	public void setQuotationConvocatoryProducts(List<QuotationConvocatoryProduct> quotationConvocatoryProducts) {
		this.quotationConvocatoryProducts = quotationConvocatoryProducts;
	}

	/**
	 * Retorna el valor de la cotizacion hecha por el proveedor
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/01/2018 - 9:36:30 a. m.
	 *
	 * @param p
	 *            ConvocatoryProduct
	 * @return
	 */
	public Double getPriceQuotationConvocatoryProductByProduct(ConvocatoryProduct p) {
		for (QuotationConvocatoryProduct q : quotationConvocatoryProducts) {
			if (q.getConvocatoryProduct().getProduct().getId().equals(p.getProduct().getId())) {
				return q.getPrice();
			}
		}
		return 0.0;
	}

	@Override
	public String toString() {
		return "Quotation [id=" + id + "]";
	}

}
