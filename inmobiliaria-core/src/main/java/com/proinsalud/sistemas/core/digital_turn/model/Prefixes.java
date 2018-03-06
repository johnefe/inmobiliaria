package com.proinsalud.sistemas.core.digital_turn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mauricio Pinchao
 * @datetime 13/02/2018 - 4:40:50 p. m.
 *
 */
@Entity
@Table(name = "prefixes", schema = "digital_turn")
public class Prefixes implements Serializable {

	private static final long serialVersionUID = -3740376104735399690L;

	@Id
	@Column(name = "id_prefixes")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "order_prefix")
	private Integer orderPrefix;
	
	@Column(name = "color")
	private String color;
	
	public Prefixes() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getDetail() {
		return detail;
	}

	public String getPrefix() {
		return prefix;
	}

	public Integer getOrderPrefix() {
		return orderPrefix;
	}

	public String getColor() {
		return color;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setOrderPrefix(Integer orderPrefix) {
		this.orderPrefix = orderPrefix;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Prefixes [id=" + id + ", detail=" + detail + ", prefix=" + prefix + ", orderPrefix=" + orderPrefix + ", color=" + color + "]";
	}
}
