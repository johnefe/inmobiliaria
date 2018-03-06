package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.model.OrderBuy;
import com.proinsalud.sistemas.core.convocatory.service.IOrderBuyService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 9/02/2018 - 10:43:07 a. m.
 *
 */
@Repository(value = "orderBuyService")
public class OrderBuyService implements IOrderBuyService, Serializable {

	private static final long serialVersionUID = -2654822008200632421L;
	@Autowired(required = true)
	@Qualifier(value = "orderBuyDao")
	private IOrderBuyDao iOrderBuyDao;

	@Transactional
	public OrderBuy persistEntity(OrderBuy entity) {
		return iOrderBuyDao.persistEntity(entity);
	}

	@Transactional
	public OrderBuy mergeEntity(OrderBuy entity) {
		return iOrderBuyDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(OrderBuy entity) {
		iOrderBuyDao.deleteEntity(entity);
	}

	@Transactional
	public List<OrderBuy> findAllEntity() {
		return iOrderBuyDao.findAllEntity();
	}

	@Transactional
	public OrderBuy findEntityById(Long id) {
		return iOrderBuyDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<OrderBuy> entities) {
		iOrderBuyDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<OrderBuy> entities) {
		iOrderBuyDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<OrderBuy> entities) {
		iOrderBuyDao.deleteEntity(entities);
	}

}

