package com.proinsalud.sistemas.core.convocatory.service;

import java.util.List;

import com.proinsalud.sistemas.core.convocatory.model.CommentProductOrderBuy;

public interface ICommentProductOrderBuyService {

	public CommentProductOrderBuy persistEntity(CommentProductOrderBuy entity);
	
	public CommentProductOrderBuy mergeEntity(CommentProductOrderBuy entity);
	
	public void deleteEntity(CommentProductOrderBuy entity);
	
	public List<CommentProductOrderBuy> findAllEntity();
	
	public CommentProductOrderBuy findEntityById(Long id);
	
	public void persistEntity(List<CommentProductOrderBuy> entities);
	
	public void mergeEntity(List<CommentProductOrderBuy> entities);
	
	public void deleteEntity(List<CommentProductOrderBuy> entities);

	public List<CommentProductOrderBuy> findAllEntityByIdProductOrderBuy(Long idProductOrderBuy);
	
}
