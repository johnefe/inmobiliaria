package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IDigitalTurnTempDao;
import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurnTemp;
import com.proinsalud.sistemas.core.digital_turn.service.IDigitalTurnTempService;

@Repository(value = "digitalTurnTempService")
public class DigitalTurnTempService implements IDigitalTurnTempService, Serializable {

	private static final long serialVersionUID = 3591481235004926911L;
	@Autowired(required = true)
	@Qualifier(value = "digitalTurnTempDao")
	private IDigitalTurnTempDao iDigitalTurnTempDao;

	@Transactional
	public DigitalTurnTemp persistEntity(DigitalTurnTemp entity) {
		return iDigitalTurnTempDao.persistEntity(entity);
	}

	@Transactional
	public DigitalTurnTemp mergeEntity(DigitalTurnTemp entity) {
		return iDigitalTurnTempDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(DigitalTurnTemp entity) {
		iDigitalTurnTempDao.deleteEntity(entity);
	}

	@Transactional
	public List<DigitalTurnTemp> findAllEntity() {
		return iDigitalTurnTempDao.findAllEntity();
	}

	@Transactional
	public DigitalTurnTemp findEntityById(Long id) {
		return iDigitalTurnTempDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<DigitalTurnTemp> entities) {
		iDigitalTurnTempDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<DigitalTurnTemp> entities) {
		iDigitalTurnTempDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<DigitalTurnTemp> entities) {
		iDigitalTurnTempDao.deleteEntity(entities);
	}

	@Transactional
	public List<DigitalTurnTemp> findEndsTwentyRegisters(Long idPoint, String enAtencion, String llamando) {
		return iDigitalTurnTempDao.findEndsTwentyRegisters(idPoint,enAtencion, llamando);
	}

	@Transactional
	public List<DigitalTurnTemp> findAllEntityByOrder() {
		return iDigitalTurnTempDao.findAllEntityByOrder();
	}

	@Transactional
	public List<DigitalTurnTemp> findAllEntityByPoint(Long idPoint, String state) {
		return iDigitalTurnTempDao.finAllEntitybyPoint(idPoint, state);
	}

	@Transactional
	public List<DigitalTurnTemp> findTurnByStateAttention(Long idPoint, String atendido) {
		
		return iDigitalTurnTempDao.findTurnByStateAttention(idPoint, atendido);
	}

	@Transactional
	public List<List<DigitalTurnTemp>> findEntityCustomViewTV(Long idPoint) {
		return iDigitalTurnTempDao.findEntityCustomViewTV(idPoint);
	}
}

