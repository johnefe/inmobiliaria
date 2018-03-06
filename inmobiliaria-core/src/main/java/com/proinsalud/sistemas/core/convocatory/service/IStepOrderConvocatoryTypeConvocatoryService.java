package com.proinsalud.sistemas.core.convocatory.service;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTypeConvocatory;

public interface IStepOrderConvocatoryTypeConvocatoryService {

	public StepOrderConvocatoryTypeConvocatory persistEntity(StepOrderConvocatoryTypeConvocatory entity);
	
	public StepOrderConvocatoryTypeConvocatory mergeEntity(StepOrderConvocatoryTypeConvocatory entity);
	
	public void deleteEntity(StepOrderConvocatoryTypeConvocatory entity);
	
	public List<StepOrderConvocatoryTypeConvocatory> findAllEntity();
	
	public StepOrderConvocatoryTypeConvocatory findEntityById(Long id);
	
	public void persistEntity(List<StepOrderConvocatoryTypeConvocatory> entities);
	
	public void mergeEntity(List<StepOrderConvocatoryTypeConvocatory> entities);
	
	public void deleteEntity(List<StepOrderConvocatoryTypeConvocatory> entities);

	public List<StepOrderConvocatoryTypeConvocatory> findEntityByIdTypeConvocatory(Long idTypeConvocatory);
	
}
