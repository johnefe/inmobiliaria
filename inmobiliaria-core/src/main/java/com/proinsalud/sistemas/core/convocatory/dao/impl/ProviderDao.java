package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IProviderDao;
import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:02:11 a. m.
 *
 */
@Repository(value = "providerDao")
public class ProviderDao extends GenericDao<Long, Provider> implements IProviderDao, Serializable {

	private static final long serialVersionUID = 2574097827292320384L;

	public Provider persistEntity(Provider entity) {
		return super.persist(entity);
	}

	public Provider mergeEntity(Provider entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Provider entity) {
		super.delete(entity);
	}

	public List<Provider> findAllEntity() {
		return super.findAll();
	}

	public Provider findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Provider> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Provider> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Provider> entities) {
		super.deleteAll(entities);
	}

	public Provider findEntityByIdPerson(Long idPerson) {
		List<Provider> lst = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idPerson", idPerson);
		lst = executeNamedQuery("Provider.findEntityByIdPerson", params);
		return lst.isEmpty() ? null : lst.get(0);
	}

}
