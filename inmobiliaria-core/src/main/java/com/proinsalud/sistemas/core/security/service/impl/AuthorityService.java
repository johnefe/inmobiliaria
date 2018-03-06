package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IAuthorityDao;
import com.proinsalud.sistemas.core.security.model.Authority;
import com.proinsalud.sistemas.core.security.service.IAuthorityService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:49:49 a. m.
 *
 */
@Repository(value = "authorityService")
public class AuthorityService implements IAuthorityService, Serializable {

	private static final long serialVersionUID = -7369801373332033339L;

	@Autowired(required = true)
	@Qualifier(value = "authorityDao")
	private IAuthorityDao iAuthorityDao;

	@Transactional
	public Authority persistEntity(Authority entity) {
		return iAuthorityDao.persistEntity(entity);
	}

	@Transactional
	public Authority mergeEntity(Authority entity) {
		return iAuthorityDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Authority entity) {
		iAuthorityDao.deleteEntity(entity);
	}

	@Transactional
	public List<Authority> findAllEntity() {
		return iAuthorityDao.findAllEntity();
	}

	@Transactional
	public Authority findEntityById(Long id) {
		return iAuthorityDao.findEntityById(id);
	}

}
