package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.ITypePublicityDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.TypePublicity;
import com.proinsalud.sistemas.core.inmobiliaria.service.ITypePublicityService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "typePublicityService")
public class TypePublicityService implements ITypePublicityService, Serializable {
	
	private static final long serialVersionUID = 9037369746726667247L;
	@Autowired(required = true)
	@Qualifier(value = "typePublicityDao")
	private ITypePublicityDao iTypePublicityDao;

	@Transactional
	public TypePublicity persistEntity(TypePublicity entity) {
		return iTypePublicityDao.persistEntity(entity);
	}

	@Transactional
	public TypePublicity mergeEntity(TypePublicity entity) {
		return iTypePublicityDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(TypePublicity entity) {
		iTypePublicityDao.deleteEntity(entity);
	}

	@Transactional
	public List<TypePublicity> findAllEntity() {
		return iTypePublicityDao.findAllEntity();
	}

	@Transactional
	public TypePublicity findEntityById(Long id) {
		return iTypePublicityDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<TypePublicity> entities) {
		iTypePublicityDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<TypePublicity> entities) {
		iTypePublicityDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<TypePublicity> entities) {
		iTypePublicityDao.deleteEntity(entities);
	}

}

