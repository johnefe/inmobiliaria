package com.proinsalud.sistemas.core.inmobiliaria.service;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.Persons;

public interface IPersonsService {

	public Persons persistEntity(Persons entity);
	
	public Persons mergeEntity(Persons entity);
	
	public void deleteEntity(Persons entity);
	
	public List<Persons> findAllEntity();
	
	public Persons findEntityById(Long id);
	
	public void persistEntity(List<Persons> entities);
	
	public void mergeEntity(List<Persons> entities);
	
	public void deleteEntity(List<Persons> entities);
	
}
