package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderConvocatoryTypeConvocatoryDao;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTypeConvocatory;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 15/02/2018 - 9:02:39 a. m.
 *
 */
@Repository(value = "stepOrderConvocatoryTypeConvocatoryDao")
public class StepOrderConvocatoryTypeConvocatoryDao extends GenericDao<Long, StepOrderConvocatoryTypeConvocatory> implements IStepOrderConvocatoryTypeConvocatoryDao, Serializable {

	private static final long serialVersionUID = 1948155437116295788L;

	public StepOrderConvocatoryTypeConvocatory persistEntity(StepOrderConvocatoryTypeConvocatory entity) {
		return super.persist(entity);
	}

	public StepOrderConvocatoryTypeConvocatory mergeEntity(StepOrderConvocatoryTypeConvocatory entity) {
		return super.merge(entity);
	}

	public void deleteEntity(StepOrderConvocatoryTypeConvocatory entity) {
		super.delete(entity);
	}

	public List<StepOrderConvocatoryTypeConvocatory> findAllEntity() {
		return super.findAll();
	}

	public StepOrderConvocatoryTypeConvocatory findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<StepOrderConvocatoryTypeConvocatory> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<StepOrderConvocatoryTypeConvocatory> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<StepOrderConvocatoryTypeConvocatory> entities) {
		super.deleteAll(entities);
	}

	public List<StepOrderConvocatoryTypeConvocatory> findEntityByIdTypeConvocatory(Long idTypeConvocatory) {
		List<StepOrderConvocatoryTypeConvocatory> lst = new ArrayList<StepOrderConvocatoryTypeConvocatory>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idTypeConvocatory", idTypeConvocatory);
		lst = executeNamedQuery("StepOrderConvocatoryTypeConvocatory.findEntityByIdTypeConvocatory", parametros);
		return lst;
	}

}
