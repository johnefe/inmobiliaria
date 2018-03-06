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
 * @datetime 13/02/2018 - 5:38:09 p. m.
 *
 */
@Entity
@Table(name = "point_service_priority", schema = "digital_turn")
@NamedQueries({
	@NamedQuery(name = "PointServicePriority.findEntityByIdPointService", query = "SELECT ps FROM PointServicePriority ps WHERE ps.pointService.id=:idPointService AND ps.priorityTurn.id=:idPriorityTurn")
})
public class PointServicePriority implements Serializable {

	private static final long serialVersionUID = -5710864436827331274L;
	
	@Id
	@Column(name = "id_point_service_priority")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_point_service")
	private PointService pointService;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_priority_turn")
	private PriorityTurn priorityTurn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_prefix")
	private Prefixes prefix;
	
	public PointServicePriority() {
		super();
	}

	public Long getId() {
		return id;
	}

	public PointService getPointService() {
		return pointService;
	}

	public PriorityTurn getPriorityTurn() {
		return priorityTurn;
	}

	public Prefixes getPrefix() {
		return prefix;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPointService(PointService pointService) {
		this.pointService = pointService;
	}

	public void setPriorityTurn(PriorityTurn priorityTurn) {
		this.priorityTurn = priorityTurn;
	}

	public void setPrefix(Prefixes prefix) {
		this.prefix = prefix;
	}

	@Override
	public String toString() {
		return "PointServicePriority [id=" + id + ", pointService=" + pointService + ", priorityTurn=" + priorityTurn + ", prefix=" + prefix + "]";
	}
}
