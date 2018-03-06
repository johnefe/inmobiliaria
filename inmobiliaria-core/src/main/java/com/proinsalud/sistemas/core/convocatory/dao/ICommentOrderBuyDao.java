package com.proinsalud.sistemas.core.convocatory.dao;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.CommentOrderBuy;

public interface ICommentOrderBuyDao {

	public CommentOrderBuy persistEntity(CommentOrderBuy entity);
	
	public CommentOrderBuy mergeEntity(CommentOrderBuy entity);
	
	public void deleteEntity(CommentOrderBuy entity);
	
	public List<CommentOrderBuy> findAllEntity();
	
	public CommentOrderBuy findEntityById(Long id);
	
	public void persistEntity(List<CommentOrderBuy> entities);
	
	public void mergeEntity(List<CommentOrderBuy> entities);
	
	public void deleteEntity(List<CommentOrderBuy> entities);

	public List<CommentOrderBuy> findAllEntityByIdOrderBuy(Long idOrderBuy);
	
}
