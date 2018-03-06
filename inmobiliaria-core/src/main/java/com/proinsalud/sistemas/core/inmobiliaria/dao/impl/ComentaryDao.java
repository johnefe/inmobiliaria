package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IComentaryDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Comentary;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "comentaryDao")
public class ComentaryDao extends GenericDao<Long, Comentary> implements IComentaryDao, Serializable {

	private static final long serialVersionUID = -9038263374002351677L;

	public Comentary persistEntity(Comentary entity) {
		return super.persist(entity);
	}

	public Comentary mergeEntity(Comentary entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Comentary entity) {
		super.delete(entity);
	}

	public List<Comentary> findAllEntity() {
		return super.findAll();
	}

	public Comentary findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Comentary> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Comentary> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Comentary> entities) {
		super.deleteAll(entities);
	}

}
