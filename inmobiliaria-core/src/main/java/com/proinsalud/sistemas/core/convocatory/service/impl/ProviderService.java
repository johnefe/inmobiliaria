package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IProviderDao;
import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.convocatory.service.IProviderService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:04:21 a. m.
 *
 */
@Repository(value = "providerService")
public class ProviderService implements IProviderService, Serializable {

	private static final long serialVersionUID = 4627119096556789992L;
	
	@Autowired(required = true)
	@Qualifier(value = "providerDao")
	private IProviderDao iProviderDao;

	@Transactional
	public Provider persistEntity(Provider entity) {
		return iProviderDao.persistEntity(entity);
	}

	@Transactional
	public Provider mergeEntity(Provider entity) {
		return iProviderDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Provider entity) {
		iProviderDao.deleteEntity(entity);
	}

	@Transactional
	public List<Provider> findAllEntity() {
		return iProviderDao.findAllEntity();
	}

	@Transactional
	public Provider findEntityById(Long id) {
		return iProviderDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Provider> entities) {
		iProviderDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Provider> entities) {
		iProviderDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Provider> entities) {
		iProviderDao.deleteEntity(entities);
	}

	@Transactional
	public Provider findEntityByIdPerson(Long idPerson) {
		return iProviderDao.findEntityByIdPerson(idPerson);
	}

}

