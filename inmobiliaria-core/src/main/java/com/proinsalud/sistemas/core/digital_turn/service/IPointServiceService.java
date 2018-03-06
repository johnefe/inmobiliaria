package com.proinsalud.sistemas.core.digital_turn.service;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.PointService;

public interface IPointServiceService {

	public PointService persistEntity(PointService entity);
	
	public PointService mergeEntity(PointService entity);
	
	public void deleteEntity(PointService entity);
	
	public List<PointService> findAllEntity();
	
	public PointService findEntityById(Long id);
	
	public void persistEntity(List<PointService> entities);
	
	public void mergeEntity(List<PointService> entities);
	
	public void deleteEntity(List<PointService> entities);
	
	public List<PointService> findEntityByIdPoint(Long idPoint);
	
}
