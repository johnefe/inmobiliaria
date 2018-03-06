package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.ISliderDao;
import com.proinsalud.sistemas.core.digital_turn.model.Slider;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 23/02/2018 - 4:03:19 p. m.
 */

@Repository(value = "sliderDao")
public class SliderDao extends GenericDao<Long, Slider> implements ISliderDao, Serializable {

	
	private static final long serialVersionUID = -3458419149979917361L;

	public Slider persistEntity(Slider entity) {
		return super.persist(entity);
	}

	public Slider mergeEntity(Slider entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Slider entity) {
		super.delete(entity);
	}

	public List<Slider> findAllEntity() {
		return super.findAll();
	}

	public Slider findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Slider> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Slider> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Slider> entities) {
		super.deleteAll(entities);
	}

}
