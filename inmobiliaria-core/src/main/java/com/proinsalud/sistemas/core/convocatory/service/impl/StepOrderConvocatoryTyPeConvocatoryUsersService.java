package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IStepOrderConvocatoryTyPeConvocatoryUsersDao;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTyPeConvocatoryUsers;
import com.proinsalud.sistemas.core.convocatory.service.IStepOrderConvocatoryTyPeConvocatoryUsersService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 15/02/2018 - 9:02:39 a. m.
 *
 */
@Repository(value = "stepOrderConvocatoryTyPeConvocatoryUsersService")
public class StepOrderConvocatoryTyPeConvocatoryUsersService implements IStepOrderConvocatoryTyPeConvocatoryUsersService, Serializable {

	private static final long serialVersionUID = 3899164098114849852L;
	
	@Autowired(required = true)
	@Qualifier(value = "stepOrderConvocatoryTyPeConvocatoryUsersDao")
	private IStepOrderConvocatoryTyPeConvocatoryUsersDao iStepOrderConvocatoryTyPeConvocatoryUsersDao;

	@Transactional
	public StepOrderConvocatoryTyPeConvocatoryUsers persistEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity) {
		return iStepOrderConvocatoryTyPeConvocatoryUsersDao.persistEntity(entity);
	}

	@Transactional
	public StepOrderConvocatoryTyPeConvocatoryUsers mergeEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity) {
		return iStepOrderConvocatoryTyPeConvocatoryUsersDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity) {
		iStepOrderConvocatoryTyPeConvocatoryUsersDao.deleteEntity(entity);
	}

	@Transactional
	public List<StepOrderConvocatoryTyPeConvocatoryUsers> findAllEntity() {
		return iStepOrderConvocatoryTyPeConvocatoryUsersDao.findAllEntity();
	}

	@Transactional
	public StepOrderConvocatoryTyPeConvocatoryUsers findEntityById(Long id) {
		return iStepOrderConvocatoryTyPeConvocatoryUsersDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities) {
		iStepOrderConvocatoryTyPeConvocatoryUsersDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities) {
		iStepOrderConvocatoryTyPeConvocatoryUsersDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities) {
		iStepOrderConvocatoryTyPeConvocatoryUsersDao.deleteEntity(entities);
	}

	@Transactional
	public List<StepOrderConvocatoryTyPeConvocatoryUsers> findAllEntityByIdUser(Long idUser) {
		return iStepOrderConvocatoryTyPeConvocatoryUsersDao.findAllEntityByIdUser(idUser);
	}

}

