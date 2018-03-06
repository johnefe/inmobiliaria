package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.ICalificationDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Calification;
import com.proinsalud.sistemas.core.inmobiliaria.service.ICalificationService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "calificationService")
public class CalificationService implements ICalificationService, Serializable {

	private static final long serialVersionUID = -1780191771375675129L;
	@Autowired(required = true)
	@Qualifier(value = "calificationDao")
	private ICalificationDao iCalificationDao;

	@Transactional
	public Calification persistEntity(Calification entity) {
		return iCalificationDao.persistEntity(entity);
	}

	@Transactional
	public Calification mergeEntity(Calification entity) {
		return iCalificationDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Calification entity) {
		iCalificationDao.deleteEntity(entity);
	}

	@Transactional
	public List<Calification> findAllEntity() {
		return iCalificationDao.findAllEntity();
	}

	@Transactional
	public Calification findEntityById(Long id) {
		return iCalificationDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Calification> entities) {
		iCalificationDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Calification> entities) {
		iCalificationDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Calification> entities) {
		iCalificationDao.deleteEntity(entities);
	}

}

