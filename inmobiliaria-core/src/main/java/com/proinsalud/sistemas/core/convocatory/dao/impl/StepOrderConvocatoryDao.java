package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatory;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 15/02/2018 - 9:02:39 a. m.
 *
 */
@Repository(value = "stepOrderConvocatoryDao")
public class StepOrderConvocatoryDao extends GenericDao<Long, StepOrderConvocatory> implements IStepOrderConvocatoryDao, Serializable {

	private static final long serialVersionUID = 230768045034962807L;

	public StepOrderConvocatory persistEntity(StepOrderConvocatory entity) {
		return super.persist(entity);
	}

	public StepOrderConvocatory mergeEntity(StepOrderConvocatory entity) {
		return super.merge(entity);
	}

	public void deleteEntity(StepOrderConvocatory entity) {
		super.delete(entity);
	}

	public List<StepOrderConvocatory> findAllEntity() {
		return super.findAll();
	}

	public StepOrderConvocatory findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<StepOrderConvocatory> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<StepOrderConvocatory> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<StepOrderConvocatory> entities) {
		super.deleteAll(entities);
	}

}
