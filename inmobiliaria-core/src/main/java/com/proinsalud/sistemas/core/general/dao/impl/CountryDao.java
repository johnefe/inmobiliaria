package com.proinsalud.sistemas.core.general.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.general.dao.ICountryDao;
import com.proinsalud.sistemas.core.general.model.Country;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "countryDao")
public class CountryDao extends GenericDao<Long, Country> implements ICountryDao, Serializable {

	private static final long serialVersionUID = 5870172373110457038L;

	public Country persistEntity(Country entity) {
		return super.persist(entity);
	}

	public Country mergeEntity(Country entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Country entity) {
		super.delete(entity);
	}

	public List<Country> findAllEntity() {
		return super.findAll();
	}

	public Country findEntityById(Long id) {
		return super.findById(id);
	}

}
