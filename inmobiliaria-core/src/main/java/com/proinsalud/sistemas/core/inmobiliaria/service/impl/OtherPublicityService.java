package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IOtherPublicityDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.OtherPublicity;
import com.proinsalud.sistemas.core.inmobiliaria.service.IOtherPublicityService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "otherPublicityService")
public class OtherPublicityService implements IOtherPublicityService, Serializable {

	private static final long serialVersionUID = -8956051512877697782L;
	@Autowired(required = true)
	@Qualifier(value = "otherPublicityDao")
	private IOtherPublicityDao iOtherPublicityDao;

	@Transactional
	public OtherPublicity persistEntity(OtherPublicity entity) {
		return iOtherPublicityDao.persistEntity(entity);
	}

	@Transactional
	public OtherPublicity mergeEntity(OtherPublicity entity) {
		return iOtherPublicityDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(OtherPublicity entity) {
		iOtherPublicityDao.deleteEntity(entity);
	}

	@Transactional
	public List<OtherPublicity> findAllEntity() {
		return iOtherPublicityDao.findAllEntity();
	}

	@Transactional
	public OtherPublicity findEntityById(Long id) {
		return iOtherPublicityDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<OtherPublicity> entities) {
		iOtherPublicityDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<OtherPublicity> entities) {
		iOtherPublicityDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<OtherPublicity> entities) {
		iOtherPublicityDao.deleteEntity(entities);
	}

}

