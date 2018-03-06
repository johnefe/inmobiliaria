package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IUserOptionDao;
import com.proinsalud.sistemas.core.security.model.UserOption;
import com.proinsalud.sistemas.core.security.service.IUserOptionService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:50:57 a. m.
 *
 */
@Repository(value = "userOptionService")
public class UserOptionService implements IUserOptionService, Serializable {

	private static final long serialVersionUID = -1632771116192403722L;

	@Autowired(required = true)
	@Qualifier(value = "userOptionDao")
	private IUserOptionDao iUserOptionDao;

	@Override
	@Transactional
	public UserOption persistEntity(UserOption user) {
		return iUserOptionDao.persistEntity(user);
	}

	@Override
	@Transactional
	public UserOption mergeEntity(UserOption user) {
		return iUserOptionDao.mergeEntity(user);
	}

	@Override
	@Transactional
	public void deleteEntity(UserOption user) {
		iUserOptionDao.deleteEntity(user);
	}

	@Override
	@Transactional
	public List<UserOption> findAllEntity() {
		return iUserOptionDao.findAllEntity();
	}

	@Override
	@Transactional
	public UserOption findEntityById(Long id) {
		return iUserOptionDao.findEntityById(id);
	}

	@Override
	@Transactional
	public List<UserOption> findByUser(Long id) {
		return iUserOptionDao.findByUser(id);
	}

	@Override
	@Transactional
	public void persistEntity(List<UserOption> entities) {
		iUserOptionDao.persistEntity(entities);
	}

	@Override
	@Transactional
	public void deleteEntity(List<UserOption> entities) {
		iUserOptionDao.deleteEntity(entities);
	}

	@Override
	@Transactional
	public UserOption findEntityByOptionUser(Long idOption, Long idUser) {
		return iUserOptionDao.findEntityByOptionUser(idOption, idUser);
	}

	@Override
	@Transactional
	public void deleteAllEntityByIdUser(Long idUser) {
		iUserOptionDao.deleteAllEntityByIdUser(idUser);
	}

}
