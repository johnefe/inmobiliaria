package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IPrefixesDao;
import com.proinsalud.sistemas.core.digital_turn.model.Prefixes;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "prefixesDao")
public class PrefixesDao extends GenericDao<Long, Prefixes> implements IPrefixesDao, Serializable {

	private static final long serialVersionUID = -3740672211029915402L;

	public Prefixes persistEntity(Prefixes entity) {
		return super.persist(entity);
	}

	public Prefixes mergeEntity(Prefixes entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Prefixes entity) {
		super.delete(entity);
	}

	public List<Prefixes> findAllEntity() {
		return super.findAll();
	}

	public Prefixes findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Prefixes> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Prefixes> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Prefixes> entities) {
		super.deleteAll(entities);
	}

}
