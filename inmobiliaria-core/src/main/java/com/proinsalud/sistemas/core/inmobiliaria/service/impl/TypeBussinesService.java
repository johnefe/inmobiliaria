package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.ITypeBussinesDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.TypeBussines;
import com.proinsalud.sistemas.core.inmobiliaria.service.ITypeBussinesService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "typeBussinesService")
public class TypeBussinesService implements ITypeBussinesService, Serializable {

	private static final long serialVersionUID = 3510548297101322629L;
	@Autowired(required = true)
	@Qualifier(value = "typeBussinesDao")
	private ITypeBussinesDao iTypeBussinesDao;

	@Transactional
	public TypeBussines persistEntity(TypeBussines entity) {
		return iTypeBussinesDao.persistEntity(entity);
	}

	@Transactional
	public TypeBussines mergeEntity(TypeBussines entity) {
		return iTypeBussinesDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(TypeBussines entity) {
		iTypeBussinesDao.deleteEntity(entity);
	}

	@Transactional
	public List<TypeBussines> findAllEntity() {
		return iTypeBussinesDao.findAllEntity();
	}

	@Transactional
	public TypeBussines findEntityById(Long id) {
		return iTypeBussinesDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<TypeBussines> entities) {
		iTypeBussinesDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<TypeBussines> entities) {
		iTypeBussinesDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<TypeBussines> entities) {
		iTypeBussinesDao.deleteEntity(entities);
	}

}

