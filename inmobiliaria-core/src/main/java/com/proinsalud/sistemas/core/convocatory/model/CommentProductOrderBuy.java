package com.proinsalud.sistemas.core.convocatory.model;

import java.io.Serializable;
import java.util.Date;

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

import com.proinsalud.sistemas.core.security.model.Users;

/**
 * Es la tabla para los comentarios de los productos de la orden de compra
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "comment_product_order_buy", schema = "convocatory")
@NamedQueries({ @NamedQuery(name = "CommentProductOrderBuy.findAllEntityByIdProductOrderBuy", query = "SELECT c FROM CommentProductOrderBuy c WHERE c.productOrderBuy.id=:idProductOrderBuy") })
public class CommentProductOrderBuy implements Serializable {

	private static final long serialVersionUID = 436829489161293354L;

	@Id
	@Column(name = "id_comment_products_order_buy")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "comment")
	private String comment;

	@Column(name = "registered", insertable = false, updatable = false)
	private Date registered;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_product_order_buy")
	private ProductOrderBuy productOrderBuy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users user;

	public CommentProductOrderBuy(String comment, ProductOrderBuy productOrderBuy, Users user) {
		super();
		this.comment = comment;
		this.productOrderBuy = productOrderBuy;
		this.user = user;
	}

	public CommentProductOrderBuy() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public ProductOrderBuy getProductOrderBuy() {
		return productOrderBuy;
	}

	public void setProductOrderBuy(ProductOrderBuy productOrderBuy) {
		this.productOrderBuy = productOrderBuy;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CommentProductOrderBuy [id=" + id + ", comment=" + comment + ", registered=" + registered + "]";
	}

}
