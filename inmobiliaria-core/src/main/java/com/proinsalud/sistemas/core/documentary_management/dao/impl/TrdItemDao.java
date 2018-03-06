package com.proinsalud.sistemas.core.documentary_management.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.documentary_management.dao.ITrdItemDao;
import com.proinsalud.sistemas.core.documentary_management.model.TrdItem;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * @author Ing Jhon Frey Diaz
 * @datetime 17/01/2018 - 8:25:29 a. m.
 */
@Repository(value = "trdItemDao")
public class TrdItemDao extends GenericDao<Long, TrdItem> implements ITrdItemDao, Serializable {

	private static final long serialVersionUID = 4624238574192695854L;

	@Override
	public TrdItem persistEntity(TrdItem entity) {
		return super.persist(entity);
	}

	@Override
	public TrdItem mergeEntity(TrdItem entity) {
		return super.merge(entity);
	}

	@Override
	public void deleteEntity(TrdItem entity) {
		super.delete(entity);
	}

	@Override
	public List<TrdItem> findAllEntity() {
		return super.findAll();
	}

	@Override
	public TrdItem findEntityById(Long id) {
		return super.findById(id);
	}

	@Override
	public void persistEntity(List<TrdItem> entities) {
		super.persistAll(entities);
	}

	@Override
	public void mergeEntity(List<TrdItem> entities) {
		super.mergeAll(entities);
	}

	@Override
	public void deleteEntity(List<TrdItem> entities) {
		super.deleteAll(entities);
	}

	@Override
	public List<TrdItem> findAllEntityByLevelTrdItem(Integer level) {
		List<TrdItem> lst = new ArrayList<TrdItem>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("level", level);
		lst = executeNamedQuery("TrdItem.findAllEntityByLevelTrdItem", parametros);
		return lst.isEmpty() ? null : lst;
	}

}
