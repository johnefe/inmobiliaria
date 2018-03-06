package com.proinsalud.sistemas.core.digital_turn.dao;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.ModuleUser;

public interface IModuleUserDao {

	public ModuleUser persistEntity(ModuleUser entity);
	
	public ModuleUser mergeEntity(ModuleUser entity);
	
	public void deleteEntity(ModuleUser entity);
	
	public List<ModuleUser> findAllEntity();
	
	public ModuleUser findEntityById(Long id);
	
	public void persistEntity(List<ModuleUser> entities);
	
	public void mergeEntity(List<ModuleUser> entities);
	
	public void deleteEntity(List<ModuleUser> entities);
	
	public List<ModuleUser> findEntityByUserId(Long idUser);
	
	public List<ModuleUser> findEntityModuleByUserId(Long idUser);
	
	public List<ModuleUser> findAllEntityByUserTypeModule(Long idUser, String typeModule);
	
	public void deleteEntityByIdModule(Long idModule, Long idUser);
}
