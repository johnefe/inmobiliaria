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
 * @datetime 6/02/2018 - 7:40:35 a. m.
 *
 */
@Entity
@Table(name = "point_service", schema = "digital_turn")
@NamedQueries({
	@NamedQuery(name = "PointService.findEntityByIdPoint", query = "SELECT p FROM PointService p WHERE p.point.id=:idPoint"),
})
public class PointService implements Serializable {

	private static final long serialVersionUID = -8192749292107637344L;

	@Id
	@Column(name = "id_point_service")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_point")
	private Point point;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_service")
	private Service service;
	
	@Column(name = "state")
	private boolean state;
	
	public PointService() {
		super();
	}

	public PointService(Point point, Service service) {
		super();
		this.point = point;
		this.service = service;
	}
	
	public Long getId() {
		return id;
	}

	public Point getPoint() {
		return point;
	}

	public Service getService() {
		return service;
	}

	public boolean isState() {
		return state;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "PointService [id=" + id + ", point=" + point + ", service=" + service + ", state=" + state + "]";
	}
}
