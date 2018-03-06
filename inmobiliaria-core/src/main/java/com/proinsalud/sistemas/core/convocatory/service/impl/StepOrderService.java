package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderDao;
import com.proinsalud.sistemas.core.convocatory.model.StepOrder;
import com.proinsalud.sistemas.core.convocatory.service.IStepOrderService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 15/02/2018 - 9:02:39 a. m.
 *
 */
@Repository(value = "stepOrderService")
public class StepOrderService implements IStepOrderService, Serializable {

	private static final long serialVersionUID = -498986915723035320L;
	
	@Autowired(required = true)
	@Qualifier(value = "stepOrderDao")
	private IStepOrderDao iStepOrderDao;

	@Transactional
	public StepOrder persistEntity(StepOrder entity) {
		return iStepOrderDao.persistEntity(entity);
	}

	@Transactional
	public StepOrder mergeEntity(StepOrder entity) {
		return iStepOrderDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(StepOrder entity) {
		iStepOrderDao.deleteEntity(entity);
	}

	@Transactional
	public List<StepOrder> findAllEntity() {
		return iStepOrderDao.findAllEntity();
	}

	@Transactional
	public StepOrder findEntityById(Long id) {
		return iStepOrderDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<StepOrder> entities) {
		iStepOrderDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<StepOrder> entities) {
		iStepOrderDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<StepOrder> entities) {
		iStepOrderDao.deleteEntity(entities);
	}

}

