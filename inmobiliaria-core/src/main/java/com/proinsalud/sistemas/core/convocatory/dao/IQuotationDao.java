package com.proinsalud.sistemas.core.convocatory.dao;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.Quotation;

public interface IQuotationDao {

	public Quotation persistEntity(Quotation entity);
	
	public Quotation mergeEntity(Quotation entity);
	
	public void deleteEntity(Quotation entity);
	
	public List<Quotation> findAllEntity();
	
	public Quotation findEntityById(Long id);
	
	public void persistEntity(List<Quotation> entities);
	
	public void mergeEntity(List<Quotation> entities);
	
	public void deleteEntity(List<Quotation> entities);
	
	public List<Quotation> findEntityByIdProvider(Long idProvider);
	
}
