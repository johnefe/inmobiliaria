package com.proinsalud.sistemas.core.convocatory.service;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.TypeConvocatory;

public interface ITypeConvocatoryService {

	public TypeConvocatory persistEntity(TypeConvocatory entity);
	
	public TypeConvocatory mergeEntity(TypeConvocatory entity);
	
	public void deleteEntity(TypeConvocatory entity);
	
	public List<TypeConvocatory> findAllEntity();
	
	public TypeConvocatory findEntityById(Long id);
	
	public void persistEntity(List<TypeConvocatory> entities);
	
	public void mergeEntity(List<TypeConvocatory> entities);
	
	public void deleteEntity(List<TypeConvocatory> entities);
	
}
