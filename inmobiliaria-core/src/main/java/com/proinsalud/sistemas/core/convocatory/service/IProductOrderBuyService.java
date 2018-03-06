package com.proinsalud.sistemas.core.convocatory.service;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.ProductOrderBuy;

public interface IProductOrderBuyService {

	public ProductOrderBuy persistEntity(ProductOrderBuy entity);
	
	public ProductOrderBuy mergeEntity(ProductOrderBuy entity);
	
	public void deleteEntity(ProductOrderBuy entity);
	
	public List<ProductOrderBuy> findAllEntity();
	
	public ProductOrderBuy findEntityById(Long id);
	
	public void persistEntity(List<ProductOrderBuy> entities);
	
	public void mergeEntity(List<ProductOrderBuy> entities);
	
	public void deleteEntity(List<ProductOrderBuy> entities);
	
}
