package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IPersonsDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Persons;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 2:04:13 p. m.
 */
@Repository(value = "personsDao")
public class PersonsDao extends GenericDao<Long, Persons> implements IPersonsDao, Serializable {

	
	private static final long serialVersionUID = 262922894895922989L;

	public Persons persistEntity(Persons entity) {
		return super.persist(entity);
	}

	public Persons mergeEntity(Persons entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Persons entity) {
		super.delete(entity);
	}

	public List<Persons> findAllEntity() {
		return super.findAll();
	}

	public Persons findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Persons> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Persons> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Persons> entities) {
		super.deleteAll(entities);
	}

}
