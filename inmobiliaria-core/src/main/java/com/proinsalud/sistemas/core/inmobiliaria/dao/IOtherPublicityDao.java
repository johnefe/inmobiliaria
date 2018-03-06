package com.proinsalud.sistemas.core.inmobiliaria.dao;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.OtherPublicity;

public interface IOtherPublicityDao {

	public OtherPublicity persistEntity(OtherPublicity entity);
	
	public OtherPublicity mergeEntity(OtherPublicity entity);
	
	public void deleteEntity(OtherPublicity entity);
	
	public List<OtherPublicity> findAllEntity();
	
	public OtherPublicity findEntityById(Long id);
	
	public void persistEntity(List<OtherPublicity> entities);
	
	public void mergeEntity(List<OtherPublicity> entities);
	
	public void deleteEntity(List<OtherPublicity> entities);
	
}
