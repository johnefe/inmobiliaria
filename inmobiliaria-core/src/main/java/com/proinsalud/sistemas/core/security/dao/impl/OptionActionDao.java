package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IOptionActionDao;
import com.proinsalud.sistemas.core.security.model.OptionAction;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:48:04 a. m.
 *
 */
@Repository(value = "optionActionDao")
public class OptionActionDao extends GenericDao<Long, OptionAction> implements IOptionActionDao, Serializable {

	private static final long serialVersionUID = 2050148681732617656L;

	public OptionAction persistEntity(OptionAction entity) {
		return super.persist(entity);
	}

	public OptionAction mergeEntity(OptionAction entity) {
		return super.merge(entity);
	}

	public void deleteEntity(OptionAction entity) {
		super.delete(entity);
	}

	public List<OptionAction> findAllEntity() {
		return super.findAll();
	}

	public OptionAction findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<OptionAction> entities) {
		super.persistAll(entities);
	}

	public void deleteEntity(List<OptionAction> entities) {
		super.deleteAll(entities);
	}

	public List<OptionAction> findAllEntityByOption(Long idOption) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idOption", idOption);
		return executeNamedQuery("OptionAction.findAllEntityByOption", params);
	}

}
