package com.proinsalud.sistemas.core.digital_turn.dao;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.Module;

public interface IModuleDao {

	public Module persistEntity(Module entity);
	
	public Module mergeEntity(Module entity);
	
	public void deleteEntity(Module entity);
	
	public List<Module> findAllEntity();
	
	public Module findEntityById(Long id);
	
	public void persistEntity(List<Module> entities);
	
	public void mergeEntity(List<Module> entities);
	
	public void deleteEntity(List<Module> entities);
	
	public List<Module> findPointByModule(Long idModule);
	
	public List<Module> findEntityByPoint(Long idPoint);
		
}
