package com.proinsalud.sistemas.core.general.dao;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.Country;

public interface ICountryDao {

	public Country persistEntity(Country entity);
	
	public Country mergeEntity(Country entity);
	
	public void deleteEntity(Country entity);
	
	public List<Country> findAllEntity();
	
	public Country findEntityById(Long id);
	
}
