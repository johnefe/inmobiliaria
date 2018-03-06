package com.proinsalud.sistemas.core.digital_turn.service;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.Point;

public interface IPointService {

	public Point persistEntity(Point entity);
	
	public Point mergeEntity(Point entity);
	
	public void deleteEntity(Point entity);
	
	public List<Point> findAllEntity();
	
	public Point findEntityById(Long id);
	
	public void persistEntity(List<Point> entities);
	
	public void mergeEntity(List<Point> entities);
	
	public void deleteEntity(List<Point> entities);
	
}
