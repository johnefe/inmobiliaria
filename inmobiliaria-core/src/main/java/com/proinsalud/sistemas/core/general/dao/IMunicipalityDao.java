package com.proinsalud.sistemas.core.general.dao;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.Municipality;

public interface IMunicipalityDao {

	public Municipality persistEntity(Municipality entity);
	
	public Municipality mergeEntity(Municipality entity);
	
	public void deleteEntity(Municipality entity);
	
	public List<Municipality> findAllEntity();
	
	public Municipality findEntityById(Long id);
	
	public List<Municipality> findEntityByIdDepartment(Long idDepartment);
	
}
