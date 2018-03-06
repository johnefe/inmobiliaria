package com.proinsalud.sistemas.core.human_talent.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.human_talent.dao.IAreaDao;
import com.proinsalud.sistemas.core.human_talent.model.Area;
import com.proinsalud.sistemas.core.util.comparators.GeneralComparator;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 29/01/2018 - 10:58:39 a. m.
 */
@Repository(value = "areaDao")
public class AreaDao extends GenericDao<Long, Area> implements IAreaDao, Serializable {

	private static final long serialVersionUID = -8325447240756354317L;

	public Area persistEntity(Area entity) {
		return super.persist(entity);
	}

	public Area mergeEntity(Area entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Area entity) {
		super.delete(entity);
	}

	public List<Area> findAllEntity() {
		return super.findAll();
	}

	public Area findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Area> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Area> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Area> entities) {
		super.deleteAll(entities);
	}

	@Override
	public List<Area> findAreaRoot() {
		List<Area> areas = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		areas = executeNamedQuery("Area.findAreaRoot", params);
		if (!areas.isEmpty()) {
			GeneralComparator.organized(GeneralComparator.AreaCompareByName, areas, "getAreas");
		}
		return areas;
	}

	@Override
	public List<Area> findAreaItemsRoot() {
		List<Area> areas = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		areas = executeNamedQuery("Area.findAreaRoot", params);
		if (!areas.isEmpty()) {
			Area.organizedItemsAsc(areas);
		}
		return areas;
	}

}
