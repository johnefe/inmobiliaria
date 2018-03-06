package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IDigitalTurnDao;
import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurn;
import com.proinsalud.sistemas.core.digital_turn.service.IDigitalTurnService;

@Repository(value = "digitalTurnService")
public class DigitalTurnService implements IDigitalTurnService, Serializable {

	private static final long serialVersionUID = -4129581350389986787L;
	@Autowired(required = true)
	@Qualifier(value = "digitalTurnDao")
	private IDigitalTurnDao iDigitalTurnDao;

	@Transactional
	public DigitalTurn persistEntity(DigitalTurn entity) {
		return iDigitalTurnDao.persistEntity(entity);
	}

	@Transactional
	public DigitalTurn mergeEntity(DigitalTurn entity) {
		return iDigitalTurnDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(DigitalTurn entity) {
		iDigitalTurnDao.deleteEntity(entity);
	}

	@Transactional
	public List<DigitalTurn> findAllEntity() {
		return iDigitalTurnDao.findAllEntity();
	}

	@Transactional
	public DigitalTurn findEntityById(Long id) {
		return iDigitalTurnDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<DigitalTurn> entities) {
		iDigitalTurnDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<DigitalTurn> entities) {
		iDigitalTurnDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<DigitalTurn> entities) {
		iDigitalTurnDao.deleteEntity(entities);
	}

}

