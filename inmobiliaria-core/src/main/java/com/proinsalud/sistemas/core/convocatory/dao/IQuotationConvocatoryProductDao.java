package com.proinsalud.sistemas.core.convocatory.dao;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.QuotationConvocatoryProduct;

public interface IQuotationConvocatoryProductDao {

	public QuotationConvocatoryProduct persistEntity(QuotationConvocatoryProduct entity);
	
	public QuotationConvocatoryProduct mergeEntity(QuotationConvocatoryProduct entity);
	
	public void deleteEntity(QuotationConvocatoryProduct entity);
	
	public List<QuotationConvocatoryProduct> findAllEntity();
	
	public QuotationConvocatoryProduct findEntityById(Long id);
	
	public void persistEntity(List<QuotationConvocatoryProduct> entities);
	
	public void mergeEntity(List<QuotationConvocatoryProduct> entities);
	
	public void deleteEntity(List<QuotationConvocatoryProduct> entities);
	
}
