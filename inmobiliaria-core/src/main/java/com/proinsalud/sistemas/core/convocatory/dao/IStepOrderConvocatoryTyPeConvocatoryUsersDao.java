package com.proinsalud.sistemas.core.convocatory.dao;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTyPeConvocatoryUsers;

public interface IStepOrderConvocatoryTyPeConvocatoryUsersDao {

	public StepOrderConvocatoryTyPeConvocatoryUsers persistEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity);
	
	public StepOrderConvocatoryTyPeConvocatoryUsers mergeEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity);
	
	public void deleteEntity(StepOrderConvocatoryTyPeConvocatoryUsers entity);
	
	public List<StepOrderConvocatoryTyPeConvocatoryUsers> findAllEntity();
	
	public StepOrderConvocatoryTyPeConvocatoryUsers findEntityById(Long id);
	
	public void persistEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities);
	
	public void mergeEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities);
	
	public void deleteEntity(List<StepOrderConvocatoryTyPeConvocatoryUsers> entities);
	
	public List<StepOrderConvocatoryTyPeConvocatoryUsers> findAllEntityByIdUser(Long idUser);
	
}
