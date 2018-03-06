package com.proinsalud.sistemas.core.inmobiliaria.dao;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.Inmueble;

public interface IInmuebleDao {

	public Inmueble persistEntity(Inmueble entity);
	
	public Inmueble mergeEntity(Inmueble entity);
	
	public void deleteEntity(Inmueble entity);
	
	public List<Inmueble> findAllEntity();
	
	public Inmueble findEntityById(Long id);
	
	public void persistEntity(List<Inmueble> entities);
	
	public void mergeEntity(List<Inmueble> entities);
	
	public void deleteEntity(List<Inmueble> entities);
	
}
