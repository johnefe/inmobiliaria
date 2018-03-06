package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IModuleDao;
import com.proinsalud.sistemas.core.digital_turn.model.Module;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "moduleDao")
public class ModuleDao extends GenericDao<Long, Module> implements IModuleDao, Serializable {

	private static final long serialVersionUID = 8809096851250109754L;

	public Module persistEntity(Module entity) {
		return super.persist(entity);
	}

	public Module mergeEntity(Module entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Module entity) {
		super.delete(entity);
	}

	public List<Module> findAllEntity() {
		return super.findAll();
	}

	public Module findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Module> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Module> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Module> entities) {
		super.deleteAll(entities);
	}

	@Override
	public List<Module> findPointByModule(Long idModule) {
		
		List<Module> lst = new ArrayList<Module>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idModule", idModule);
		lst = executeNamedQuery("Module.findPointByModule", parametros);
		return lst;
	}
	
	public List<Module> findEntityByPoint(Long idPoint) {
		List<Module> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPoint", idPoint);
		lst = executeNamedQuery("Module.findEntityByPoint", parametros);
		return lst;
	}
}
