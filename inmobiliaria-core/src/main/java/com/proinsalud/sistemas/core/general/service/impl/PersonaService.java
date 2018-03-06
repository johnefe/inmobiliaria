package com.proinsalud.sistemas.core.general.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.general.dao.IPersonaDao;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.general.service.IPersonaService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:05:10 a. m.
 *
 */
@Repository(value = "personaService")
public class PersonaService implements IPersonaService, Serializable {

	private static final long serialVersionUID = 8617979650150386100L;
	@Autowired(required = true)
	@Qualifier(value = "personaDao")
	private IPersonaDao iPersonaDao;

	@Transactional
	public Person persistEntity(Person entity) {
		return iPersonaDao.persistEntity(entity);
	}

	@Transactional
	public Person mergeEntity(Person entity) {
		return iPersonaDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Person entity) {
		iPersonaDao.deleteEntity(entity);
	}

	@Transactional
	public List<Person> findAllEntity() {
		return iPersonaDao.findAllEntity();
	}

	@Transactional
	public Person findEntityById(Long id) {
		return iPersonaDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Person> entities) {
		iPersonaDao.persistEntity(entities);

	}

	@Transactional
	public void mergeEntity(List<Person> entities) {
		iPersonaDao.mergeEntity(entities);

	}

	@Transactional
	public void deleteEntity(List<Person> entities) {
		iPersonaDao.deleteEntity(entities);

	}

	@Transactional
	public List<Person> findEntityByName(String nombre) {
		return iPersonaDao.findEntityByName(nombre);
	}

	@Transactional
	public List<Person> findAllEntityWithUsers() {
		return iPersonaDao.findAllEntityWithUsers();
	}

	@Transactional
	public Person findEntityByIdentification(String identification) {
		return iPersonaDao.findEntityByIdentification(identification);
	}
}
