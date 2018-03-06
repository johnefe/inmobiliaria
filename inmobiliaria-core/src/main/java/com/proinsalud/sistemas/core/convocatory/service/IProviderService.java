package com.proinsalud.sistemas.core.convocatory.service;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.Provider;

public interface IProviderService {

	public Provider persistEntity(Provider entity);
	
	public Provider mergeEntity(Provider entity);
	
	public void deleteEntity(Provider entity);
	
	public List<Provider> findAllEntity();
	
	public Provider findEntityById(Long id);
	
	public void persistEntity(List<Provider> entities);
	
	public void mergeEntity(List<Provider> entities);
	
	public void deleteEntity(List<Provider> entities);
	
	public Provider findEntityByIdPerson(Long idPerson);
	
}
