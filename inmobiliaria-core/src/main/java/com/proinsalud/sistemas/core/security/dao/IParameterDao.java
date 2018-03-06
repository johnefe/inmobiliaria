package com.proinsalud.sistemas.core.security.dao;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.Parameter;

public interface IParameterDao {

	public Parameter persistEntity(Parameter entity);
	
	public Parameter mergeEntity(Parameter entity);
	
	public void deleteEntity(Parameter entity);
	
	public List<Parameter> findAllEntity();
	
	public Parameter findEntityById(Long id);
	
}
