package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderConvocatoryTyPeConvocatoryUsersDao;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTyPeConvocatoryUsers;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 15/02/2018 - 9:02:39 a. m.
 *
 */
@Repository(value = "stepOrderConvocatoryTyPeConvocatoryUsersDao")
public class StepOrderConvocatoryTyPeConvocatoryUsersDao extends GenericDao<Long, StepOrderConvocatoryTyPeConvocatoryUsers> implements IStepOrderConvocatoryTyPeConvocatoryUsersDao, Serializable {

	private static final long serialVersionUID = 5568850768806240444L;

	public StepOrderConvocatoryTyPeConvocatoryUsers persistEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity) {
		return super.persist(entity);
	}

	public StepOrderConvocatoryTyPeConvocatoryUsers mergeEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity) {
		return super.merge(entity);
	}

	public void deleteEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity) {
		super.delete(entity);
	}

	public List<StepOrderConvocatoryTyPeConvocatoryUsers> findAllEntity() {
		return super.findAll();
	}

	public StepOrderConvocatoryTyPeConvocatoryUsers findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities) {
		super.deleteAll(entities);
	}

	public List<StepOrderConvocatoryTyPeConvocatoryUsers> findAllEntityByIdUser(Long idUser) {
		List<StepOrderConvocatoryTyPeConvocatoryUsers> lst = new ArrayList<StepOrderConvocatoryTyPeConvocatoryUsers>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idUser", idUser);
		lst = executeNamedQuery("StepOrderConvocatoryTyPeConvocatoryUsers.findAllEntityByIdUser", parametros);
		return lst;
	}

}
