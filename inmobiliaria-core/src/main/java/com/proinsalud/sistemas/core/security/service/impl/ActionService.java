package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IActionDao;
import com.proinsalud.sistemas.core.security.model.Action;
import com.proinsalud.sistemas.core.security.service.IActionService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:49:37 a. m.
 *
 */
@Repository(value = "actionService")
public class ActionService implements IActionService, Serializable {

	private static final long serialVersionUID = 5425416180449009846L;

	@Autowired(required = true)
	@Qualifier(value = "actionDao")
	private IActionDao iActionDao;

	@Transactional
	public Action persistEntity(Action entity) {
		return iActionDao.persistEntity(entity);
	}

	@Transactional
	public Action mergeEntity(Action entity) {
		return iActionDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Action entity) {
		iActionDao.deleteEntity(entity);
	}

	@Transactional
	public List<Action> findAllEntity() {
		return iActionDao.findAllEntity();
	}

	@Transactional
	public Action findEntityById(Long id) {
		return iActionDao.findEntityById(id);
	}

}
