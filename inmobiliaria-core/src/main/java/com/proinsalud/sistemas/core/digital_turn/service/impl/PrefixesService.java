package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IPrefixesDao;
import com.proinsalud.sistemas.core.digital_turn.model.Prefixes;
import com.proinsalud.sistemas.core.digital_turn.service.IPrefixesService;

@Repository(value = "prefixesService")
public class PrefixesService implements IPrefixesService, Serializable {

	private static final long serialVersionUID = -8423926757211023345L;
	@Autowired(required = true)
	@Qualifier(value = "prefixesDao")
	private IPrefixesDao iPrefixesDao;

	@Transactional
	public Prefixes persistEntity(Prefixes entity) {
		return iPrefixesDao.persistEntity(entity);
	}

	@Transactional
	public Prefixes mergeEntity(Prefixes entity) {
		return iPrefixesDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Prefixes entity) {
		iPrefixesDao.deleteEntity(entity);
	}

	@Transactional
	public List<Prefixes> findAllEntity() {
		return iPrefixesDao.findAllEntity();
	}

	@Transactional
	public Prefixes findEntityById(Long id) {
		return iPrefixesDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Prefixes> entities) {
		iPrefixesDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Prefixes> entities) {
		iPrefixesDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Prefixes> entities) {
		iPrefixesDao.deleteEntity(entities);
	}

}

