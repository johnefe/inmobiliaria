package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.Convocatory;
import com.proinsalud.sistemas.core.convocatory.service.IConvocatoryService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:03:53 a. m.
 *
 */
@Repository(value = "convocatoryService")
public class ConvocatoryService implements IConvocatoryService, Serializable {

	private static final long serialVersionUID = -3388612690059785763L;
	@Autowired(required = true)
	@Qualifier(value = "convocatoryDao")
	private IConvocatoryDao iConvocatoryDao;

	@Transactional
	public Convocatory persistEntity(Convocatory entity) {
		return iConvocatoryDao.persistEntity(entity);
	}

	@Transactional
	public Convocatory mergeEntity(Convocatory entity) {
		return iConvocatoryDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Convocatory entity) {
		iConvocatoryDao.deleteEntity(entity);
	}

	@Transactional
	public List<Convocatory> findAllEntity() {
		return iConvocatoryDao.findAllEntity();
	}

	@Transactional
	public Convocatory findEntityById(Long id) {
		return iConvocatoryDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Convocatory> entities) {
		iConvocatoryDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Convocatory> entities) {
		iConvocatoryDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Convocatory> entities) {
		iConvocatoryDao.deleteEntity(entities);
	}

	@Transactional
	public List<Convocatory> findByAllEntityByState(String state) {
		return iConvocatoryDao.findAllEntityByState(state);
	}

}

