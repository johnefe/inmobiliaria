package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IPriorityTurnDao;
import com.proinsalud.sistemas.core.digital_turn.model.PriorityTurn;
import com.proinsalud.sistemas.core.digital_turn.service.IPriorityTurnService;

@Repository(value = "priorityTurnService")
public class PriorityTurnService implements IPriorityTurnService, Serializable {

	private static final long serialVersionUID = -3854759820717708920L;
	@Autowired(required = true)
	@Qualifier(value = "priorityTurnDao")
	private IPriorityTurnDao iPriorityTurnDao;

	@Transactional
	public PriorityTurn persistEntity(PriorityTurn entity) {
		return iPriorityTurnDao.persistEntity(entity);
	}

	@Transactional
	public PriorityTurn mergeEntity(PriorityTurn entity) {
		return iPriorityTurnDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(PriorityTurn entity) {
		iPriorityTurnDao.deleteEntity(entity);
	}

	@Transactional
	public List<PriorityTurn> findAllEntity() {
		return iPriorityTurnDao.findAllEntity();
	}

	@Transactional
	public PriorityTurn findEntityById(Long id) {
		return iPriorityTurnDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<PriorityTurn> entities) {
		iPriorityTurnDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<PriorityTurn> entities) {
		iPriorityTurnDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<PriorityTurn> entities) {
		iPriorityTurnDao.deleteEntity(entities);
	}

}

