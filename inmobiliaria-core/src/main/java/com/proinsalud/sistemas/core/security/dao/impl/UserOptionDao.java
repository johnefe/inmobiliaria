package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IUserOptionDao;
import com.proinsalud.sistemas.core.security.model.UserOption;

/**
 * 
 * @author Andres Santacruz
 * @datetime 3/01/2018 - 4:07:36 p. m.
 *
 */
@Repository(value = "userOptionDao")
public class UserOptionDao extends GenericDao<Long, UserOption> implements IUserOptionDao, Serializable {

	private static final long serialVersionUID = 3157212765344529984L;

	public UserOption persistEntity(UserOption entity) {
		return super.persist(entity);
	}

	public UserOption mergeEntity(UserOption entity) {
		return super.merge(entity);
	}

	public void deleteEntity(UserOption entity) {
		super.delete(entity);
	}

	public List<UserOption> findAllEntity() {
		return super.findAll();
	}

	public UserOption findEntityById(Long id) {
		return super.findById(id);
	}

	public List<UserOption> findByUser(Long id) {
		List<UserOption> usersOptions = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idUser", id);
		usersOptions = executeNamedQuery("UserOption.findByUser", params);
		return usersOptions;
	}

	public void persistEntity(List<UserOption> entities) {
		super.persistAll(entities);
	}

	public void deleteEntity(List<UserOption> entities) {
		super.deleteAll(entities);
	}

	public UserOption findEntityByOptionUser(Long idOption, Long idUser) {
		List<UserOption> uOptions = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idOption", idOption);
		params.put("idUser", idUser);
		uOptions = executeNamedQuery("UserOption.findEntityByOptionUser", params);
		return uOptions.isEmpty() ? null : uOptions.get(0);
	}

	public void deleteAllEntityByIdUser(Long idUser) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idUser", idUser);
		executeNamedQueryUpdate("UserOption.deleteAllEntityByIdUser", params);
	}

}
