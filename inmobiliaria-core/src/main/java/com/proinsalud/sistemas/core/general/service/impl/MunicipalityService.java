package com.proinsalud.sistemas.core.general.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.general.dao.IMunicipalityDao;
import com.proinsalud.sistemas.core.general.model.Municipality;
import com.proinsalud.sistemas.core.general.service.IMunicipalityService;

@Repository(value = "municipalityService")
public class MunicipalityService implements IMunicipalityService, Serializable {

	private static final long serialVersionUID = -7596619523794533681L;
	@Autowired(required = true)
	@Qualifier(value = "municipalityDao")
	private IMunicipalityDao iMunicipalityDao;

	@Transactional
	public Municipality persistEntity(Municipality entity) {
		return iMunicipalityDao.persistEntity(entity);
	}

	@Transactional
	public Municipality mergeEntity(Municipality entity) {
		return iMunicipalityDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Municipality entity) {
		iMunicipalityDao.deleteEntity(entity);
	}

	@Transactional
	public List<Municipality> findAllEntity() {
		return iMunicipalityDao.findAllEntity();
	}

	@Transactional
	public Municipality findEntityById(Long id) {
		return iMunicipalityDao.findEntityById(id);
	}

	@Transactional
	public List<Municipality> findByIdDepartment(Long idDepartment) {
		return iMunicipalityDao.findEntityByIdDepartment(idDepartment);
	}

}

