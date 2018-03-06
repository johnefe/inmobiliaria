package com.proinsalud.sistemas.core.inmobiliaria.dao;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.TypePublicity;

public interface ITypePublicityDao {

	public TypePublicity persistEntity(TypePublicity entity);
	
	public TypePublicity mergeEntity(TypePublicity entity);
	
	public void deleteEntity(TypePublicity entity);
	
	public List<TypePublicity> findAllEntity();
	
	public TypePublicity findEntityById(Long id);
	
	public void persistEntity(List<TypePublicity> entities);
	
	public void mergeEntity(List<TypePublicity> entities);
	
	public void deleteEntity(List<TypePublicity> entities);
	
}
