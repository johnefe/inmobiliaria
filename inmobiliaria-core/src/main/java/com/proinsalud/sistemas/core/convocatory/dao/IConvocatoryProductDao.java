package com.proinsalud.sistemas.core.convocatory.dao;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.ConvocatoryProduct;

public interface IConvocatoryProductDao {

	public ConvocatoryProduct persistEntity(ConvocatoryProduct entity);
	
	public ConvocatoryProduct mergeEntity(ConvocatoryProduct entity);
	
	public void deleteEntity(ConvocatoryProduct entity);
	
	public List<ConvocatoryProduct> findAllEntity();
	
	public ConvocatoryProduct findEntityById(Long id);
	
	public void persistEntity(List<ConvocatoryProduct> entities);
	
	public void mergeEntity(List<ConvocatoryProduct> entities);
	
	public void deleteEntity(List<ConvocatoryProduct> entities);
	
}
