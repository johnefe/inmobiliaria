package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IUserOptionActionDao;
import com.proinsalud.sistemas.core.security.model.UserOptionAction;
import com.proinsalud.sistemas.core.security.service.IUserOptionActionService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:50:49 a. m.
 *
 */
@Repository(value = "userOptionActionService")
public class UserOptionActionService implements IUserOptionActionService, Serializable {

	private static final long serialVersionUID = -2326242084708419059L;

	@Autowired(required = true)
	@Qualifier(value = "userOptionActionDao")
	private IUserOptionActionDao iUserOptionActionDao;

	@Transactional
	public UserOptionAction persistEntity(UserOptionAction user) {
		return iUserOptionActionDao.persistEntity(user);
	}

	@Transactional
	public UserOptionAction mergeEntity(UserOptionAction user) {
		return iUserOptionActionDao.mergeEntity(user);
	}

	@Transactional
	public void deleteEntity(UserOptionAction user) {
		iUserOptionActionDao.deleteEntity(user);
	}

	@Transactional
	public List<UserOptionAction> findAllEntity() {
		return iUserOptionActionDao.findAllEntity();
	}

	@Transactional
	public UserOptionAction findEntityById(Long id) {
		return iUserOptionActionDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<UserOptionAction> entities) {
		iUserOptionActionDao.persistEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<UserOptionAction> entities) {
		iUserOptionActionDao.deleteEntity(entities);
	}

	@Transactional
	public void updateUserOptionAction(List<UserOptionAction> newUOA, List<UserOptionAction> deleteUOA) {
		if (!newUOA.isEmpty()) {
			persistEntity(newUOA);
		}
		if (!deleteUOA.isEmpty()) {
			deleteEntity(deleteUOA);
		}
	}

}
