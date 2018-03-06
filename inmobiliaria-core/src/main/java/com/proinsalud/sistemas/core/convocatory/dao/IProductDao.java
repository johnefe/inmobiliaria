package com.proinsalud.sistemas.core.convocatory.dao;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.Product;

public interface IProductDao {

	public Product persistEntity(Product entity);
	
	public Product mergeEntity(Product entity);
	
	public void deleteEntity(Product entity);
	
	public List<Product> findAllEntity();
	
	public Product findEntityById(Long id);
	
	public void persistEntity(List<Product> entities);
	
	public void mergeEntity(List<Product> entities);
	
	public void deleteEntity(List<Product> entities);
	
}
