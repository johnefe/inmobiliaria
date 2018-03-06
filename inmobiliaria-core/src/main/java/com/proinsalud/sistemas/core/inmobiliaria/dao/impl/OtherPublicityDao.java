package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IOtherPublicityDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.OtherPublicity;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "otherPublicityDao")
public class OtherPublicityDao extends GenericDao<Long, OtherPublicity> implements IOtherPublicityDao, Serializable {

	private static final long serialVersionUID = -4554311274516612322L;

	public OtherPublicity persistEntity(OtherPublicity entity) {
		return super.persist(entity);
	}

	public OtherPublicity mergeEntity(OtherPublicity entity) {
		return super.merge(entity);
	}

	public void deleteEntity(OtherPublicity entity) {
		super.delete(entity);
	}

	public List<OtherPublicity> findAllEntity() {
		return super.findAll();
	}

	public OtherPublicity findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<OtherPublicity> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<OtherPublicity> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<OtherPublicity> entities) {
		super.deleteAll(entities);
	}

}
