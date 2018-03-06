package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.ILevelDao;
import com.proinsalud.sistemas.core.security.model.Level;
import com.proinsalud.sistemas.core.security.service.ILevelService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:50:00 a. m.
 *
 */
@Repository(value = "levelService")
public class LevelService implements ILevelService, Serializable {

	private static final long serialVersionUID = 647873255559040256L;

	@Autowired(required = true)
	@Qualifier(value = "levelDao")
	private ILevelDao iLevelDao;

	@Transactional
	public Level persistEntity(Level entity) {
		return iLevelDao.persistEntity(entity);
	}

	@Transactional
	public Level mergeEntity(Level entity) {
		return iLevelDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Level entity) {
		iLevelDao.deleteEntity(entity);
	}

	@Transactional
	public List<Level> findAllEntity() {
		return iLevelDao.findAllEntity();
	}

	@Transactional
	public Level findEntityById(Long id) {
		return iLevelDao.findEntityById(id);
	}
}
