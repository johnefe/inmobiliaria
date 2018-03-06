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
 * Es la tabla para los comentarios de la orden
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "comment_order_buy", schema = "convocatory")
@NamedQueries({ @NamedQuery(name = "CommentOrderBuy.findAllEntityByIdOrderBuy", query = "SELECT c FROM CommentOrderBuy c WHERE c.orderBuy.id=:idOrderBuy") })
public class CommentOrderBuy implements Serializable {

	private static final long serialVersionUID = -4064180681636544132L;

	@Id
	@Column(name = "id_comment_order_buy")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "comment")
	private String comment;

	@Column(name = "registered", insertable = false, updatable = false)
	private Date registered;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_order_buy")
	private OrderBuy orderBuy;

	public CommentOrderBuy(String comment, Users user, OrderBuy orderBuy) {
		super();
		this.comment = comment;
		this.user = user;
		this.orderBuy = orderBuy;
	}

	public CommentOrderBuy() {
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public OrderBuy getOrderBuy() {
		return orderBuy;
	}

	public void setOrderBuy(OrderBuy orderBuy) {
		this.orderBuy = orderBuy;
	}

	@Override
	public String toString() {
		return "CommentOrderBuy [id=" + id + ", comment=" + comment + ", registered=" + registered + "]";
	}

}
