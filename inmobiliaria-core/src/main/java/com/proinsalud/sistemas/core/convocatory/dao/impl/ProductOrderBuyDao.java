package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IProductOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.model.ProductOrderBuy;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 9/02/2018 - 10:43:07 a. m.
 *
 */
@Repository(value = "productOrderBuyDao")
public class ProductOrderBuyDao extends GenericDao<Long, ProductOrderBuy> implements IProductOrderBuyDao, Serializable {

	private static final long serialVersionUID = 4559215057653958476L;

	public ProductOrderBuy persistEntity(ProductOrderBuy entity) {
		return super.persist(entity);
	}

	public ProductOrderBuy mergeEntity(ProductOrderBuy entity) {
		return super.merge(entity);
	}

	public void deleteEntity(ProductOrderBuy entity) {
		super.delete(entity);
	}

	public List<ProductOrderBuy> findAllEntity() {
		return super.findAll();
	}

	public ProductOrderBuy findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<ProductOrderBuy> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<ProductOrderBuy> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<ProductOrderBuy> entities) {
		super.deleteAll(entities);
	}

}
