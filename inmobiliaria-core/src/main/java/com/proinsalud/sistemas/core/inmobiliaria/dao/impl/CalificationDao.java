package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.ICalificationDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Calification;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "calificationDao")
public class CalificationDao extends GenericDao<Long, Calification> implements ICalificationDao, Serializable {

	private static final long serialVersionUID = 2702548837958917706L;

	public Calification persistEntity(Calification entity) {
		return super.persist(entity);
	}

	public Calification mergeEntity(Calification entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Calification entity) {
		super.delete(entity);
	}

	public List<Calification> findAllEntity() {
		return super.findAll();
	}

	public Calification findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Calification> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Calification> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Calification> entities) {
		super.deleteAll(entities);
	}

}
