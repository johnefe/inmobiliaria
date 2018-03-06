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
 * @datetime 6/02/2018 - 7:49:10 a. m.
 *
 */
@Entity
@Table(name = "priority_turn", schema = "digital_turn")
public class PriorityTurn implements Serializable {

	private static final long serialVersionUID = 4892729447058023981L;
	
	@Id
	@Column(name = "id_priority_turn")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_priority_turn")
	private String namePriorityTurn;
	
	@Column(name = "priority")
	private Integer prority;
	
	public PriorityTurn() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNamePriorityTurn() {
		return namePriorityTurn;
	}

	public Integer getPrority() {
		return prority;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNamePriorityTurn(String namePriorityTurn) {
		this.namePriorityTurn = namePriorityTurn;
	}

	public void setPrority(Integer prority) {
		this.prority = prority;
	}

	@Override
	public String toString() {
		return "PriorityTurn [id=" + id + ", namePriorityTurn=" + namePriorityTurn + ", prority=" + prority + "]";
	}
}
