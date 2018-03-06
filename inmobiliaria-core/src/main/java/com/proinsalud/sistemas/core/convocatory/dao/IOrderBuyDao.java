package com.proinsalud.sistemas.core.convocatory.dao;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.OrderBuy;

public interface IOrderBuyDao {

	public OrderBuy persistEntity(OrderBuy entity);
	
	public OrderBuy mergeEntity(OrderBuy entity);
	
	public void deleteEntity(OrderBuy entity);
	
	public List<OrderBuy> findAllEntity();
	
	public OrderBuy findEntityById(Long id);
	
	public void persistEntity(List<OrderBuy> entities);
	
	public void mergeEntity(List<OrderBuy> entities);
	
	public void deleteEntity(List<OrderBuy> entities);
	
}
