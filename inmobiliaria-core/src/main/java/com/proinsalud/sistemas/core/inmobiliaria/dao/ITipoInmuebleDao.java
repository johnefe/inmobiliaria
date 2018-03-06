package com.proinsalud.sistemas.core.inmobiliaria.dao;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.TipoInmueble;

public interface ITipoInmuebleDao {

	public TipoInmueble persistEntity(TipoInmueble entity);
	
	public TipoInmueble mergeEntity(TipoInmueble entity);
	
	public void deleteEntity(TipoInmueble entity);
	
	public List<TipoInmueble> findAllEntity();
	
	public TipoInmueble findEntityById(Long id);
	
	public void persistEntity(List<TipoInmueble> entities);
	
	public void mergeEntity(List<TipoInmueble> entities);
	
	public void deleteEntity(List<TipoInmueble> entities);
	
}
