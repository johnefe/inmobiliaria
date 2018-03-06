package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.ISedeDao;
import com.proinsalud.sistemas.core.digital_turn.model.Sede;
import com.proinsalud.sistemas.core.digital_turn.service.ISedeService;

@Repository(value = "sedeService")
public class SedeService implements ISedeService, Serializable {

	private static final long serialVersionUID = -7394485211401938175L;
	@Autowired(required = true)
	@Qualifier(value = "sedeDao")
	private ISedeDao iSedeDao;

	@Transactional
	public Sede persistEntity(Sede entity) {
		return iSedeDao.persistEntity(entity);
	}

	@Transactional
	public Sede mergeEntity(Sede entity) {
		return iSedeDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Sede entity) {
		iSedeDao.deleteEntity(entity);
	}

	@Transactional
	public List<Sede> findAllEntity() {
		return iSedeDao.findAllEntity();
	}

	@Transactional
	public Sede findEntityById(Long id) {
		return iSedeDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Sede> entities) {
		iSedeDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Sede> entities) {
		iSedeDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Sede> entities) {
		iSedeDao.deleteEntity(entities);
	}

}

