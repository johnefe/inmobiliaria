package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.ICommentOrderBuyDao;
import com.proinsalud.sistemas.core.convocatory.model.CommentOrderBuy;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 9/02/2018 - 10:43:07 a. m.
 *
 */
@Repository(value = "commentOrderBuyDao")
public class CommentOrderBuyDao extends GenericDao<Long, CommentOrderBuy> implements ICommentOrderBuyDao, Serializable {

	private static final long serialVersionUID = 8425459836516558667L;

	public CommentOrderBuy persistEntity(CommentOrderBuy entity) {
		return super.persist(entity);
	}

	public CommentOrderBuy mergeEntity(CommentOrderBuy entity) {
		return super.merge(entity);
	}

	public void deleteEntity(CommentOrderBuy entity) {
		super.delete(entity);
	}

	public List<CommentOrderBuy> findAllEntity() {
		return super.findAll();
	}

	public CommentOrderBuy findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<CommentOrderBuy> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<CommentOrderBuy> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<CommentOrderBuy> entities) {
		super.deleteAll(entities);
	}

	public List<CommentOrderBuy> findAllEntityByIdOrderBuy(Long idOrderBuy) {
		List<CommentOrderBuy> lst = new ArrayList<CommentOrderBuy>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idOrderBuy",idOrderBuy);
		lst = executeNamedQuery("CommentOrderBuy.findAllEntityByIdOrderBuy", parametros);
		return lst;
	}

}
