package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.ICommentProductOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.model.CommentProductOrderBuy;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 9/02/2018 - 10:43:07 a. m.
 *
 */
@Repository(value = "commentProductOrderBuyDao")
public class CommentProductOrderBuyDao extends GenericDao<Long, CommentProductOrderBuy> implements ICommentProductOrderBuyDao, Serializable {

	private static final long serialVersionUID = -3350875160813678580L;

	public CommentProductOrderBuy persistEntity(CommentProductOrderBuy entity) {
		return super.persist(entity);
	}

	public CommentProductOrderBuy mergeEntity(CommentProductOrderBuy entity) {
		return super.merge(entity);
	}

	public void deleteEntity(CommentProductOrderBuy entity) {
		super.delete(entity);
	}

	public List<CommentProductOrderBuy> findAllEntity() {
		return super.findAll();
	}

	public CommentProductOrderBuy findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<CommentProductOrderBuy> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<CommentProductOrderBuy> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<CommentProductOrderBuy> entities) {
		super.deleteAll(entities);
	}

	public List<CommentProductOrderBuy> findAllEntityByIdProductOrderBuy(Long idProductOrderBuy) {
		List<CommentProductOrderBuy> lst = new ArrayList<CommentProductOrderBuy>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idProductOrderBuy", idProductOrderBuy);
		lst = executeNamedQuery("CommentProductOrderBuy.findAllEntityByIdProductOrderBuy", parametros);
		return lst;
	}

}
