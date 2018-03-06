package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IPrefixPointDao;
import com.proinsalud.sistemas.core.digital_turn.model.PrefixPoint;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "prefixPointDao")
public class PrefixPointDao extends GenericDao<Long, PrefixPoint> implements IPrefixPointDao, Serializable {

	private static final long serialVersionUID = -2905565580803560983L;

	public PrefixPoint persistEntity(PrefixPoint entity) {
		return super.persist(entity);
	}

	public PrefixPoint mergeEntity(PrefixPoint entity) {
		return super.merge(entity);
	}

	public void deleteEntity(PrefixPoint entity) {
		super.delete(entity);
	}

	public List<PrefixPoint> findAllEntity() {
		return super.findAll();
	}

	public PrefixPoint findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<PrefixPoint> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<PrefixPoint> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<PrefixPoint> entities) {
		super.deleteAll(entities);
	}

	public PrefixPoint findEntityByPrefixPoint(Long idPrefix, Long idPoint) {
		List<PrefixPoint> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPrefix", idPrefix);
		parametros.put("idPoint", idPoint);
		lst = executeNamedQuery("PrefixPoint.findEntityByPrefixPoint", parametros);
		return lst.isEmpty() ? null : lst.get(0);
	}

	public List<PrefixPoint> findEntityByIdPoint(Long idPoint) {
		List<PrefixPoint> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPoint", idPoint);
		lst = executeNamedQuery("PrefixPoint.findEntityByIdPoint", parametros);
		return lst;
	}

}
