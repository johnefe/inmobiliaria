package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.ITypePublicityDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.TypePublicity;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "typePublicityDao")
public class TypePublicityDao extends GenericDao<Long, TypePublicity> implements ITypePublicityDao, Serializable {

	private static final long serialVersionUID = -654246869756559861L;

	public TypePublicity persistEntity(TypePublicity entity) {
		return super.persist(entity);
	}

	public TypePublicity mergeEntity(TypePublicity entity) {
		return super.merge(entity);
	}

	public void deleteEntity(TypePublicity entity) {
		super.delete(entity);
	}

	public List<TypePublicity> findAllEntity() {
		return super.findAll();
	}

	public TypePublicity findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<TypePublicity> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<TypePublicity> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<TypePublicity> entities) {
		super.deleteAll(entities);
	}

}
