package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.IPrefixPointDao;
import com.proinsalud.sistemas.core.digital_turn.model.PrefixPoint;
import com.proinsalud.sistemas.core.digital_turn.service.IPrefixPointService;

@Repository(value = "prefixPointService")
public class PrefixPointService implements IPrefixPointService, Serializable {
	
	private static final long serialVersionUID = 3091549543751770039L;
	@Autowired(required = true)
	@Qualifier(value = "prefixPointDao")
	private IPrefixPointDao iPrefixPointDao;

	@Transactional
	public PrefixPoint persistEntity(PrefixPoint entity) {
		return iPrefixPointDao.persistEntity(entity);
	}

	@Transactional
	public PrefixPoint mergeEntity(PrefixPoint entity) {
		return iPrefixPointDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(PrefixPoint entity) {
		iPrefixPointDao.deleteEntity(entity);
	}

	@Transactional
	public List<PrefixPoint> findAllEntity() {
		return iPrefixPointDao.findAllEntity();
	}

	@Transactional
	public PrefixPoint findEntityById(Long id) {
		return iPrefixPointDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<PrefixPoint> entities) {
		iPrefixPointDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<PrefixPoint> entities) {
		iPrefixPointDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<PrefixPoint> entities) {
		iPrefixPointDao.deleteEntity(entities);
	}

	@Transactional
	public PrefixPoint findEntityByPrefixPoint(Long idPrefix, Long idPoint) {
		return iPrefixPointDao.findEntityByPrefixPoint(idPrefix, idPoint);
	}

	@Transactional
	public List<PrefixPoint> findEntityByIdPoint(Long idPoint) {
		return iPrefixPointDao.findEntityByIdPoint(idPoint);
	}

}

