package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.model.OrderBuy;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 9/02/2018 - 10:43:07 a. m.
 *
 */
@Repository(value = "orderBuyDao")
public class OrderBuyDao extends GenericDao<Long, OrderBuy> implements IOrderBuyDao, Serializable {

	private static final long serialVersionUID = 7025173441036267848L;

	public OrderBuy persistEntity(OrderBuy entity) {
		return super.persist(entity);
	}

	public OrderBuy mergeEntity(OrderBuy entity) {
		return super.merge(entity);
	}

	public void deleteEntity(OrderBuy entity) {
		super.delete(entity);
	}

	public List<OrderBuy> findAllEntity() {
		return super.findAll();
	}

	public OrderBuy findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<OrderBuy> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<OrderBuy> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<OrderBuy> entities) {
		super.deleteAll(entities);
	}

}
