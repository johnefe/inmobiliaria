package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IUserHistorialDao;
import com.proinsalud.sistemas.core.security.model.UserHistorial;
import com.proinsalud.sistemas.core.security.service.IUserHistorialService;

/**
 * 
 * @author Jhon Frey Diaz
 * @datetime 22/12/2017 - 3:00:23 p. m.
 *
 */
@Repository(value = IUserHistorialService.NAME_SERVICE)
public class UserHistorialService implements IUserHistorialService, Serializable {

	private static final long serialVersionUID = 23134421009750843L;
	
	@Autowired(required = true)
	@Qualifier(value = "userHistorialDao")
	private IUserHistorialDao iUserHistorialDao;

	@Transactional
	public UserHistorial persistEntity(UserHistorial entity) {
		return iUserHistorialDao.persistEntity(entity);
	}

	@Transactional
	public UserHistorial mergeEntity(UserHistorial entity) {
		return iUserHistorialDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(UserHistorial entity) {
		iUserHistorialDao.deleteEntity(entity);
	}

	@Transactional
	public List<UserHistorial> findAllEntity() {
		return iUserHistorialDao.findAllEntity();
	}

	@Transactional
	public UserHistorial findEntityById(Long id) {
		return iUserHistorialDao.findEntityById(id);
	}

	@Transactional
	public List<UserHistorial> findUserHistorialByIdUser(Long IdUser) {
		return iUserHistorialDao.findUserHistorialByIdUser(IdUser);
	}

}
