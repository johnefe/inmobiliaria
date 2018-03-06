package com.proinsalud.sistemas.core.warehouse.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.warehouse.dao.IProductWarehouseDao;
import com.proinsalud.sistemas.core.warehouse.model.ProductWarehouse;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 19/01/2018 - 4:04:30 p. m.
 *
 */
@Repository(value = "productWarehouseDao")
public class ProductWarehouseDao extends GenericDao<Long, ProductWarehouse> implements IProductWarehouseDao, Serializable {

	private static final long serialVersionUID = -4190740740183892608L;

	public ProductWarehouse persistEntity(ProductWarehouse entity) {
		return super.persist(entity);
	}

	public ProductWarehouse mergeEntity(ProductWarehouse entity) {
		return super.merge(entity);
	}

	public void deleteEntity(ProductWarehouse entity) {
		super.delete(entity);
	}

	public List<ProductWarehouse> findAllEntity() {
		return super.findAll();
	}

	public ProductWarehouse findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<ProductWarehouse> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<ProductWarehouse> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<ProductWarehouse> entities) {
		super.deleteAll(entities);
	}

}
