package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IServiceDao;
import com.proinsalud.sistemas.core.digital_turn.model.Service;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "serviceDao")
public class ServiceDao extends GenericDao<Long, Service> implements IServiceDao, Serializable {

	private static final long serialVersionUID = 3717198054718490307L;

	public Service persistEntity(Service entity) {
		return super.persist(entity);
	}

	public Service mergeEntity(Service entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Service entity) {
		super.delete(entity);
	}

	public List<Service> findAllEntity() {
		return super.findAll();
	}

	public Service findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Service> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Service> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Service> entities) {
		super.deleteAll(entities);
	}

	public List<Service> findAllEntityFather() {
		List<Service> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		lst = executeNamedQuery("Service.findAllEntityFather", parametros);
		return lst;
	}

	@Override
	public List<Service> findEntityByIdFather(Long idFather) {
		List<Service> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idFather", idFather);
		lst = executeNamedQuery("Service.findEntityByIdFather", parametros);
		return lst.isEmpty() ? new ArrayList<>() : lst;
	}

}
