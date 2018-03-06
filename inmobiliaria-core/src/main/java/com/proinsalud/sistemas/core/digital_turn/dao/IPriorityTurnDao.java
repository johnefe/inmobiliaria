package com.proinsalud.sistemas.core.digital_turn.dao;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.PriorityTurn;

public interface IPriorityTurnDao {

	public PriorityTurn persistEntity(PriorityTurn entity);
	
	public PriorityTurn mergeEntity(PriorityTurn entity);
	
	public void deleteEntity(PriorityTurn entity);
	
	public List<PriorityTurn> findAllEntity();
	
	public PriorityTurn findEntityById(Long id);
	
	public void persistEntity(List<PriorityTurn> entities);
	
	public void mergeEntity(List<PriorityTurn> entities);
	
	public void deleteEntity(List<PriorityTurn> entities);
	
}
