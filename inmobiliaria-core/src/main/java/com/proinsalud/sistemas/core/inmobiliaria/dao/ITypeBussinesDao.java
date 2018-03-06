package com.proinsalud.sistemas.core.inmobiliaria.dao;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.TypeBussines;

public interface ITypeBussinesDao {

	public TypeBussines persistEntity(TypeBussines entity);
	
	public TypeBussines mergeEntity(TypeBussines entity);
	
	public void deleteEntity(TypeBussines entity);
	
	public List<TypeBussines> findAllEntity();
	
	public TypeBussines findEntityById(Long id);
	
	public void persistEntity(List<TypeBussines> entities);
	
	public void mergeEntity(List<TypeBussines> entities);
	
	public void deleteEntity(List<TypeBussines> entities);
	
}
