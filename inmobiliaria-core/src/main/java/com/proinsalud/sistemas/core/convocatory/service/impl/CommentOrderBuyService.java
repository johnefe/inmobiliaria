package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.ICommentOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.model.CommentOrderBuy;
import com.proinsalud.sistemas.core.convocatory.service.ICommentOrderBuyService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 9/02/2018 - 10:43:07 a. m.
 *
 */
@Repository(value = "commentOrderBuyService")
public class CommentOrderBuyService implements ICommentOrderBuyService, Serializable {

	private static final long serialVersionUID = 5480106850298356713L;
	
	@Autowired(required = true)
	@Qualifier(value = "commentOrderBuyDao")
	private ICommentOrderBuyDao iCommentOrderBuyDao;

	@Transactional
	public CommentOrderBuy persistEntity(CommentOrderBuy entity) {
		return iCommentOrderBuyDao.persistEntity(entity);
	}

	@Transactional
	public CommentOrderBuy mergeEntity(CommentOrderBuy entity) {
		return iCommentOrderBuyDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(CommentOrderBuy entity) {
		iCommentOrderBuyDao.deleteEntity(entity);
	}

	@Transactional
	public List<CommentOrderBuy> findAllEntity() {
		return iCommentOrderBuyDao.findAllEntity();
	}

	@Transactional
	public CommentOrderBuy findEntityById(Long id) {
		return iCommentOrderBuyDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<CommentOrderBuy> entities) {
		iCommentOrderBuyDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<CommentOrderBuy> entities) {
		iCommentOrderBuyDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<CommentOrderBuy> entities) {
		iCommentOrderBuyDao.deleteEntity(entities);
	}

	@Transactional
	public List<CommentOrderBuy> findAllEntityByIdOrderBuy(Long idOrderBuy) {
		return iCommentOrderBuyDao.findAllEntityByIdOrderBuy(idOrderBuy);
		
	}

}
