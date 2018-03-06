package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IUserrDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Userr;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "userrDao")
public class UserrDao extends GenericDao<Long, Userr> implements IUserrDao, Serializable {

	/**
	 * @author jhon frey diaz
	 * @datetime 2/03/2018 - 2:18:56 p. m.
	 */
	private static final long serialVersionUID = -1979115053959503181L;

	public Userr persistEntity(Userr entity) {
		return super.persist(entity);
	}

	public Userr mergeEntity(Userr entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Userr entity) {
		super.delete(entity);
	}

	public List<Userr> findAllEntity() {
		return super.findAll();
	}

	public Userr findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Userr> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Userr> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Userr> entities) {
		super.deleteAll(entities);
	}

}
