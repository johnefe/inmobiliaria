package com.proinsalud.sistemas.core.convocatory.service;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.Convocatory;

public interface IConvocatoryService {

	public Convocatory persistEntity(Convocatory entity);
	
	public Convocatory mergeEntity(Convocatory entity);
	
	public void deleteEntity(Convocatory entity);
	
	public List<Convocatory> findAllEntity();
	
	public Convocatory findEntityById(Long id);
	
	public void persistEntity(List<Convocatory> entities);
	
	public void mergeEntity(List<Convocatory> entities);
	
	public void deleteEntity(List<Convocatory> entities);
	
	public List<Convocatory> findByAllEntityByState(String state);
	
}
