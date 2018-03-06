package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IUserOptionActionDao;
import com.proinsalud.sistemas.core.security.model.UserOptionAction;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:48:45 a. m.
 *
 */
@Repository(value = "userOptionActionDao")
public class UserOptionActionDao extends GenericDao<Long, UserOptionAction> implements IUserOptionActionDao, Serializable {

	private static final long serialVersionUID = -8115756818251330278L;

	public UserOptionAction persistEntity(UserOptionAction entity) {
		return super.persist(entity);
	}

	public UserOptionAction mergeEntity(UserOptionAction entity) {
		return super.merge(entity);
	}

	public void deleteEntity(UserOptionAction entity) {
		super.delete(entity);
	}

	public List<UserOptionAction> findAllEntity() {
		return super.findAll();
	}

	public UserOptionAction findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<UserOptionAction> entities) {
		super.persistAll(entities);
	}

	public void deleteEntity(List<UserOptionAction> entities) {
		super.deleteAll(entities);
	}

}
