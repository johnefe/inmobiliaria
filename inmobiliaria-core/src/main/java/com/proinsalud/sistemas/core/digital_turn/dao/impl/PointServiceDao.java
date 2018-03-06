package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IPointServiceDao;
import com.proinsalud.sistemas.core.digital_turn.model.PointService;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "pointServiceDao")
public class PointServiceDao extends GenericDao<Long, PointService> implements IPointServiceDao, Serializable {

	private static final long serialVersionUID = 7017300282618710447L;

	public PointService persistEntity(PointService entity) {
		return super.persist(entity);
	}

	public PointService mergeEntity(PointService entity) {
		return super.merge(entity);
	}

	public void deleteEntity(PointService entity) {
		super.delete(entity);
	}

	public List<PointService> findAllEntity() {
		return super.findAll();
	}

	public PointService findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<PointService> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<PointService> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<PointService> entities) {
		super.deleteAll(entities);
	}

	public List<PointService> findEntityByIdPoint(Long idPoint) {
		List<PointService> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPoint", idPoint);
		lst = executeNamedQuery("PointService.findEntityByIdPoint", parametros);
		return lst;
	}
	
}
