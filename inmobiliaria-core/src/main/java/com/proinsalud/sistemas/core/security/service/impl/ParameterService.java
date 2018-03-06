package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IParameterDao;
import com.proinsalud.sistemas.core.security.model.Parameter;
import com.proinsalud.sistemas.core.security.service.IParameterService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:50:29 a. m.
 *
 */
@Repository(value = "parameterService")
public class ParameterService implements IParameterService, Serializable {

	private static final long serialVersionUID = -6295100828948497258L;
	@Autowired(required = true)
	@Qualifier(value = "parameterDao")
	private IParameterDao iParameterDao;

	@Transactional
	public Parameter persistEntity(Parameter entity) {
		return iParameterDao.persistEntity(entity);
	}

	@Transactional
	public Parameter mergeEntity(Parameter entity) {
		return iParameterDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Parameter entity) {
		iParameterDao.deleteEntity(entity);
	}

	@Transactional
	public List<Parameter> findAllEntity() {
		return iParameterDao.findAllEntity();
	}

	@Transactional
	public Parameter findEntityById(Long id) {
		return iParameterDao.findEntityById(id);
	}

}
