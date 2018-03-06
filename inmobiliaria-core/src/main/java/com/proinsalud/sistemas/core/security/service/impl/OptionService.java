package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IOptionDao;
import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.security.service.IOptionService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:50:22 a. m.
 *
 */
@Repository(value = "optionService")
public class OptionService implements IOptionService, Serializable {

	private static final long serialVersionUID = -7457231559275330781L;

	@Autowired(required = true)
	@Qualifier(value = "optionDao")
	private IOptionDao iOptionDao;

	@Transactional
	public Option persistEntity(Option entity) {
		return iOptionDao.persistEntity(entity);
	}

	@Transactional
	public Option mergeEntity(Option entity) {
		return iOptionDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Option entity) {
		iOptionDao.deleteEntity(entity);
	}

	@Transactional
	public List<Option> findAllEntity() {
		return iOptionDao.findAllEntity();
	}

	@Transactional
	public Option findEntityById(Long id) {
		return iOptionDao.findEntityById(id);
	}

	@Transactional
	public List<Option> findByLevel(Integer level) {
		return iOptionDao.findByLevel(level);
	}
}
