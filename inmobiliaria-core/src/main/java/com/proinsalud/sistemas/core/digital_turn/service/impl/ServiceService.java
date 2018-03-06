package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IServiceDao;
import com.proinsalud.sistemas.core.digital_turn.model.Service;
import com.proinsalud.sistemas.core.digital_turn.service.IServiceService;

@Repository(value = "serviceService")
public class ServiceService implements IServiceService, Serializable {

	private static final long serialVersionUID = -8719194376749010400L;
	@Autowired(required = true)
	@Qualifier(value = "serviceDao")
	private IServiceDao iServiceDao;

	@Transactional
	public Service persistEntity(Service entity) {
		return iServiceDao.persistEntity(entity);
	}

	@Transactional
	public Service mergeEntity(Service entity) {
		return iServiceDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Service entity) {
		iServiceDao.deleteEntity(entity);
	}

	@Transactional
	public List<Service> findAllEntity() {
		return iServiceDao.findAllEntity();
	}

	@Transactional
	public Service findEntityById(Long id) {
		return iServiceDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Service> entities) {
		iServiceDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Service> entities) {
		iServiceDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Service> entities) {
		iServiceDao.deleteEntity(entities);
	}

	@Transactional
	public List<Service> findAllEntityFather() {
		return iServiceDao.findAllEntityFather();
	}

	@Transactional
	public List<Service> findEntityByIdFather(Long idFather) {
		return iServiceDao.findEntityByIdFather(idFather);
	}

}

