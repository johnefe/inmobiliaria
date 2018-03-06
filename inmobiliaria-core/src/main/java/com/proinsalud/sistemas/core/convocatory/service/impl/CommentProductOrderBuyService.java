package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.ICommentProductOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.model.CommentProductOrderBuy;
import com.proinsalud.sistemas.core.convocatory.service.ICommentProductOrderBuyService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 9/02/2018 - 10:43:07 a. m.
 *
 */
@Repository(value = "commentProductOrderBuyService")
public class CommentProductOrderBuyService implements ICommentProductOrderBuyService, Serializable {

	private static final long serialVersionUID = 8687802002691655298L;
	
	@Autowired(required = true)
	@Qualifier(value = "commentProductOrderBuyDao")
	private ICommentProductOrderBuyDao iCommentProductOrderBuyDao;

	@Transactional
	public CommentProductOrderBuy persistEntity(CommentProductOrderBuy entity) {
		return iCommentProductOrderBuyDao.persistEntity(entity);
	}

	@Transactional
	public CommentProductOrderBuy mergeEntity(CommentProductOrderBuy entity) {
		return iCommentProductOrderBuyDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(CommentProductOrderBuy entity) {
		iCommentProductOrderBuyDao.deleteEntity(entity);
	}

	@Transactional
	public List<CommentProductOrderBuy> findAllEntity() {
		return iCommentProductOrderBuyDao.findAllEntity();
	}

	@Transactional
	public CommentProductOrderBuy findEntityById(Long id) {
		return iCommentProductOrderBuyDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<CommentProductOrderBuy> entities) {
		iCommentProductOrderBuyDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<CommentProductOrderBuy> entities) {
		iCommentProductOrderBuyDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<CommentProductOrderBuy> entities) {
		iCommentProductOrderBuyDao.deleteEntity(entities);
	}

	@Transactional
	public List<CommentProductOrderBuy> findAllEntityByIdProductOrderBuy(Long idProductOrderBuy) {
		return iCommentProductOrderBuyDao.findAllEntityByIdProductOrderBuy(idProductOrderBuy);
	}

}
