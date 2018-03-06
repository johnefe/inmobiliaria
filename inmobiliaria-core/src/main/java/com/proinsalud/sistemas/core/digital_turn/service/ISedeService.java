package com.proinsalud.sistemas.core.digital_turn.service;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.Sede;

public interface ISedeService {

	public Sede persistEntity(Sede entity);
	
	public Sede mergeEntity(Sede entity);
	
	public void deleteEntity(Sede entity);
	
	public List<Sede> findAllEntity();
	
	public Sede findEntityById(Long id);
	
	public void persistEntity(List<Sede> entities);
	
	public void mergeEntity(List<Sede> entities);
	
	public void deleteEntity(List<Sede> entities);
	
}
