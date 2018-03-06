package com.proinsalud.sistemas.core.digital_turn.dao;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurn;

public interface IDigitalTurnDao {

	public DigitalTurn persistEntity(DigitalTurn entity);
	
	public DigitalTurn mergeEntity(DigitalTurn entity);
	
	public void deleteEntity(DigitalTurn entity);
	
	public List<DigitalTurn> findAllEntity();
	
	public DigitalTurn findEntityById(Long id);
	
	public void persistEntity(List<DigitalTurn> entities);
	
	public void mergeEntity(List<DigitalTurn> entities);
	
	public void deleteEntity(List<DigitalTurn> entities);
	
}
