package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.ISedeDao;
import com.proinsalud.sistemas.core.digital_turn.model.Sede;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "sedeDao")
public class SedeDao extends GenericDao<Long, Sede> implements ISedeDao, Serializable {

	private static final long serialVersionUID = -7155159477752106103L;

	public Sede persistEntity(Sede entity) {
		return super.persist(entity);
	}

	public Sede mergeEntity(Sede entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Sede entity) {
		super.delete(entity);
	}

	public List<Sede> findAllEntity() {
		return super.findAll();
	}

	public Sede findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Sede> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Sede> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Sede> entities) {
		super.deleteAll(entities);
	}

}
