package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IOptionActionDao;
import com.proinsalud.sistemas.core.security.model.OptionAction;
import com.proinsalud.sistemas.core.security.service.IOptionActionService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:50:12 a. m.
 *
 */
@Repository(value = "optionActionService")
public class OptionActionService implements IOptionActionService, Serializable {

	private static final long serialVersionUID = -5242129361149898919L;
	@Autowired(required = true)
	@Qualifier(value = "optionActionDao")
	private IOptionActionDao iOptionActionDao;

	@Transactional
	public OptionAction persistEntity(OptionAction entity) {
		return iOptionActionDao.persistEntity(entity);
	}

	@Transactional
	public OptionAction mergeEntity(OptionAction entity) {
		return iOptionActionDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(OptionAction entity) {
		iOptionActionDao.deleteEntity(entity);
	}

	@Transactional
	public List<OptionAction> findAllEntity() {
		return iOptionActionDao.findAllEntity();
	}

	@Transactional
	public OptionAction findEntityById(Long id) {
		return iOptionActionDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<OptionAction> entities) {
		iOptionActionDao.persistEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<OptionAction> entities) {
		iOptionActionDao.deleteEntity(entities);
	}

	@Transactional
	public List<OptionAction> findAllEntityByOption(Long idOption) {
		return iOptionActionDao.findAllEntityByOption(idOption);
	}

	@Transactional
	public void updateOptionAction(List<OptionAction> newOA, List<OptionAction> deleteOA) {
		if (!newOA.isEmpty()) {
			persistEntity(newOA);
		}
		if (!deleteOA.isEmpty()) {
			deleteEntity(deleteOA);
		}
	}
}
