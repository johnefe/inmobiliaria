package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.ITypeBussinesDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.TypeBussines;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "typeBussinesDao")
public class TypeBussinesDao extends GenericDao<Long, TypeBussines> implements ITypeBussinesDao, Serializable {

	private static final long serialVersionUID = 3441606999617657307L;

	public TypeBussines persistEntity(TypeBussines entity) {
		return super.persist(entity);
	}

	public TypeBussines mergeEntity(TypeBussines entity) {
		return super.merge(entity);
	}

	public void deleteEntity(TypeBussines entity) {
		super.delete(entity);
	}

	public List<TypeBussines> findAllEntity() {
		return super.findAll();
	}

	public TypeBussines findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<TypeBussines> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<TypeBussines> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<TypeBussines> entities) {
		super.deleteAll(entities);
	}

}
