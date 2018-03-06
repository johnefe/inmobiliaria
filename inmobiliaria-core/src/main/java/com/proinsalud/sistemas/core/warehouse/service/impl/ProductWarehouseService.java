package com.proinsalud.sistemas.core.warehouse.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.warehouse.dao.IProductWarehouseDao;
import com.proinsalud.sistemas.core.warehouse.model.ProductWarehouse;
import com.proinsalud.sistemas.core.warehouse.service.IProductWarehouseService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 19/01/2018 - 4:04:30 p. m.
 *
 */
@Repository(value = "productWarehouseService")
public class ProductWarehouseService implements IProductWarehouseService, Serializable {

	private static final long serialVersionUID = -4032060203187558380L;
	@Autowired(required = true)
	@Qualifier(value = "productWarehouseDao")
	private IProductWarehouseDao iProductWarehouseDao;

	@Transactional
	public ProductWarehouse persistEntity(ProductWarehouse entity) {
		return iProductWarehouseDao.persistEntity(entity);
	}

	@Transactional
	public ProductWarehouse mergeEntity(ProductWarehouse entity) {
		return iProductWarehouseDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(ProductWarehouse entity) {
		iProductWarehouseDao.deleteEntity(entity);
	}

	@Transactional
	public List<ProductWarehouse> findAllEntity() {
		return iProductWarehouseDao.findAllEntity();
	}

	@Transactional
	public ProductWarehouse findEntityById(Long id) {
		return iProductWarehouseDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<ProductWarehouse> entities) {
		iProductWarehouseDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<ProductWarehouse> entities) {
		iProductWarehouseDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<ProductWarehouse> entities) {
		iProductWarehouseDao.deleteEntity(entities);
	}

}
