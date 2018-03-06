package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IModuleUserDao;
import com.proinsalud.sistemas.core.digital_turn.model.ModuleUser;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "moduleUserDao")
public class ModuleUserDao extends GenericDao<Long, ModuleUser> implements IModuleUserDao, Serializable {

	private static final long serialVersionUID = 1595802119395200317L;

	public ModuleUser persistEntity(ModuleUser entity) {
		return super.persist(entity);
	}

	public ModuleUser mergeEntity(ModuleUser entity) {
		return super.merge(entity);
	}

	public void deleteEntity(ModuleUser entity) {
		super.delete(entity);
	}

	public List<ModuleUser> findAllEntity() {
		return super.findAll();
	}

	public ModuleUser findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<ModuleUser> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<ModuleUser> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<ModuleUser> entities) {
		super.deleteAll(entities);
	}

	public List<ModuleUser> findEntityByUserId(Long idUser) {
		List<ModuleUser> lst = new ArrayList<ModuleUser>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idUser", idUser);
		lst = executeNamedQuery("ModuleUser.findEntityByUserId", parametros);
		return lst;
	}

	public List<ModuleUser> findEntityModuleByUserId(Long idUser) {
		List<ModuleUser> lst = new ArrayList<ModuleUser>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idUser", idUser);
		lst = executeNamedQuery("ModuleUser.findEntityModuleByUserId", parametros);
		return lst;
	}

	public List<ModuleUser> findAllEntityByUserTypeModule(Long idUser, String typeModule) {
		List<ModuleUser> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idUser", idUser);
		parametros.put("typeModule", typeModule);
		lst = executeNamedQuery("ModuleUser.findAllEntityByUserTypeModule", parametros);
		return lst;
	}

	@Override
	public void deleteEntityByIdModule(Long idModule, Long idUser) {
		// TODO Auto-generated method stub
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idModule", idModule);
		parametros.put("idUser", idUser);
		executeNamedQuery("ModuleUser.deleteEntityByIdModule", parametros);	
		
		
	}

}
