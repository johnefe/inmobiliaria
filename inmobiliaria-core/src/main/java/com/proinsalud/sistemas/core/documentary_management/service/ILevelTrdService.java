package com.proinsalud.sistemas.core.documentary_management.service;

import java.util.List;

import com.proinsalud.sistemas.core.documentary_management.model.LevelTrd;

public interface ILevelTrdService {

	public LevelTrd persistEntity(LevelTrd entity);
	
	public LevelTrd mergeEntity(LevelTrd entity);
	
	public void deleteEntity(LevelTrd entity);
	
	public List<LevelTrd> findAllEntity();
	
	public LevelTrd findEntityById(Long id);
	
	public void persistEntity(List<LevelTrd> entities);
	
	public void mergeEntity(List<LevelTrd> entities);
	
	public void deleteEntity(List<LevelTrd> entities);
	
}
