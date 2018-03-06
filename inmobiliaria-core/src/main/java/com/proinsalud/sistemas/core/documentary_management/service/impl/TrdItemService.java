package com.proinsalud.sistemas.core.documentary_management.service.impl;

import java.io.Serializable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.documentary_management.dao.ITrdItemDao;
import com.proinsalud.sistemas.core.documentary_management.model.TrdItem;
import com.proinsalud.sistemas.core.documentary_management.service.ITrdItemService;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 29/01/2018 - 10:58:39 a. m.
 */
@Repository(value = "trdItemService")
public class TrdItemService implements ITrdItemService, Serializable {

	private static final long serialVersionUID = 5199668215127838348L;
	@Autowired(required = true)
	@Qualifier(value = "trdItemDao")
	private ITrdItemDao iTrdItemDao;

	@Override
	@Transactional
	public TrdItem persistEntity(TrdItem entity) {
		return iTrdItemDao.persistEntity(entity);
	}

	@Override
	@Transactional
	public TrdItem mergeEntity(TrdItem entity) {
		return iTrdItemDao.mergeEntity(entity);
	}

	@Override
	@Transactional
	public void deleteEntity(TrdItem entity) {
		iTrdItemDao.deleteEntity(entity);
	}

	@Override
	@Transactional
	public List<TrdItem> findAllEntity() {
		return iTrdItemDao.findAllEntity();
	}

	@Override
	@Transactional
	public TrdItem findEntityById(Long id) {
		return iTrdItemDao.findEntityById(id);
	}

	@Override
	@Transactional
	public void persistEntity(List<TrdItem> entities) {
		iTrdItemDao.persistEntity(entities);
	}

	@Override
	@Transactional
	public void mergeEntity(List<TrdItem> entities) {
		iTrdItemDao.mergeEntity(entities);
	}

	@Override
	@Transactional
	public void deleteEntity(List<TrdItem> entities) {
		iTrdItemDao.deleteEntity(entities);
	}

	@Override
	@Transactional
	public List<TrdItem> findAllEntityByLevelTrdItem(Integer level) {
		return iTrdItemDao.findAllEntityByLevelTrdItem(level);
	}

}
