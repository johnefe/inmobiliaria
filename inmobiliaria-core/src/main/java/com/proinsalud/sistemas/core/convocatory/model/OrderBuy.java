package com.proinsalud.sistemas.core.convocatory.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import org.hibernate.annotations.DynamicUpdate;

/**
 * Es la tabla para la orden de compra
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 7:38:45 a. m.
 *
 */
@Entity
@Table(name = "order_buy", schema = "convocatory")
@DynamicUpdate
public class OrderBuy implements Serializable {

	private static final long serialVersionUID = -4080956745035845485L;

	@Id
	@Column(name = "id_order_buy")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "registered", insertable = false, updatable = false)
	private Date registered;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderBuy")
	private List<ProductOrderBuy> productsOrder;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderBuy")
	private List<CommentOrderBuy> comments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_convocatory")
	private Convocatory convocatory;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderBuy")
	private List<StepOrder> steps;

	@Transient
	private String comment;

	public OrderBuy() {
		super();
		comments = new ArrayList<>();
		productsOrder = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public List<ProductOrderBuy> getProductsOrder() {
		return productsOrder;
	}

	public void setProductsOrder(List<ProductOrderBuy> productsOrder) {
		this.productsOrder = productsOrder;
	}

	public List<CommentOrderBuy> getComments() {
		return comments;
	}

	public void setComments(List<CommentOrderBuy> comments) {
		this.comments = comments;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Convocatory getConvocatory() {
		return convocatory;
	}

	public void setConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
	}

	public List<StepOrder> getSteps() {
		return steps;
	}

	public void setSteps(List<StepOrder> steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "OrderBuy [id=" + id + ", code=" + code + ", registered=" + registered + "]";
	}

}
