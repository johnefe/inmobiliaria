package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IPointServicePriorityDao;
import com.proinsalud.sistemas.core.digital_turn.model.PointServicePriority;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "pointServicePriorityDao")
public class PointServicePriorityDao extends GenericDao<Long, PointServicePriority> implements IPointServicePriorityDao, Serializable {

	private static final long serialVersionUID = 4333432270969899471L;

	public PointServicePriority persistEntity(PointServicePriority entity) {
		return super.persist(entity);
	}

	public PointServicePriority mergeEntity(PointServicePriority entity) {
		return super.merge(entity);
	}

	public void deleteEntity(PointServicePriority entity) {
		super.delete(entity);
	}

	public List<PointServicePriority> findAllEntity() {
		return super.findAll();
	}

	public PointServicePriority findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<PointServicePriority> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<PointServicePriority> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<PointServicePriority> entities) {
		super.deleteAll(entities);
	}

	public PointServicePriority findEntityByIdPointService(Long idPointService, Long idPriorityTurn) {
		List<PointServicePriority> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPointService", idPointService);
		parametros.put("idPriorityTurn", idPriorityTurn);
		lst = executeNamedQuery("PointServicePriority.findEntityByIdPointService", parametros);
		return lst.isEmpty() ? null : lst.get(0);
	}

}
