package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderConvocatoryTypeConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTypeConvocatory;
import com.proinsalud.sistemas.core.convocatory.service.IStepOrderConvocatoryTypeConvocatoryService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 15/02/2018 - 9:02:39 a. m.
 *
 */
@Repository(value = "stepOrderConvocatoryTypeConvocatoryService")
public class StepOrderConvocatoryTypeConvocatoryService implements IStepOrderConvocatoryTypeConvocatoryService, Serializable {

	private static final long serialVersionUID = -2130519741759728942L;
	
	@Autowired(required = true)
	@Qualifier(value = "stepOrderConvocatoryTypeConvocatoryDao")
	private IStepOrderConvocatoryTypeConvocatoryDao iStepOrderConvocatoryTypeConvocatoryDao;

	@Transactional
	public StepOrderConvocatoryTypeConvocatory persistEntity(StepOrderConvocatoryTypeConvocatory entity) {
		return iStepOrderConvocatoryTypeConvocatoryDao.persistEntity(entity);
	}

	@Transactional
	public StepOrderConvocatoryTypeConvocatory mergeEntity(StepOrderConvocatoryTypeConvocatory entity) {
		return iStepOrderConvocatoryTypeConvocatoryDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(StepOrderConvocatoryTypeConvocatory entity) {
		iStepOrderConvocatoryTypeConvocatoryDao.deleteEntity(entity);
	}

	@Transactional
	public List<StepOrderConvocatoryTypeConvocatory> findAllEntity() {
		return iStepOrderConvocatoryTypeConvocatoryDao.findAllEntity();
	}

	@Transactional
	public StepOrderConvocatoryTypeConvocatory findEntityById(Long id) {
		return iStepOrderConvocatoryTypeConvocatoryDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<StepOrderConvocatoryTypeConvocatory> entities) {
		iStepOrderConvocatoryTypeConvocatoryDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<StepOrderConvocatoryTypeConvocatory> entities) {
		iStepOrderConvocatoryTypeConvocatoryDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<StepOrderConvocatoryTypeConvocatory> entities) {
		iStepOrderConvocatoryTypeConvocatoryDao.deleteEntity(entities);
	}

	@Transactional
	public List<StepOrderConvocatoryTypeConvocatory> findEntityByIdTypeConvocatory(Long idTypeConvocatory) {
		return iStepOrderConvocatoryTypeConvocatoryDao.findEntityByIdTypeConvocatory(idTypeConvocatory);
	}

}

