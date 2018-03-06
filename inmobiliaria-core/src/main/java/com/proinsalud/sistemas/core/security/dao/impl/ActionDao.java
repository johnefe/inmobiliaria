package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IActionDao;
import com.proinsalud.sistemas.core.security.model.Action;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:47:44 a. m.
 *
 */
@Repository(value = "actionDao")
public class ActionDao extends GenericDao<Long, Action> implements IActionDao, Serializable {

	private static final long serialVersionUID = -2293653979632842592L;

	public Action persistEntity(Action entity) {
		return super.persist(entity);
	}

	public Action mergeEntity(Action entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Action entity) {
		super.delete(entity);
	}

	public List<Action> findAllEntity() {
		return super.findAll();
	}

	public Action findEntityById(Long id) {
		return super.findById(id);
	}
}
