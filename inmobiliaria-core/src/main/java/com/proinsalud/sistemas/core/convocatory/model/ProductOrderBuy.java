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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Es la tabla para los productos de la orden de compra
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "product_order_buy", schema = "convocatory")
public class ProductOrderBuy implements Serializable {

	private static final long serialVersionUID = -9072138561869550177L;

	@Id
	@Column(name = "id_product_order_buy")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_order_buy")
	private OrderBuy orderBuy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_quotation_convocatory_producto")
	private QuotationConvocatoryProduct productSelected;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productOrderBuy")
	private List<CommentProductOrderBuy> comments;

	@Transient
	private String comment;

	public ProductOrderBuy() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderBuy getOrderBuy() {
		return orderBuy;
	}

	public void setOrderBuy(OrderBuy orderBuy) {
		this.orderBuy = orderBuy;
	}

	public QuotationConvocatoryProduct getProductSelected() {
		return productSelected;
	}

	public void setProductSelected(QuotationConvocatoryProduct productSelected) {
		this.productSelected = productSelected;
	}

	public List<CommentProductOrderBuy> getComments() {
		return comments;
	}

	public void setComments(List<CommentProductOrderBuy> comments) {
		this.comments = comments;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ProductOrderBuy [id=" + id + "]";
	}

}
