package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IUserrDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Userr;
import com.proinsalud.sistemas.core.inmobiliaria.service.IUserrService;

@Repository(value = "userrService")
public class UserrService implements IUserrService, Serializable {

	/**
	 * @author Jhon Frey Diaz
	 * @datetime 2/03/2018 - 2:19:24 p. m.
	 */
	private static final long serialVersionUID = 7721036251988459991L;
	@Autowired(required = true)
	@Qualifier(value = "userrDao")
	private IUserrDao iUserrDao;

	@Transactional
	public Userr persistEntity(Userr entity) {
		return iUserrDao.persistEntity(entity);
	}

	@Transactional
	public Userr mergeEntity(Userr entity) {
		return iUserrDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Userr entity) {
		iUserrDao.deleteEntity(entity);
	}

	@Transactional
	public List<Userr> findAllEntity() {
		return iUserrDao.findAllEntity();
	}

	@Transactional
	public Userr findEntityById(Long id) {
		return iUserrDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Userr> entities) {
		iUserrDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Userr> entities) {
		iUserrDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Userr> entities) {
		iUserrDao.deleteEntity(entities);
	}

}

