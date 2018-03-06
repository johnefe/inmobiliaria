package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.ITypeConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.TypeConvocatory;
import com.proinsalud.sistemas.core.convocatory.service.ITypeConvocatoryService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:46:17 a. m.
 *
 */
@Repository(value = "typeConvocatoryService")
public class TypeConvocatoryService implements ITypeConvocatoryService, Serializable {

	private static final long serialVersionUID = -265771557359410493L;
	@Autowired(required = true)
	@Qualifier(value = "typeConvocatoryDao")
	private ITypeConvocatoryDao iTypeConvocatoryDao;

	@Transactional
	public TypeConvocatory persistEntity(TypeConvocatory entity) {
		return iTypeConvocatoryDao.persistEntity(entity);
	}

	@Transactional
	public TypeConvocatory mergeEntity(TypeConvocatory entity) {
		return iTypeConvocatoryDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(TypeConvocatory entity) {
		iTypeConvocatoryDao.deleteEntity(entity);
	}

	@Transactional
	public List<TypeConvocatory> findAllEntity() {
		return iTypeConvocatoryDao.findAllEntity();
	}

	@Transactional
	public TypeConvocatory findEntityById(Long id) {
		return iTypeConvocatoryDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<TypeConvocatory> entities) {
		iTypeConvocatoryDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<TypeConvocatory> entities) {
		iTypeConvocatoryDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<TypeConvocatory> entities) {
		iTypeConvocatoryDao.deleteEntity(entities);
	}

}
