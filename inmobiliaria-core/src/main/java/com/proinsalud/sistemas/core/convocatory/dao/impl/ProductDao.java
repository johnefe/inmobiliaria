package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IProductDao;
import com.proinsalud.sistemas.core.convocatory.model.Product;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:02:22 a. m.
 *
 */
@Repository(value = "productDao")
public class ProductDao extends GenericDao<Long, Product> implements IProductDao, Serializable {

	private static final long serialVersionUID = -5527440860612609603L;

	public Product persistEntity(Product entity) {
		return super.persist(entity);
	}

	public Product mergeEntity(Product entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Product entity) {
		super.delete(entity);
	}

	public List<Product> findAllEntity() {
		return super.findAll();
	}

	public Product findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Product> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Product> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Product> entities) {
		super.deleteAll(entities);
	}

}
