package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.Parameter;

public interface IParameterService {

	public Parameter persistEntity(Parameter entity);
	
	public Parameter mergeEntity(Parameter entity);
	
	public void deleteEntity(Parameter entity);
	
	public List<Parameter> findAllEntity();
	
	public Parameter findEntityById(Long id);
	
}
