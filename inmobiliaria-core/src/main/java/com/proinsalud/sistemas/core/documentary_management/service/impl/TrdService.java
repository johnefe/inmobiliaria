package com.proinsalud.sistemas.core.documentary_management.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.documentary_management.dao.ITrdDao;
import com.proinsalud.sistemas.core.documentary_management.model.Trd;
import com.proinsalud.sistemas.core.documentary_management.service.ITrdService;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 29/01/2018 - 10:58:39 a. m.
 */
@Repository(value = "trdService")
public class TrdService implements ITrdService, Serializable {

	private static final long serialVersionUID = 8612508693723671087L;
	@Autowired(required = true)
	@Qualifier(value = "trdDao")
	private ITrdDao iTrdDao;

	@Override
	@Transactional
	public Trd persistEntity(Trd entity) {
		return iTrdDao.persistEntity(entity);
	}

	@Override
	@Transactional
	public Trd mergeEntity(Trd entity) {
		return iTrdDao.mergeEntity(entity);
	}

	@Override
	@Transactional
	public void deleteEntity(Trd entity) {
		iTrdDao.deleteEntity(entity);
	}

	@Override
	@Transactional
	public List<Trd> findAllEntity() {
		return iTrdDao.findAllEntity();
	}

	@Override
	@Transactional
	public Trd findEntityById(Long id) {
		return iTrdDao.findEntityById(id);
	}

	@Override
	@Transactional
	public void persistEntity(List<Trd> entities) {
		iTrdDao.persistEntity(entities);
	}

	@Override
	@Transactional
	public void mergeEntity(List<Trd> entities) {
		iTrdDao.mergeEntity(entities);
	}

	@Override
	@Transactional
	public void deleteEntity(List<Trd> entities) {
		iTrdDao.deleteEntity(entities);
	}
}
