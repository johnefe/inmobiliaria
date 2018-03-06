package com.proinsalud.sistemas.core.human_talent.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.human_talent.dao.IAreaDao;
import com.proinsalud.sistemas.core.human_talent.model.Area;
import com.proinsalud.sistemas.core.human_talent.service.IAreaService;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 29/01/2018 - 10:58:39 a. m.
 */
@Repository(value = "areaService")
public class AreaService implements IAreaService, Serializable {

	private static final long serialVersionUID = -5979484806396233956L;
	@Autowired(required = true)
	@Qualifier(value = "areaDao")
	private IAreaDao iAreaDao;

	@Transactional
	public Area persistEntity(Area entity) {
		return iAreaDao.persistEntity(entity);
	}

	@Transactional
	public Area mergeEntity(Area entity) {
		return iAreaDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Area entity) {
		iAreaDao.deleteEntity(entity);
	}

	@Transactional
	public List<Area> findAllEntity() {
		return iAreaDao.findAllEntity();
	}
	
	@Transactional
	public List<Area> findAreaRoot(){
		return iAreaDao.findAreaRoot();
	}

	@Transactional
	public Area findEntityById(Long id) {
		return iAreaDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Area> entities) {
		iAreaDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Area> entities) {
		iAreaDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Area> entities) {
		iAreaDao.deleteEntity(entities);
	}

	@Transactional
	public List<Area> findAreaItemsRoot() {
		return iAreaDao.findAreaItemsRoot();
	}


}
