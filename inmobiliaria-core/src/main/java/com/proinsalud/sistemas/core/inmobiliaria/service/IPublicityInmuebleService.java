package com.proinsalud.sistemas.core.inmobiliaria.service;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.PublicityInmueble;

public interface IPublicityInmuebleService {

	public PublicityInmueble persistEntity(PublicityInmueble entity);
	
	public PublicityInmueble mergeEntity(PublicityInmueble entity);
	
	public void deleteEntity(PublicityInmueble entity);
	
	public List<PublicityInmueble> findAllEntity();
	
	public PublicityInmueble findEntityById(Long id);
	
	public void persistEntity(List<PublicityInmueble> entities);
	
	public void mergeEntity(List<PublicityInmueble> entities);
	
	public void deleteEntity(List<PublicityInmueble> entities);
	
}
