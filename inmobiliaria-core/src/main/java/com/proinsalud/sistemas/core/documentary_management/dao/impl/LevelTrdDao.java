package com.proinsalud.sistemas.core.documentary_management.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.documentary_management.dao.ILevelTrdDao;
import com.proinsalud.sistemas.core.documentary_management.model.LevelTrd;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 18/01/2018 - 7:27:54 a. m.
 */
@Repository(value = "levelTrdDao")
public class LevelTrdDao extends GenericDao<Long, LevelTrd> implements ILevelTrdDao, Serializable {

	private static final long serialVersionUID = 898473161561568801L;

	public LevelTrd persistEntity(LevelTrd entity) {
		return super.persist(entity);
	}

	public LevelTrd mergeEntity(LevelTrd entity) {
		return super.merge(entity);
	}

	public void deleteEntity(LevelTrd entity) {
		super.delete(entity);
	}

	public List<LevelTrd> findAllEntity() {
		return super.findAll();
	}

	public LevelTrd findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<LevelTrd> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<LevelTrd> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<LevelTrd> entities) {
		super.deleteAll(entities);
	}

}
