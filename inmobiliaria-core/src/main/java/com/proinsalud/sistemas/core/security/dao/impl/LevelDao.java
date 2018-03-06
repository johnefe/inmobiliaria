package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.ILevelDao;
import com.proinsalud.sistemas.core.security.model.Level;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:47:57 a. m.
 *
 */
@Repository(value = "levelDao")
public class LevelDao extends GenericDao<Long, Level> implements ILevelDao, Serializable {

	private static final long serialVersionUID = 5393365490439082759L;

	public Level persistEntity(Level entity) {
		return super.persist(entity);
	}

	public Level mergeEntity(Level entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Level entity) {
		super.delete(entity);
	}

	public List<Level> findAllEntity() {
		return super.findAll();
	}

	public Level findEntityById(Long id) {
		return super.findById(id);
	}

}
