package com.proinsalud.sistemas.core.general.service;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.Municipality;

public interface IMunicipalityService {

	public Municipality persistEntity(Municipality entity);
	
	public Municipality mergeEntity(Municipality entity);
	
	public void deleteEntity(Municipality entity);
	
	public List<Municipality> findAllEntity();
	
	public Municipality findEntityById(Long id);
	
	public List<Municipality> findByIdDepartment(Long idDepartment);
}
