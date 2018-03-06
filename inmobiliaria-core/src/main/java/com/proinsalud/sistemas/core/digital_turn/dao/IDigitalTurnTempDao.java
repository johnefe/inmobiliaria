package com.proinsalud.sistemas.core.digital_turn.dao;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurnTemp;

public interface IDigitalTurnTempDao {

	public DigitalTurnTemp persistEntity(DigitalTurnTemp entity);
	
	public DigitalTurnTemp mergeEntity(DigitalTurnTemp entity);
	
	public void deleteEntity(DigitalTurnTemp entity);
	
	public List<DigitalTurnTemp> findAllEntity();
	
	public DigitalTurnTemp findEntityById(Long id);
	
	public void persistEntity(List<DigitalTurnTemp> entities);
	
	public void mergeEntity(List<DigitalTurnTemp> entities);
	
	public void deleteEntity(List<DigitalTurnTemp> entities);
	
	public List<DigitalTurnTemp> findEndsTwentyRegisters(Long idPoint, String enAtencion, String llamando);
	
	public List<DigitalTurnTemp> findTurnByStateAttention(Long idPoint, String atendido);
	
	public List<DigitalTurnTemp> findAllEntityByOrder();
	
	public List<DigitalTurnTemp> finAllEntitybyPoint(Long idPoint, String state);
	
	public List<List<DigitalTurnTemp>> findEntityCustomViewTV(Long idPoint);
	
}
