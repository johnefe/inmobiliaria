package com.proinsalud.sistemas.core.general.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.general.dao.IPersonaDao;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:51:49 a. m.
 *
 */
@Repository(value = "personaDao")
public class PersonaDao extends GenericDao<Long, Person> implements IPersonaDao, Serializable {

	private static final long serialVersionUID = -8687652818094248158L;

	public Person persistEntity(Person entity) {
		return super.persist(entity);
	}

	public Person mergeEntity(Person entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Person entity) {
		super.delete(entity);
	}

	public List<Person> findAllEntity() {
		return super.findAll();
	}

	public Person findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Person> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Person> entities) {
		super.mergeAll(entities);

	}

	public void deleteEntity(List<Person> entities) {
		super.deleteAll(entities);
	}

	public List<Person> findEntityByName(String nombre) {
		List<Person> lst = new ArrayList<Person>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombrePersona", nombre);
		lst = executeNamedQuery("Persons.findByNombre", parametros);
		return lst;
	}

	public List<Person> findAllEntityWithUsers() {
		List<Person> lst = new ArrayList<Person>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		lst = executeNamedQuery("Persons.findAllEntityWithUsers", parametros);
		return lst;
	}

	public Person findEntityByIdentification(String identification) {
		List<Person> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("identification", identification);
		lst = executeNamedQuery("Persons.findEntityByIdentification", parametros);
		return lst.isEmpty() ? null : lst.get(0);
	}

}
