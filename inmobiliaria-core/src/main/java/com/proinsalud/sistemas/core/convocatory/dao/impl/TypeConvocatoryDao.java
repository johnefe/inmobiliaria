package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.ITypeConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.TypeConvocatory;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:46:04 a. m.
 *
 */
@Repository(value = "typeConvocatoryDao")
public class TypeConvocatoryDao extends GenericDao<Long, TypeConvocatory> implements ITypeConvocatoryDao, Serializable {

	private static final long serialVersionUID = -717343280135434513L;

	public TypeConvocatory persistEntity(TypeConvocatory entity) {
		return super.persist(entity);
	}

	public TypeConvocatory mergeEntity(TypeConvocatory entity) {
		return super.merge(entity);
	}

	public void deleteEntity(TypeConvocatory entity) {
		super.delete(entity);
	}

	public List<TypeConvocatory> findAllEntity() {
		return super.findAll();
	}

	public TypeConvocatory findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<TypeConvocatory> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<TypeConvocatory> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<TypeConvocatory> entities) {
		super.deleteAll(entities);
	}

}
