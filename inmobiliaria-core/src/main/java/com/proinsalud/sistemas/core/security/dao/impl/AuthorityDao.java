package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IAuthorityDao;
import com.proinsalud.sistemas.core.security.model.Authority;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:47:50 a. m.
 *
 */
@Repository(value = "authorityDao")
public class AuthorityDao extends GenericDao<Long, Authority> implements IAuthorityDao, Serializable {
	private static final long serialVersionUID = -2503691650136621689L;

	public Authority persistEntity(Authority entity) {
		return super.persist(entity);
	}

	public Authority mergeEntity(Authority entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Authority entity) {
		super.delete(entity);
	}

	public List<Authority> findAllEntity() {
		return super.findAll();
	}

	public Authority findEntityById(Long id) {
		return super.findById(id);
	}

}
