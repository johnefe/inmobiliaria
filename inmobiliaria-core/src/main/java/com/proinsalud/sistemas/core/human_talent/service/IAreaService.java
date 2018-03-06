package com.proinsalud.sistemas.core.human_talent.service;

import java.util.List;

import com.proinsalud.sistemas.core.human_talent.model.Area;

public interface IAreaService {

	public Area persistEntity(Area entity);
	
	public Area mergeEntity(Area entity);
	
	public void deleteEntity(Area entity);
	
	public List<Area> findAllEntity();
	
	public Area findEntityById(Long id);
	
	public void persistEntity(List<Area> entities);
	
	public void mergeEntity(List<Area> entities);
	
	public void deleteEntity(List<Area> entities);
	
	public List<Area> findAreaRoot();
	
	public List<Area> findAreaItemsRoot();
	
}
