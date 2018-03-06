package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IUserAuthorityDao;
import com.proinsalud.sistemas.core.security.model.UserAuthority;
import com.proinsalud.sistemas.core.security.service.IUserAuthorityService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:50:35 a. m.
 *
 */
@Repository(value = "userAuthorityService")
public class UserAuthorityService implements IUserAuthorityService, Serializable {

	private static final long serialVersionUID = 4729379769980510311L;
	@Autowired(required = true)
	@Qualifier(value = "userAuthorityDao")
	private IUserAuthorityDao iUserAuthorityDao;

	@Transactional
	public UserAuthority persistEntity(UserAuthority entity) {
		return iUserAuthorityDao.persistEntity(entity);
	}

	@Transactional
	public UserAuthority mergeEntity(UserAuthority entity) {
		return iUserAuthorityDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(UserAuthority entity) {
		iUserAuthorityDao.deleteEntity(entity);
	}

	@Transactional
	public List<UserAuthority> findAllEntity() {
		return iUserAuthorityDao.findAllEntity();
	}

	@Transactional
	public UserAuthority findEntityById(Long id) {
		return iUserAuthorityDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<UserAuthority> entities) {
		iUserAuthorityDao.persistEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<UserAuthority> entities) {
		iUserAuthorityDao.deleteEntity(entities);
	}

}
