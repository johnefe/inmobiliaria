package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.security.dao.IParameterDao;
import com.proinsalud.sistemas.core.security.model.Parameter;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:48:19 a. m.
 *
 */
@Repository(value = "parameterDao")
public class ParameterDao extends GenericDao<Long, Parameter> implements IParameterDao, Serializable {

	private static final long serialVersionUID = -2960407897725601448L;

	public Parameter persistEntity(Parameter entity) {
		return super.persist(entity);
	}

	public Parameter mergeEntity(Parameter entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Parameter entity) {
		super.delete(entity);
	}

	public List<Parameter> findAllEntity() {
		return super.findAll();
	}

	public Parameter findEntityById(Long id) {
		return super.findById(id);
	}

}
