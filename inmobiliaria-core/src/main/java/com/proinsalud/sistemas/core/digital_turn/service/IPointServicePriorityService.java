package com.proinsalud.sistemas.core.digital_turn.service;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.PointServicePriority;

public interface IPointServicePriorityService {

	public PointServicePriority persistEntity(PointServicePriority entity);
	
	public PointServicePriority mergeEntity(PointServicePriority entity);
	
	public void deleteEntity(PointServicePriority entity);
	
	public List<PointServicePriority> findAllEntity();
	
	public PointServicePriority findEntityById(Long id);
	
	public void persistEntity(List<PointServicePriority> entities);
	
	public void mergeEntity(List<PointServicePriority> entities);
	
	public void deleteEntity(List<PointServicePriority> entities);
	
	public PointServicePriority findEntityByIdPointService(Long idPointService, Long idPriorityTurn);
	
}
