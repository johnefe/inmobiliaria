package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IUserHistorialDao;
import com.proinsalud.sistemas.core.security.model.UserHistorial;

/**
 * @author Jhon Frey Diaz
 * @datetime 22/12/2017 - 11:01:54 a. m.
 *
 */
@Repository(value = "userHistorialDao")
public class UserHistorialDao extends GenericDao<Long, UserHistorial> implements IUserHistorialDao, Serializable {

	private static final long serialVersionUID = -3580092160344042909L;

	public UserHistorial persistEntity(UserHistorial entity) {
		return super.persist(entity);
	}

	public UserHistorial mergeEntity(UserHistorial entity) {
		return super.merge(entity);
	}

	public void deleteEntity(UserHistorial entity) {
		super.delete(entity);
	}

	public List<UserHistorial> findAllEntity() {
		return super.findAll();
	}

	public UserHistorial findEntityById(Long id) {
		return super.findById(id);
	}

	public List<UserHistorial> findUserHistorialByIdUser(Long IdUser) {
		List<UserHistorial> userHistorials = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idUser", IdUser);
		userHistorials = executeNamedQuery("UserHistorial.findUserHistorialByIdUser", params);
		return userHistorials;

	}

}
