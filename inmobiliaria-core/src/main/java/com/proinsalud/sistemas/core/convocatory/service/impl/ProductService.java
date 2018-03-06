package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IProductDao;
import com.proinsalud.sistemas.core.convocatory.model.Product;
import com.proinsalud.sistemas.core.convocatory.service.IProductService;
/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:04:10 a. m.
 *
 */
@Repository(value = "productService")
public class ProductService implements IProductService, Serializable {

	private static final long serialVersionUID = 5096862681405716123L;
	
	@Autowired(required = true)
	@Qualifier(value = "productDao")
	private IProductDao iProductDao;

	@Transactional
	public Product persistEntity(Product entity) {
		return iProductDao.persistEntity(entity);
	}

	@Transactional
	public Product mergeEntity(Product entity) {
		return iProductDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Product entity) {
		iProductDao.deleteEntity(entity);
	}

	@Transactional
	public List<Product> findAllEntity() {
		return iProductDao.findAllEntity();
	}

	@Transactional
	public Product findEntityById(Long id) {
		return iProductDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Product> entities) {
		iProductDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Product> entities) {
		iProductDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Product> entities) {
		iProductDao.deleteEntity(entities);
	}

}

