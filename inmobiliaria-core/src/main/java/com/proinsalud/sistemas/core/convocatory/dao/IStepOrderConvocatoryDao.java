package com.proinsalud.sistemas.core.convocatory.dao;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatory;

public interface IStepOrderConvocatoryDao {

	public StepOrderConvocatory persistEntity(StepOrderConvocatory entity);
	
	public StepOrderConvocatory mergeEntity(StepOrderConvocatory entity);
	
	public void deleteEntity(StepOrderConvocatory entity);
	
	public List<StepOrderConvocatory> findAllEntity();
	
	public StepOrderConvocatory findEntityById(Long id);
	
	public void persistEntity(List<StepOrderConvocatory> entities);
	
	public void mergeEntity(List<StepOrderConvocatory> entities);
	
	public void deleteEntity(List<StepOrderConvocatory> entities);
	
}
