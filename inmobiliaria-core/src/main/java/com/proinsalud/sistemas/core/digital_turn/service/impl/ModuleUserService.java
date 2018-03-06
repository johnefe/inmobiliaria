package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IModuleUserDao;
import com.proinsalud.sistemas.core.digital_turn.model.ModuleUser;
import com.proinsalud.sistemas.core.digital_turn.service.IModuleUserService;

@Repository(value = "moduleUserService")
public class ModuleUserService implements IModuleUserService, Serializable {

	private static final long serialVersionUID = 937707111084812318L;
	@Autowired(required = true)
	@Qualifier(value = "moduleUserDao")
	private IModuleUserDao iModuleUserDao;

	@Transactional
	public ModuleUser persistEntity(ModuleUser entity) {
		return iModuleUserDao.persistEntity(entity);
	}

	@Transactional
	public ModuleUser mergeEntity(ModuleUser entity) {
		return iModuleUserDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(ModuleUser entity) {
		iModuleUserDao.deleteEntity(entity);
	}

	@Transactional
	public List<ModuleUser> findAllEntity() {
		return iModuleUserDao.findAllEntity();
	}

	@Transactional
	public ModuleUser findEntityById(Long id) {
		return iModuleUserDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<ModuleUser> entities) {
		iModuleUserDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<ModuleUser> entities) {
		iModuleUserDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<ModuleUser> entities) {
		iModuleUserDao.deleteEntity(entities);
	}

	@Transactional
	public List<ModuleUser> findEntityByUserId(Long idUser) {
		return iModuleUserDao.findEntityByUserId(idUser);
	}

	@Transactional
	public List<ModuleUser> findEntityModuleByUserId(Long idUser) {
		return iModuleUserDao.findEntityModuleByUserId(idUser);
	}

	@Transactional
	public List<ModuleUser> findAllEntityByUserTypeModule(Long idUser, String typeModule) {
		return iModuleUserDao.findAllEntityByUserTypeModule(idUser, typeModule);
	}

	@Transactional
	public void deleteEntityByIdModule(Long idModule, Long idUser) {
		// TODO Auto-generated method stub
		iModuleUserDao.deleteEntityByIdModule(idModule, idUser);
		
	}

}

