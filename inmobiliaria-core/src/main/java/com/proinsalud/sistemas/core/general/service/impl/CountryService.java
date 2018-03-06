package com.proinsalud.sistemas.core.general.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.general.dao.ICountryDao;
import com.proinsalud.sistemas.core.general.model.Country;
import com.proinsalud.sistemas.core.general.service.ICountryService;

@Repository(value = "countryService")
public class CountryService implements ICountryService, Serializable {

	private static final long serialVersionUID = -8250779744706508809L;
	@Autowired(required = true)
	@Qualifier(value = "countryDao")
	private ICountryDao iCountryDao;

	@Transactional
	public Country persistEntity(Country entity) {
		return iCountryDao.persistEntity(entity);
	}

	@Transactional
	public Country mergeEntity(Country entity) {
		return iCountryDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Country entity) {
		iCountryDao.deleteEntity(entity);
	}

	@Transactional
	public List<Country> findAllEntity() {
		return iCountryDao.findAllEntity();
	}

	@Transactional
	public Country findEntityById(Long id) {
		return iCountryDao.findEntityById(id);
	}

}

