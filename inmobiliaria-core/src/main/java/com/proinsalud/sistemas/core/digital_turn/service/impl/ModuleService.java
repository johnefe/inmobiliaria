package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IModuleDao;
import com.proinsalud.sistemas.core.digital_turn.model.Module;
import com.proinsalud.sistemas.core.digital_turn.service.IModuleService;

@Repository(value = "moduleService")
public class ModuleService implements IModuleService, Serializable {

	private static final long serialVersionUID = 1474260721187667756L;
	@Autowired(required = true)
	@Qualifier(value = "moduleDao")
	private IModuleDao iModuleDao;

	@Transactional
	public Module persistEntity(Module entity) {
		return iModuleDao.persistEntity(entity);
	}

	@Transactional
	public Module mergeEntity(Module entity) {
		return iModuleDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Module entity) {
		iModuleDao.deleteEntity(entity);
	}

	@Transactional
	public List<Module> findAllEntity() {
		return iModuleDao.findAllEntity();
	}

	@Transactional
	public Module findEntityById(Long id) {
		return iModuleDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Module> entities) {
		iModuleDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Module> entities) {
		iModuleDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Module> entities) {
		iModuleDao.deleteEntity(entities);
	}

	@Transactional
	public List<Module> findPointByModule(Long idModule) {
		return iModuleDao.findPointByModule(idModule);
	}

	@Transactional
	public List<Module> findEntityByPoint(Long idPoint) {
		return iModuleDao.findEntityByPoint(idPoint);
	}

}

