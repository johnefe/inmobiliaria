package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IPointDao;
import com.proinsalud.sistemas.core.digital_turn.model.Point;
import com.proinsalud.sistemas.core.digital_turn.service.IPointService;

@Repository(value = "pointService")
public class PointService implements IPointService, Serializable {

	private static final long serialVersionUID = -2021363171524692124L;
	@Autowired(required = true)
	@Qualifier(value = "pointDao")
	private IPointDao iPointDao;

	@Transactional
	public Point persistEntity(Point entity) {
		return iPointDao.persistEntity(entity);
	}

	@Transactional
	public Point mergeEntity(Point entity) {
		return iPointDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Point entity) {
		iPointDao.deleteEntity(entity);
	}

	@Transactional
	public List<Point> findAllEntity() {
		return iPointDao.findAllEntity();
	}

	@Transactional
	public Point findEntityById(Long id) {
		return iPointDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Point> entities) {
		iPointDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Point> entities) {
		iPointDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Point> entities) {
		iPointDao.deleteEntity(entities);
	}

}

