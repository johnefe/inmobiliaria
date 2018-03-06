package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.digital_turn.dao.ISliderDao;
import com.proinsalud.sistemas.core.digital_turn.model.Slider;
import com.proinsalud.sistemas.core.digital_turn.service.ISliderService;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 23/02/2018 - 4:03:52 p. m.
 */
@Repository(value = "sliderService")
public class SliderService implements ISliderService, Serializable {

	
	private static final long serialVersionUID = -2534693921503747976L;
	@Autowired(required = true)
	@Qualifier(value = "sliderDao")
	private ISliderDao iSliderDao;

	@Transactional
	public Slider persistEntity(Slider entity) {
		return iSliderDao.persistEntity(entity);
	}

	@Transactional
	public Slider mergeEntity(Slider entity) {
		return iSliderDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Slider entity) {
		iSliderDao.deleteEntity(entity);
	}

	@Transactional
	public List<Slider> findAllEntity() {
		return iSliderDao.findAllEntity();
	}

	@Transactional
	public Slider findEntityById(Long id) {
		return iSliderDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Slider> entities) {
		iSliderDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Slider> entities) {
		iSliderDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Slider> entities) {
		iSliderDao.deleteEntity(entities);
	}

}

