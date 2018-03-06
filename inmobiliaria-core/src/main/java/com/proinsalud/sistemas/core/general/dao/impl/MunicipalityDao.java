package com.proinsalud.sistemas.core.general.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.general.dao.IMunicipalityDao;
import com.proinsalud.sistemas.core.general.model.Municipality;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "municipalityDao")
public class MunicipalityDao extends GenericDao<Long, Municipality> implements IMunicipalityDao, Serializable {

	private static final long serialVersionUID = 1475038395307741464L;

	public Municipality persistEntity(Municipality entity) {
		return super.persist(entity);
	}

	public Municipality mergeEntity(Municipality entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Municipality entity) {
		super.delete(entity);
	}

	public List<Municipality> findAllEntity() {
		return super.findAll();
	}

	public Municipality findEntityById(Long id) {
		return super.findById(id);
	}

	public List<Municipality> findEntityByIdDepartment(Long idDepartment) {
		List<Municipality> municipalityList = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idDepartment", idDepartment);
		municipalityList = executeNamedQuery("Municipality.findByIdDepartment", parametros);
		return municipalityList;
	}

}
