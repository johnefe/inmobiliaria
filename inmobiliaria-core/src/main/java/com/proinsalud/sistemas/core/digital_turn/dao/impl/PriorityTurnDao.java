package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IPriorityTurnDao;
import com.proinsalud.sistemas.core.digital_turn.model.PriorityTurn;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "priorityTurnDao")
public class PriorityTurnDao extends GenericDao<Long, PriorityTurn> implements IPriorityTurnDao, Serializable {

	private static final long serialVersionUID = 6420727352697484079L;

	public PriorityTurn persistEntity(PriorityTurn entity) {
		return super.persist(entity);
	}

	public PriorityTurn mergeEntity(PriorityTurn entity) {
		return super.merge(entity);
	}

	public void deleteEntity(PriorityTurn entity) {
		super.delete(entity);
	}

	public List<PriorityTurn> findAllEntity() {
		return super.findAll();
	}

	public PriorityTurn findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<PriorityTurn> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<PriorityTurn> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<PriorityTurn> entities) {
		super.deleteAll(entities);
	}

}
