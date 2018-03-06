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
import javax.persistence.Table;

import com.proinsalud.sistemas.core.security.model.Users;

/**
 * Es la tabla para guardar los estados o pasos de la Orden de compra order_buy
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "step_order", schema = "convocatory")
public class StepOrder implements Serializable {

	private static final long serialVersionUID = -9072138561869550177L;

	@Id
	@Column(name = "id_step_order")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_order_buy")
	private OrderBuy orderBuy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_step_order_convocatory_type_convocatory")
	private StepOrderConvocatoryTypeConvocatory stepOCTC;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_users")
	private Users user;

	@Column(name = "comment", length = 1000, nullable = false)
	private String comment;

	@Column(name = "registered", insertable = false, updatable = false)
	private Date registered;

	@Column(name = "aprobed")
	private boolean aprobed;

	public StepOrder() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public StepOrderConvocatoryTypeConvocatory getStepOCTC() {
		return stepOCTC;
	}

	public void setStepOCTC(StepOrderConvocatoryTypeConvocatory stepOCTC) {
		this.stepOCTC = stepOCTC;
	}

	public OrderBuy getOrderBuy() {
		return orderBuy;
	}

	public void setOrderBuy(OrderBuy orderBuy) {
		this.orderBuy = orderBuy;
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

	public boolean isAprobed() {
		return aprobed;
	}

	public void setAprobed(boolean aprobed) {
		this.aprobed = aprobed;
	}

	@Override
	public String toString() {
		return "StepOrder [id=" + id + ", comment=" + comment + ", registered=" + registered + ", aprobed=" + aprobed + "]";
	}

}
