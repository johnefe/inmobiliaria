package com.proinsalud.sistemas.core.inmobiliaria.service;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.Calification;

public interface ICalificationService {

	public Calification persistEntity(Calification entity);
	
	public Calification mergeEntity(Calification entity);
	
	public void deleteEntity(Calification entity);
	
	public List<Calification> findAllEntity();
	
	public Calification findEntityById(Long id);
	
	public void persistEntity(List<Calification> entities);
	
	public void mergeEntity(List<Calification> entities);
	
	public void deleteEntity(List<Calification> entities);
	
}
