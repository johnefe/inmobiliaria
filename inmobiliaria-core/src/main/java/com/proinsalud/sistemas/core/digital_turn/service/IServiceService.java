package com.proinsalud.sistemas.core.digital_turn.service;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.Service;

public interface IServiceService {

	public Service persistEntity(Service entity);
	
	public Service mergeEntity(Service entity);
	
	public void deleteEntity(Service entity);
	
	public List<Service> findAllEntity();
	
	public Service findEntityById(Long id);
	
	public void persistEntity(List<Service> entities);
	
	public void mergeEntity(List<Service> entities);
	
	public void deleteEntity(List<Service> entities);
	
	public List<Service> findAllEntityFather();
	
	public List<Service> findEntityByIdFather(Long idFather);
	
}
