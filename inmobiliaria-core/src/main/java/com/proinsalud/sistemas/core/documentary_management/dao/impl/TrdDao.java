package com.proinsalud.sistemas.core.documentary_management.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.documentary_management.dao.ITrdDao;
import com.proinsalud.sistemas.core.documentary_management.model.Trd;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * @author Ing Jhon Frey Diaz
 * @datetime 17/01/2018 - 8:25:29 a. m.
 */
@Repository(value = "trdDao")
public class TrdDao extends GenericDao<Long, Trd> implements ITrdDao, Serializable {

	private static final long serialVersionUID = 4589262602195597085L;

	@Override
	public Trd persistEntity(Trd entity) {
		return super.persist(entity);
	}

	@Override
	public Trd mergeEntity(Trd entity) {
		return super.merge(entity);
	}

	@Override
	public void deleteEntity(Trd entity) {
		super.delete(entity);
	}

	@Override
	public List<Trd> findAllEntity() {
		return super.findAll();
	}

	@Override
	public Trd findEntityById(Long id) {
		return super.findById(id);
	}

	@Override
	public void persistEntity(List<Trd> entities) {
		super.persistAll(entities);
	}

	@Override
	public void mergeEntity(List<Trd> entities) {
		super.mergeAll(entities);
	}

	@Override
	public void deleteEntity(List<Trd> entities) {
		super.deleteAll(entities);
	}
}
