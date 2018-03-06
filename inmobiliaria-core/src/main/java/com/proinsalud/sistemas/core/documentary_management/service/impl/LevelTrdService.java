package com.proinsalud.sistemas.core.documentary_management.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.documentary_management.dao.ILevelTrdDao;
import com.proinsalud.sistemas.core.documentary_management.model.LevelTrd;
import com.proinsalud.sistemas.core.documentary_management.service.ILevelTrdService;
/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 29/01/2018 - 10:58:39 a. m.
 */

@Repository(value = "levelTrdService")
public class LevelTrdService implements ILevelTrdService, Serializable {

	private static final long serialVersionUID = -242402051719772306L;
	
	@Autowired(required = true)
	@Qualifier(value = "levelTrdDao")
	private ILevelTrdDao iLevelTrdDao;

	@Transactional
	public LevelTrd persistEntity(LevelTrd entity) {
		return iLevelTrdDao.persistEntity(entity);
	}

	@Transactional
	public LevelTrd mergeEntity(LevelTrd entity) {
		return iLevelTrdDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(LevelTrd entity) {
		iLevelTrdDao.deleteEntity(entity);
	}

	@Transactional
	public List<LevelTrd> findAllEntity() {
		return iLevelTrdDao.findAllEntity();
	}

	@Transactional
	public LevelTrd findEntityById(Long id) {
		return iLevelTrdDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<LevelTrd> entities) {
		iLevelTrdDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<LevelTrd> entities) {
		iLevelTrdDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<LevelTrd> entities) {
		iLevelTrdDao.deleteEntity(entities);
	}

}

