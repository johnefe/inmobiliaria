package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatory;
import com.proinsalud.sistemas.core.convocatory.service.IStepOrderConvocatoryService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 15/02/2018 - 9:02:39 a. m.
 *
 */
@Repository(value = "stepOrderConvocatoryService")
public class StepOrderConvocatoryService implements IStepOrderConvocatoryService, Serializable {

	private static final long serialVersionUID = 8993423619562230076L;
	
	@Autowired(required = true)
	@Qualifier(value = "stepOrderConvocatoryDao")
	private IStepOrderConvocatoryDao iStepOrderConvocatoryDao;

	@Transactional
	public StepOrderConvocatory persistEntity(StepOrderConvocatory entity) {
		return iStepOrderConvocatoryDao.persistEntity(entity);
	}

	@Transactional
	public StepOrderConvocatory mergeEntity(StepOrderConvocatory entity) {
		return iStepOrderConvocatoryDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(StepOrderConvocatory entity) {
		iStepOrderConvocatoryDao.deleteEntity(entity);
	}

	@Transactional
	public List<StepOrderConvocatory> findAllEntity() {
		return iStepOrderConvocatoryDao.findAllEntity();
	}

	@Transactional
	public StepOrderConvocatory findEntityById(Long id) {
		return iStepOrderConvocatoryDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<StepOrderConvocatory> entities) {
		iStepOrderConvocatoryDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<StepOrderConvocatory> entities) {
		iStepOrderConvocatoryDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<StepOrderConvocatory> entities) {
		iStepOrderConvocatoryDao.deleteEntity(entities);
	}

}

