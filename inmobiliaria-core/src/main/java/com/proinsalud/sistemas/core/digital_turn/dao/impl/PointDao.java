package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IPointDao;
import com.proinsalud.sistemas.core.digital_turn.model.Point;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "pointDao")
public class PointDao extends GenericDao<Long, Point> implements IPointDao, Serializable {

	private static final long serialVersionUID = 2754582596560388154L;

	public Point persistEntity(Point entity) {
		return super.persist(entity);
	}

	public Point mergeEntity(Point entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Point entity) {
		super.delete(entity);
	}

	public List<Point> findAllEntity() {
		return super.findAll();
	}

	public Point findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Point> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Point> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Point> entities) {
		super.deleteAll(entities);
	}

}
