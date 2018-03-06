package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IPersonsDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Persons;
import com.proinsalud.sistemas.core.inmobiliaria.service.IPersonsService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 2:04:13 p. m.
 */
@Repository(value = "personsService")
public class PersonsService implements IPersonsService, Serializable {

	private static final long serialVersionUID = -7709629526164210828L;
	@Autowired(required = true)
	@Qualifier(value = "personsDao")
	private IPersonsDao iPersonsDao;

	@Transactional
	public Persons persistEntity(Persons entity) {
		return iPersonsDao.persistEntity(entity);
	}

	@Transactional
	public Persons mergeEntity(Persons entity) {
		return iPersonsDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Persons entity) {
		iPersonsDao.deleteEntity(entity);
	}

	@Transactional
	public List<Persons> findAllEntity() {
		return iPersonsDao.findAllEntity();
	}

	@Transactional
	public Persons findEntityById(Long id) {
		return iPersonsDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Persons> entities) {
		iPersonsDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Persons> entities) {
		iPersonsDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Persons> entities) {
		iPersonsDao.deleteEntity(entities);
	}

}

