package com.proinsalud.sistemas.core.inmobiliaria.dao;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.Userr;

public interface IUserrDao {

	public Userr persistEntity(Userr entity);
	
	public Userr mergeEntity(Userr entity);
	
	public void deleteEntity(Userr entity);
	
	public List<Userr> findAllEntity();
	
	public Userr findEntityById(Long id);
	
	public void persistEntity(List<Userr> entities);
	
	public void mergeEntity(List<Userr> entities);
	
	public void deleteEntity(List<Userr> entities);
	
}
