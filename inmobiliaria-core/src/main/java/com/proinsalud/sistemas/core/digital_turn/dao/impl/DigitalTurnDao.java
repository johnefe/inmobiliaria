package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IDigitalTurnDao;
import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurn;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "digitalTurnDao")
public class DigitalTurnDao extends GenericDao<Long, DigitalTurn> implements IDigitalTurnDao, Serializable {

	private static final long serialVersionUID = -7820849687151971809L;

	public DigitalTurn persistEntity(DigitalTurn entity) {
		return super.persist(entity);
	}

	public DigitalTurn mergeEntity(DigitalTurn entity) {
		return super.merge(entity);
	}

	public void deleteEntity(DigitalTurn entity) {
		super.delete(entity);
	}

	public List<DigitalTurn> findAllEntity() {
		return super.findAll();
	}

	public DigitalTurn findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<DigitalTurn> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<DigitalTurn> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<DigitalTurn> entities) {
		super.deleteAll(entities);
	}

}
