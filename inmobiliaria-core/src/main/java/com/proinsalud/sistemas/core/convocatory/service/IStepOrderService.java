package com.proinsalud.sistemas.core.convocatory.service;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.StepOrder;

public interface IStepOrderService {

	public StepOrder persistEntity(StepOrder entity);
	
	public StepOrder mergeEntity(StepOrder entity);
	
	public void deleteEntity(StepOrder entity);
	
	public List<StepOrder> findAllEntity();
	
	public StepOrder findEntityById(Long id);
	
	public void persistEntity(List<StepOrder> entities);
	
	public void mergeEntity(List<StepOrder> entities);
	
	public void deleteEntity(List<StepOrder> entities);
	
}
