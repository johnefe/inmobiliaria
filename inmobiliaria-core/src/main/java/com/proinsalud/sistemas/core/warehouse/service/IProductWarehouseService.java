package com.proinsalud.sistemas.core.warehouse.service;

import java.util.List;

import com.proinsalud.sistemas.core.warehouse.model.ProductWarehouse;

public interface IProductWarehouseService {

	public ProductWarehouse persistEntity(ProductWarehouse entity);
	
	public ProductWarehouse mergeEntity(ProductWarehouse entity);
	
	public void deleteEntity(ProductWarehouse entity);
	
	public List<ProductWarehouse> findAllEntity();
	
	public ProductWarehouse findEntityById(Long id);
	
	public void persistEntity(List<ProductWarehouse> entities);
	
	public void mergeEntity(List<ProductWarehouse> entities);
	
	public void deleteEntity(List<ProductWarehouse> entities);
	
}
