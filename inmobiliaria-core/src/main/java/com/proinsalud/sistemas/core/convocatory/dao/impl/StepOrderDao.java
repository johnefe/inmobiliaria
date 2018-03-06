package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderDao;
import com.proinsalud.sistemas.core.convocatory.model.StepOrder;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 15/02/2018 - 9:02:39 a. m.
 *
 */
@Repository(value = "stepOrderDao")
public class StepOrderDao extends GenericDao<Long, StepOrder> implements IStepOrderDao, Serializable {

	private static final long serialVersionUID = 7475951215572836033L;

	public StepOrder persistEntity(StepOrder entity) {
		return super.persist(entity);
	}

	public StepOrder mergeEntity(StepOrder entity) {
		return super.merge(entity);
	}

	public void deleteEntity(StepOrder entity) {
		super.delete(entity);
	}

	public List<StepOrder> findAllEntity() {
		return super.findAll();
	}

	public StepOrder findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<StepOrder> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<StepOrder> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<StepOrder> entities) {
		super.deleteAll(entities);
	}

}
