package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IProductOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.model.ProductOrderBuy;
import com.proinsalud.sistemas.core.convocatory.service.IProductOrderBuyService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 9/02/2018 - 10:43:07 a. m.
 *
 */
@Repository(value = "productOrderBuyService")
public class ProductOrderBuyService implements IProductOrderBuyService, Serializable {

	private static final long serialVersionUID = -7931656230906168194L;
	@Autowired(required = true)
	@Qualifier(value = "productOrderBuyDao")
	private IProductOrderBuyDao iProductOrderBuyDao;

	@Transactional
	public ProductOrderBuy persistEntity(ProductOrderBuy entity) {
		return iProductOrderBuyDao.persistEntity(entity);
	}

	@Transactional
	public ProductOrderBuy mergeEntity(ProductOrderBuy entity) {
		return iProductOrderBuyDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(ProductOrderBuy entity) {
		iProductOrderBuyDao.deleteEntity(entity);
	}

	@Transactional
	public List<ProductOrderBuy> findAllEntity() {
		return iProductOrderBuyDao.findAllEntity();
	}

	@Transactional
	public ProductOrderBuy findEntityById(Long id) {
		return iProductOrderBuyDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<ProductOrderBuy> entities) {
		iProductOrderBuyDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<ProductOrderBuy> entities) {
		iProductOrderBuyDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<ProductOrderBuy> entities) {
		iProductOrderBuyDao.deleteEntity(entities);
	}

}

