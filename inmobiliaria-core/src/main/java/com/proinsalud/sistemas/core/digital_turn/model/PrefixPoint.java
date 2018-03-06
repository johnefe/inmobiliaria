package com.proinsalud.sistemas.core.digital_turn.model;

import java.io.Serializable;

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

/**
 * @author Mauricio Pinchao
 * @datetime 13/02/2018 - 4:47:13 p. m.
 *
 */
@Entity
@Table(name = "prefix_point", schema = "digital_turn")
@NamedQueries({
	@NamedQuery(name = "PrefixPoint.findEntityByPrefixPoint", query = "SELECT p FROM PrefixPoint p WHERE p.prefix.id=:idPrefix AND p.point.id=:idPoint"),
	@NamedQuery(name = "PrefixPoint.findEntityByIdPoint", query = "SELECT p FROM PrefixPoint p WHERE p.point.id=:idPoint")
})
public class PrefixPoint implements Serializable {

	private static final long serialVersionUID = 3102449262060560259L;

	@Id
	@Column(name = "id_prefix_point")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_prefix")
	private Prefixes prefix;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_point")
	private Point point;
	
	@Column(name = "secuence")
	private Integer secuence; 
	
	public PrefixPoint() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Prefixes getPrefix() {
		return prefix;
	}

	public Point getPoint() {
		return point;
	}

	public Integer getSecuence() {
		return secuence;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrefix(Prefixes prefix) {
		this.prefix = prefix;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setSecuence(Integer secuence) {
		this.secuence = secuence;
	}

	@Override
	public String toString() {
		return "PrefixPoint [id=" + id + ", prefix=" + prefix + ", point=" + point + ", secuence=" + secuence + "]";
	}
}
