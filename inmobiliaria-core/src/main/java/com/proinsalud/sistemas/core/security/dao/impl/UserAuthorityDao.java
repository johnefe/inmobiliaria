package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IUserAuthorityDao;
import com.proinsalud.sistemas.core.security.model.UserAuthority;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:48:29 a. m.
 *
 */
@Repository(value = "userAuthorityDao")
public class UserAuthorityDao extends GenericDao<Long, UserAuthority> implements IUserAuthorityDao, Serializable {

	private static final long serialVersionUID = -1206407380858762122L;

	public UserAuthority persistEntity(UserAuthority entity) {
		return super.persist(entity);
	}

	public UserAuthority mergeEntity(UserAuthority entity) {
		return super.merge(entity);
	}

	public void deleteEntity(UserAuthority entity) {
		super.delete(entity);
	}

	public List<UserAuthority> findAllEntity() {
		return super.findAll();
	}

	public UserAuthority findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<UserAuthority> entities) {
		super.persistAll(entities);
	}

	public void deleteEntity(List<UserAuthority> entities) {
		super.deleteAll(entities);
	}

}
