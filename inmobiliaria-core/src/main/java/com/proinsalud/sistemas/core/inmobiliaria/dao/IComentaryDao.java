package com.proinsalud.sistemas.core.inmobiliaria.dao;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.Comentary;

public interface IComentaryDao {

	public Comentary persistEntity(Comentary entity);
	
	public Comentary mergeEntity(Comentary entity);
	
	public void deleteEntity(Comentary entity);
	
	public List<Comentary> findAllEntity();
	
	public Comentary findEntityById(Long id);
	
	public void persistEntity(List<Comentary> entities);
	
	public void mergeEntity(List<Comentary> entities);
	
	public void deleteEntity(List<Comentary> entities);
	
}
