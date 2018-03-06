package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IPointServicePriorityDao;
import com.proinsalud.sistemas.core.digital_turn.model.PointServicePriority;
import com.proinsalud.sistemas.core.digital_turn.service.IPointServicePriorityService;

@Repository(value = "pointServicePriorityService")
public class PointServicePriorityService implements IPointServicePriorityService, Serializable {

	private static final long serialVersionUID = -2604503314516817641L;
	@Autowired(required = true)
	@Qualifier(value = "pointServicePriorityDao")
	private IPointServicePriorityDao iPointServicePriorityDao;

	@Transactional
	public PointServicePriority persistEntity(PointServicePriority entity) {
		return iPointServicePriorityDao.persistEntity(entity);
	}

	@Transactional
	public PointServicePriority mergeEntity(PointServicePriority entity) {
		return iPointServicePriorityDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(PointServicePriority entity) {
		iPointServicePriorityDao.deleteEntity(entity);
	}

	@Transactional
	public List<PointServicePriority> findAllEntity() {
		return iPointServicePriorityDao.findAllEntity();
	}

	@Transactional
	public PointServicePriority findEntityById(Long id) {
		return iPointServicePriorityDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<PointServicePriority> entities) {
		iPointServicePriorityDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<PointServicePriority> entities) {
		iPointServicePriorityDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<PointServicePriority> entities) {
		iPointServicePriorityDao.deleteEntity(entities);
	}

	@Transactional
	public PointServicePriority findEntityByIdPointService(Long idPointService, Long idPriorityTurn) {
		return iPointServicePriorityDao.findEntityByIdPointService(idPointService, idPriorityTurn);
	}

}

