package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IPointServiceDao;
import com.proinsalud.sistemas.core.digital_turn.model.PointService;
import com.proinsalud.sistemas.core.digital_turn.service.IPointServiceService;

@Repository(value = "pointServiceService")
public class PointServiceService implements IPointServiceService, Serializable {

	private static final long serialVersionUID = 5811930737973337783L;
	@Autowired(required = true)
	@Qualifier(value = "pointServiceDao")
	private IPointServiceDao iPointServiceDao;

	@Transactional
	public PointService persistEntity(PointService entity) {
		return iPointServiceDao.persistEntity(entity);
	}

	@Transactional
	public PointService mergeEntity(PointService entity) {
		return iPointServiceDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(PointService entity) {
		iPointServiceDao.deleteEntity(entity);
	}

	@Transactional
	public List<PointService> findAllEntity() {
		return iPointServiceDao.findAllEntity();
	}

	@Transactional
	public PointService findEntityById(Long id) {
		return iPointServiceDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<PointService> entities) {
		iPointServiceDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<PointService> entities) {
		iPointServiceDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<PointService> entities) {
		iPointServiceDao.deleteEntity(entities);
	}

	@Transactional
	public List<PointService> findEntityByIdPoint(Long idPoint) {
		return iPointServiceDao.findEntityByIdPoint(idPoint);
	}

	
}

