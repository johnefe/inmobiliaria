package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.Convocatory;
import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.util.HibernateUtil;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:02:59 a. m.
 *
 */
@Repository(value = "convocatoryDao")
public class ConvocatoryDao extends GenericDao<Long, Convocatory> implements IConvocatoryDao, Serializable {

	private static final long serialVersionUID = -5856554637401936309L;

	public Convocatory persistEntity(Convocatory entity) {
		return super.persist(entity);
	}

	public Convocatory mergeEntity(Convocatory entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Convocatory entity) {
		super.delete(entity);
	}

	public List<Convocatory> findAllEntity() {
		List<Convocatory> lst = new ArrayList<Convocatory>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		lst = executeNamedQuery("Convocatory.findAllEntity", parametros);
		HibernateUtil.initializeObject(lst);
		return lst;
	}

	public Convocatory findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Convocatory> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Convocatory> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Convocatory> entities) {
		super.deleteAll(entities);
	}

	public List<Convocatory> findAllEntityByState(String state) {
		List<Convocatory> lst = new ArrayList<Convocatory>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("state", state);
		lst = executeNamedQuery("Convocatory.findAllEntityByState", parametros);
		HibernateUtil.initializeObject(lst);
		return lst.isEmpty() ? null : lst;
	}
}
