package com.proinsalud.sistemas.core.general.service;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.Country;

public interface ICountryService {

	public Country persistEntity(Country entity);
	
	public Country mergeEntity(Country entity);
	
	public void deleteEntity(Country entity);
	
	public List<Country> findAllEntity();
	
	public Country findEntityById(Long id);
	
}
